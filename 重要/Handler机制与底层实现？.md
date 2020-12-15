`Handler`

## 回答要点

1. Handler 最终都会调用 **sendMessageAtTime** 方法
2. MessageQueue 的实现是**单链表**
3. 要提到 Looper 在 prepare() 时到 **ThreadLocal**
4. 要讲到 native 层，特别是 **epoll** 相关的方法（epoll_wait()、epoll_create()、epoll_ctl() 三个方法），以及几个原生方法调用的时机
5. 要讲到 native 层的 MessageQueue ，native 层的 Looper（最终功能实现的地方）

## 几个重要的方法

- Looper()：创建 MessageQueue
- Looper.prepare()：new 出 Looper，并将其存入 ThreadLocal
- Looper.loop()：开始无限循环，从 MessageQueue 中取出 Message，并分发到 target
- MessageQueue()：创建原生的 MessageQueue，原生的 MessageQueue 会创建原生的 Looper
- messageQueue.next()：取出下一条消息，如果没有就阻塞
- messageQueue.nativePollOnce()：核心方法，原生的取出下一条消息，如果没有就调用 epoll_wait() 阻塞等待
- messageQueue.enqueueMessage()：向消息队列插入消息，有很多条件，一般会调用 nativeWake()
- messageQueue.nativeWake()：底层 Looper 会向 mWakeEventFd 写 1，尝试唤醒
- handler.sendMessageAtTime()：Handler 的所有 send 和 post 最终都会调用这个方法
- handler.dispatchMessage()：Looper 取出消息后，交给 target 分发

## 原理

1. **Looper**：有两个重要的方法，分别是 prepare() 和 loop()，prepare() 方法会调用构造函数，创建对应的 MessageQueue 和保存当前的线程，并把 Looper 放入 ThreadLocal 中；loop() 方法，会无限从 MessageQueue 中取出消息，通过调用 queue.next() 的方法，当队列中没有消息时，next() 会无限循环，造成阻塞，等来 MessageQueue 中加入新消息，然后再唤醒；Looper 从队列中取到消息后，会拿到当前 Message的 target 指向的 Handler，并调用 Handler 的 dispatchMessage() 进行分发，进而调用我们重写的 handleMessage 或 者 handleCallback()，主线程的 Looper 在 ActivityThread 中被初始化和开始循环

2. **Handler**：Handler 依附于创建 Handler 的线程，因为它持有当前线程的 Looper。不论是调用 post 方法还是 send 方法，最终都会调用 sendMessageAtTime(Message msg, long uptimeMillis)，然后调用 Handler 的 enqueueMessage(Messagequeue queue, Message msg, long uptimeMillis) 方法，实际调用 MessageQueue 的 enqueueMessage(Message msg, long when) 方法

3. **MessageQueue**：在 Looper 初始化的时候被初始化，维护着消息队列（实际是单链表的）Message mMessages，每个节点就是一个 Message

4. **Message**：作为消息队列中的一个对象，他本身就是一个节点，不需要再用 Node 包装，通过字段 next 连接下一个 Message，target 指向 Handler，进而执行后面的消息分发

5. **ThreadLocal**：在 Looper 的成员变量中创建，在 prepare() 时，会把 Looper 存进 ThreadLocal 中，使用 ThreadLocal 的 set() 或者 get() 方法时，需要传入当前的线程t作为参数，ThreadLocal 是属于线程的。因此 get() 的语义是获得属于当前线程的变量。注意：ThreadLocalMap 的引用由 Thread 唯一持有，意思就是 ThreadLocalMap 跟随 Thread 对象一起被回收。

## 原理图

<img src="../assets/Handler的原理.png" style="zoom:80%;" />

## IdleHandler 的原理？

[IdleHandler的原理？](./IdleHandler的原理？.md)

## 同步屏障的原理？

TODO

## HandlerThread 的实现原理？

TODO

## select、poll、epoll 对比？

#### 1. select：时间复杂度 O(n)

它仅仅知道了，有 I/O 事件发生了，却并不知道是哪那几个流（可能有一个，多个，甚至全部），我们只能无差别轮询所有流，找出能读出数据，或者写入数据的流，对他们进行操作。所以 select 具有 O(n) 的**无差别轮询复杂度**，同时处理的流越多，无差别轮询时间就越长。

#### 2. poll：时间复杂度 O(n)

poll 本质上和 select 没有区别，它将用户传入的数组拷贝到内核空间，然后查询每个 fd 对应的设备状态， 但是它没有最大连接数的限制，原因是它是基于**链表**来存储的.

#### 3. epoll：时间复杂度 O(1)

epoll 可以理解为 event poll，不同于忙轮询和无差别轮询，epoll 会把哪个流发生了怎样的 I/O 事件通知我们。所以我们说 epoll 实际上是 

**事件驱动**（每个事件关联上 fd）的，此时我们对这些流的操作都是有意义的。（复杂度降低到了 O(1)）


## 链接
[公众号：鸿洋：Handler 10问，你顶的住吗？（好文！）](https://mp.weixin.qq.com/s/V1xI2M8AibgB2whHSOTQGQ)

[简书：Android消息机制的原理及源码解析](https://www.jianshu.com/p/f10)

[慕课网：Handler发送消息的Delay靠谱吗？](https://coding.imooc.com/lesson/317.html#mid=22311"%3Ehttps://coding.imooc.com/lesson/317.html)

[Android消息机制（讲得不错）](https://www.jianshu.com/p/57a426b8f145)

[Android消息机制2-Handler\(Native层\)](http://gityuan.com/2015/12/27/handler-message-native/)

[select、poll、epoll之间的区别(搜狗面试)](https://www.cnblogs.com/aspirant/p/9166944.html)


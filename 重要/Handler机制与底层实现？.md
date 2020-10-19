## 回答要点

1. Handler 最终都会调用 **sendMessageAtTime** 方法
2. MessageQueue 的实现是**单链表**
3. 要提到 Looper 在准备时到 **ThreadLocal**
4. 要讲到 native 层，特别是 **epoll** 相关的方法（epoll_wait、epoll_create、epoll_ctl 三个方法），以及几个 native 方法调用的时机
5. 要讲到 native 层的 MessageQueue ，native 层的 Looper（主要实现的地方）

## 原理

1. **Looper**：有两个重要的方法，分别是 prepare() 和 loop()，prepare() 方法会调用构造函数，创建对应的 MessageQueue 和保存当前的线程，并把 Looper 放入 ThreadLocal 中；loop() 方法，会无限从 MessageQueue 中取出消息，通过调用 queue.next() 的方法，当队列中没有消息时，next() 会无限循环，造成阻塞，等来 MessageQueue 中加入新消息，然后再唤醒；Looper 从队列中取到消息后，会拿到当前 Message的 target 指向的 Handler，并调用 Handler 的 dispatchMessage() 进行分发，进而调用我们重写的 handleMessage 或 者 handleCallback()，主线程的 Looper 在 ActivityThread 中被初始化和开始循环

2. **Handler**：Handler 依附于创建 Handler 的线程，因为它持有当前线程的 Looper。不论是调用 post 方法还是 send 方法，最终都会调用 sendMessageAtTime(Message msg, long uptimeMillis)，然后调用 Handler 的 enqueueMessage(Messagequeue queue, Message msg, long uptimeMillis) 方法，实际调用 MessageQueue 的 enqueueMessage(Message msg, long when) 方法

3. **MessageQueue**：在 Looper 初始化的时候被初始化，维护着消息队列（实际是单链表的）Message mMessages，每个节点就是一个 Message

4. **Message**：作为消息队列中的一个对象，他本身就是一个节点，不需要再用 Node 包装，通过字段 next 连接下一个 Message，target 指向 Handler，进而执行后面的消息分发

5. **ThreadLocal**：在 Looper 的成员变量中创建，在 prepare() 时，会把 Looper 存进 ThreadLocal 中，使用 ThreadLocal 的 set() 或者 get() 方法时，需要传入当前的线程t作为参数，ThreadLocal 是属于线程的。因此 get() 的语义是获得属于当前线程的变量。注意：ThreadLocalMap 的引用由 Thread 唯一持有，意思就是 ThreadLocalMap 跟随 Thread 对象一起被回收。

## 原理图

<img src="../assets/Handler的原理.png" style="zoom:80%;" />


## 链接
[简书：Android消息机制的原理及源码解析](https://www.jianshu.com/p/f10)

[慕课网：Handler发送消息的Delay靠谱吗？](https://coding.imooc.com/lesson/317.html#mid=22311"%3Ehttps://coding.imooc.com/lesson/317.html)

[Android消息机制（讲得不错）](https://www.jianshu.com/p/57a426b8f145)


[Android消息机制2-Handler\(Native层\)](http://gityuan.com/2015/12/27/handler-message-native/)
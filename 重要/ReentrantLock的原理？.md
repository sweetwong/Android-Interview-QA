## 回答

ReentrantLock 是基于**AQS**（**抽象队列同步器**）实现的，AQS 是基于 **CAS**（比较并交换）实现的
AQS 中维护一个同步队列，队列是实现是通过双链表，链表每个节点用它的一个内部类 Node 表示，每个节点保存了 Thread 的信息


## AQS 原理？

AQS 全称为 AbstractQueuedSynchronizer ，它提供了一个 FIFO 队列，是一个用来实现同步锁以及其他涉及到同步功能的核心组件，常见的有：ReentrantLock、CountDownLatch 等。AQS 是一个抽象类，主要是通过继承的方式来使用，**它本身没有实现任何的同步接口，仅仅是定义了同步状态的获取以及释放的方法来提供自定义的同步组件**。

AQS 的实现依赖内部的同步队列，也就是 FIFO 的双向队列，**如果当前线程竞争锁失败，那么 AQS 会把当前线程以及等待状态信息构造成一个 Node 加入到同步队列中，同时再阻塞该线程**。当获取锁的线程释放锁以后，会从队列中唤醒一个阻塞的节点(线程)。

AQS 队列内部维护的是一个 FIFO 的双向链表，这种结构的特点是每个数据结构都有两个指针，分别指向直接的后继节点和直接前驱节点。所以双向链表可以从任意一个节点开始很方便的访问前驱和后继。每个 Node 其实是由线程封装，当线程争抢锁失败后会封装成 Node 加入到 AQS 队列中去

## 链接

[美团：从ReentrantLock的实现看AQS的原理及应用（终极好文）](https://tech.meituan.com/2019/12/05/aqs-theory-and-apply.html)


[思否：深入分析AQS实现原理（终极好文）](https://segmentfault.com/a/1190000017372067)

[博客园：ReentrantLock实现原理深入探究](https://www.cnblogs.com/xrq730/p/4979021.html)

[简书：分析ReentrantLock的实现原理](https://www.jianshu.com/p/fe027772e156)
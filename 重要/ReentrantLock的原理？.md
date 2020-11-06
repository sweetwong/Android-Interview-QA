## 回答要点

- ReentrantLock 是基于**AQS**（**抽象队列同步器**）实现的，AQS 是基于 **LockSupport** 和 **各种 CAS 操作** 实现的
- AQS 中维护了一个双向链表，每个链表的节点都包装了一个线程
- AQS 中维护了一个状态 state，这个 state 是 volatile，各线程通过 CAS 方式去尝试改变 state，如果成功了就代表获取到了锁，如果没成功就插入到队尾
- ReentranLock 公平锁的实现是通过每次在 tryAcquire() 方法中，查看当前线程对应的节点的前驱节点是否是头节点，如果是，则代表当前线程等待的时间最长
- AQS 维护的队列的 head 节点是个虚节点（dummyHead）
- AQS 的等待机制是通过 LockSupport.park() 和 LockSupport.unpark()，之所不使用 object.wait() 和 object.notify() 是因为这两个方法必须要在 synchronized 方法块中才能使用


## AQS 原理？

AQS 全称为 AbstractQueuedSynchronizer ，它提供了一个 FIFO 队列，是一个用来实现同步锁以及其他涉及到同步功能的核心组件，常见的有：ReentrantLock、CountDownLatch 等。AQS 是一个抽象类，主要是通过继承的方式来使用，**它本身没有实现任何的同步接口，仅仅是定义了同步状态的获取以及释放的方法来提供自定义的同步组件**。

AQS 的实现依赖内部的同步队列，也就是 FIFO 的双向队列，**如果当前线程竞争锁失败，那么 AQS 会把当前线程以及等待状态信息构造成一个 Node 加入到同步队列中，同时再阻塞该线程**。当获取锁的线程释放锁以后，会从队列中唤醒一个阻塞的节点(线程)。

AQS 队列内部维护的是一个 FIFO 的双向链表，这种结构的特点是每个数据结构都有两个指针，分别指向直接的后继节点和直接前驱节点。所以双向链表可以从任意一个节点开始很方便的访问前驱和后继。每个 Node 其实是由线程封装，当线程争抢锁失败后会封装成 Node 加入到 AQS 队列中去

## ReentrantLock 如何实现公平锁和非公平锁？

### 非公平锁

```java
        final void lock() {
            // 与公平锁不同，会先立刻尝试获取一次锁
            if (compareAndSetState(0, 1))
                setExclusiveOwnerThread(Thread.currentThread());
            else
                acquire(1);
        }

        protected final boolean tryAcquire(int acquires) {
            return nonfairTryAcquire(acquires);
        }

        final boolean nonfairTryAcquire(int acquires) {
            final Thread current = Thread.currentThread();
            int c = getState();
            if (c == 0) {
                if (compareAndSetState(0, acquires)) {
                    setExclusiveOwnerThread(current);
                    return true;
                }
            }
            else if (current == getExclusiveOwnerThread()) {
                int nextc = c + acquires;
                if (nextc < 0) // overflow
                    throw new Error("Maximum lock count exceeded");
                setState(nextc);
                return true;
            }
            return false;
        }

```

### 公平锁

```java
        final void lock() {
            acquire(1);
        }

        protected final boolean tryAcquire(int acquires) {
            final Thread current = Thread.currentThread();
            int c = getState();
            if (c == 0) {
                // 与非公平锁不同的地方，需要先判断有没有比当前线程等待更久的线程
                // 如果有，就无法获取锁
                if (!hasQueuedPredecessors() &&
                    compareAndSetState(0, acquires)) {
                    setExclusiveOwnerThread(current);
                    return true;
                }
            }
            else if (current == getExclusiveOwnerThread()) {
                int nextc = c + acquires;
                if (nextc < 0)
                    throw new Error("Maximum lock count exceeded");
                setState(nextc);
                return true;
            }
            return false;
        }
```
## 链接

[美团：从ReentrantLock的实现看AQS的原理及应用（终极好文）](https://tech.meituan.com/2019/12/05/aqs-theory-and-apply.html)


[思否：深入分析AQS实现原理（终极好文）](https://segmentfault.com/a/1190000017372067)

[博客园：ReentrantLock实现原理深入探究](https://www.cnblogs.com/xrq730/p/4979021.html)

[简书：分析ReentrantLock的实现原理](https://www.jianshu.com/p/fe027772e156)
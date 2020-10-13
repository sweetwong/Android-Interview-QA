## 区别
|     类     |    ArrayBlockingQueue    | LinkedBlockingQueue |
| :--------: | :----------------------: | :-----------------: |
|  数据结构  |           数组           |        链表         |
|    长度    |           固定           |     可以不固定      |
|  计数变量  |        int count         | AtomicInteger count |
|     锁     |    单锁（一把全局锁）    | 双锁（读锁和写锁）  |
| 锁的公平性 | 可以使用公平锁和非公平锁 |  只能使用非公平锁   |
|    效率    |            低            | 高（因为读写分离）  |

## 问题
#### 为什么ArrayBlockingQueue使用单锁，LinkedBlockingQueue使用双锁？
ABS的实现是基于数组，LBS的实现基于链表。LBS链表头尾节点互不干扰，可以同时读写，提升效率，只要保证count的原子性既可以；而ABS无论读和写都是针对于同一个数组，是一个循环数组，而位置的选择是无法原子化的，因此只能加锁来解决。

## 链接
[Java并发面试常识之LinkedBlockingQueue](https://my.oschina.net/xpbob/blog/995836)

[When to prefer LinkedBlockingQueue over ArrayBlockingQueue?](https://stackoverflow.com/questions/35967792/when-to-prefer-linkedblockingqueue-over-arrayblockingqueue/35975458#:~:text=ArrayBlockingQueue%20can%20be%20created%20with,will%20cost%20you%20in%20throughput.&text=LinkedBlockingQueue%20should%20have%20better%20throughput,the%20head%20and%20the%20tail.)
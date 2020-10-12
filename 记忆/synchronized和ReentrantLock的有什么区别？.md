### 对比图
<img src="../assets/ReentrantLock和synchronized对比图.png" style="zoom: 80%;" />

### 区别

1. Lock是一个接口，而synchronized是Java中的关键字，synchronized是内置的语言实现；

2. synchronized在发生异常时，会自动释放线程占有的锁，因此不会导致死锁现象发生；而Lock在发生异常时，如果没有主动通过unLock()去释放锁，则很可能造成死锁现象，因此使用Lock时需要在finally块中释放锁；
3. Lock可以让等待锁的线程响应中断，通过lock.lockInterruptibly()来实现这个机制，而synchronized却不行，使用synchronized时，等待的线程会一直等待下去，不能够响应中断；
4. 通过Lock可以知道有没有成功获取锁，而synchronized却无法办到。
5. Lock可以提高多个线程进行读操作的效率。

### 链接
[CSDN：ReenTrantLock可重入锁（和synchronized的区别）总结](https://blog.csdn.net/qq838642798/article/details/65441415)

[美团：从ReentrantLock的实现看AQS的原理及应用](https://tech.meituan.com/2019/12/05/aqs-theory-and-apply.html)
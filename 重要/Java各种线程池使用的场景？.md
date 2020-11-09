## Java 开发者手册

> 【强制】线程资源必须通过线程池提供，不允许在应用中自行显式创建线程。

## ThreadPoolExecutor 的 7 个参数（记住）

1. `int corePoolSize`：线程池的基本大小
2. `int maximumPoolSize`：线程池最大线程大小
3. `long keepAliveTime`：线程空闲后的存活时间
4. `TimeUnit unit`：keepAliveTime 的时间单位
5. `BlockingQueue<Runnable> workQueue`：用于存放任务的阻塞队列
   - newFixedThreadPool()：LinkedBlockingQueue
   - newCachedThreadPool()：SynchronousQueue
   - newSingleThreadExecutor()：LinkedBlockingQueue
6. `ThreadFactory threadFactory`：生产线程的工厂
7. `RejectedExecutionHandler handler`：当队列和最大线程池都满了之后的饱和策略

## 线程池的种类

### newCachedThreadPool()

- 底层：返回 ThreadPoolExecutor 实例，corePoolSize 为 0；maximumPoolSize 为 Integer.MAX_VALUE；keepAliveTime 为 60L；unit 为 TimeUnit.SECONDS；workQueue 为 SynchronousQueue（同步队列）
- 通俗：当有新任务到来，则插入到 SynchronousQueue 中，由于 SynchronousQueue 是同步队列，因此会在池中寻找可用线程来执行，若有可以线程则执行，若没有可用线程则创建一个线程来执行该任务；若池中线程空闲时间超过指定大小，则该线程会被销毁。
- 适用：执行很多短期异步的小程序或者负载较轻的服务器

### newFixedThreadPool()

- 底层：返回 ThreadPoolExecutor 实例，接收参数为所设定线程数量 nThread，corePoolSize 为 nThread，maximumPoolSize 为 nThread；keepAliveTime 为 0L；unit 为 TimeUnit.MILLISECONDS；WorkQueue 为 LinkedBlockingQueue 无解阻塞队列
- 通俗：创建可容纳固定数量线程的池子，每隔线程的存活时间是无限的，当池子满了就不在添加线程了；如果池中的所有线程均在繁忙状态，对于新任务会进入阻塞队列中(无界的阻塞队列)
- 适用：执行长期的任务，性能好很多

### newSingleThreadExecutor()

- 底层：FinalizableDelegatedExecutorService 包装的 ThreadPoolExecutor 实例，corePoolSize 为 1；maximumPoolSize 为 1；keepAliveTime 为 0L；unit 为 TimeUnit.MILLISECONDS；workQueue 为 LinkedBlockingQueue 无解阻塞队列
- 通俗：创建只有一个线程的线程池，且线程的存活时间是无限的；当该线程正繁忙时，对于新任务会进入阻塞队列中（无界的阻塞队列）
- 适用：一个任务一个任务执行的场景

### NewScheduledThreadPool()

- 底层：创建 ScheduledThreadPoolExecutor 实例，corePoolSize 为传递来的参数，maximumPoolSize 为 Integer.MAX_VALUE；keepAliveTime 为 0；unit 为 TimeUnit.NANOSECONDS；workQueue 为 DelayedWorkQueue 一个按超时时间升序排序的队列
- 通俗：创建一个固定大小的线程池，线程池内线程存活时间无限制，线程池可以支持定时及周期性任务执行，如果所有线程均处于繁忙状态，对于新任务会进入 DelayedWorkQueue 队列中，这是一种按照超时时间排序的队列结构
- 适用：周期性执行任务的场景

## 线程池主要流程

![img](https://i.loli.net/2019/05/08/5cd1d2ac0936c.jpg)            

## 线程池中定义的状态

- `RUNNING`：自然是运行状态，指可以接受任务执行队列里的任务
- `SHUTDOWN`：指调用了 `shutdown()` 方法，不再接受新任务了，但是队列里的任务得执行完毕。
- `STOP`：指调用了 `shutdownNow()` 方法，不再接受新任务，同时抛弃阻塞队列里的所有任务并中断所有正在执行任务。
- `TIDYING`：所有任务都执行完毕，在调用 `shutdown()/shutdownNow()` 中都会尝试更新为这个状态。
- `TERMINATED`：终止状态，当执行 `terminated()` 后会更新为这个状态。

![img](https://i.loli.net/2019/05/08/5cd1d2aa81655.jpg)

## 链接

[美团：Java线程池实现原理及其在美团业务中的实践](https://tech.meituan.com/2020/04/02/java-pooling-pratice-in-meituan.html)

[如何优雅的使用和理解线程池](https://crossoverjie.top/2018/07/29/java-senior/ThreadPool/)

[线程池的种类，区别和使用场景](https://www.cnblogs.com/sachen/p/7401959.html)
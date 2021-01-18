# Java多线程简介及线程同步

### 一、线程是什么？

线程（thread）是操作系统能够进行运算调度的最小单位，它被包含在进程之中，是进程中的实际运作单位。

### 二、线程状态

一个线程只能处于一种状态，并且这里的线程状态特指 Java 虚拟机的线程状态，不能反映线程在特定操作系统下的状态。

- #### 新建（NEW）

  创建后尚未启动。

- #### 可运行（RUNABLE）

  正在 Java 虚拟机中运行。但是在操作系统层面，它可能处于运行状态，也可能等待资源调度（例如处理器资源），资源调度完成就进入运行状态。所以该状态的可运行是指可以被运行，具体有没有运行要看底层操作系统的资源调度。

- #### 阻塞（BLOCKED）

  请求获取 monitor lock 从而进入 synchronized 函数或者代码块，但是其它线程已经占用了该 monitor lock，所以出于阻塞状态。要结束该状态进入从而 RUNABLE 需要其他线程释放 monitor lock。

- #### 无期限等待（WAITING）

  等待其它线程显式地唤醒。

  阻塞和等待的区别在于，阻塞是被动的，它是在等待获取 monitor lock。而等待是主动的，通过调用 Object.wait() 等方法进入。

  |                  进入方法                  |               退出方法               |
  | :----------------------------------------: | :----------------------------------: |
  | 没有设置 Timeout 参数的 Object.wait() 方法 | Object.notify() / Object.notifyAll() |
  | 没有设置 Timeout 参数的 Thread.join() 方法 |         被调用的线程执行完毕         |
  |          LockSupport.park() 方法           |      LockSupport.unpark(Thread)      |

  

- #### 期限等待（TIMED_WAITING）

  无需等待其它线程显式地唤醒，在一定时间之后会被系统自动唤醒。

  |                 进入方法                 |                    退出方法                     |
  | :--------------------------------------: | :---------------------------------------------: |
  |           Thread.sleep() 方法            |                    时间结束                     |
  | 设置了 Timeout 参数的 Object.wait() 方法 | 时间结束 / Object.notify() / Object.notifyAll() |
  | 设置了 Timeout 参数的 Thread.join() 方法 |         时间结束 / 被调用的线程执行完毕         |
  |       LockSupport.parkNanos() 方法       |           LockSupport.unpark(Thread)            |
  |       LockSupport.parkUntil() 方法       |           LockSupport.unpark(Thread)            |

  调用 Thread.sleep() 方法使线程进入限期等待状态时，常常用“使一个线程睡眠”进行描述。调用 Object.wait() 方法使线程进入限期等待或者无限期等待时，常常用“挂起一个线程”进行描述。睡眠和挂起是用来描述行为，而阻塞和等待用来描述状态。

- #### 死亡（TERMINATED）

  可以是线程结束任务之后自己结束，或者产生了异常而结束。

### 三、使用线程

实现 Runnable 和 Callable 接口的类只能当做一个可以在线程中运行的任务，不是真正意义上的线程，因此最后还需要通过 Thread 来调用。可以理解为任务是通过线程驱动从而执行的。

- #### 实现 Runnable 接口

  ```java
  public class MyRunnable implements Runnable {
      @Override
      public void run() {
          // ...
      }
  }
  ```

  使用 Runnable 实例再创建一个 Thread 实例，然后调用 Thread 实例的 start() 方法来启动线程。

  ```java
  public static void main(String[] args) {
      MyRunnable instance = new MyRunnable();
      Thread thread = new Thread(instance);
      thread.start();
  }
  ```

- #### 实现 Callable 接口

  与 Runnable 相比，Callable 可以有返回值，返回值通过 FutureTask 进行封装。

  在等待返回的时候，线程处于阻塞状态。

  ```java
  public class MyCallable implements Callable<Integer> {
      public Integer call() {
          return 123;
      }
  }
  ```

  ```java
  public static void main(String[] args) throws ExecutionException, InterruptedException {
      MyCallable mc = new MyCallable();
      FutureTask<Integer> ft = new FutureTask<>(mc);
      Thread thread = new Thread(ft);
      thread.start();
      System.out.println(ft.get());
  }
  ```

- #### 继承 Thread 类

  同样也是需要实现 run() 方法，因为 Thread 类也实现了 Runable 接口。

  当调用 start() 方法启动一个线程时，虚拟机会将该线程放入就绪队列中等待被调度，当一个线程被调度时会执行该线程的 run() 方法。

  ```java
  public class MyThread extends Thread {
      public void run() {
          // ...
      }
  }
  ```

  ```java
  public static void main(String[] args) {
      MyThread mt = new MyThread();
      mt.start();
  }
  ```

- #### 实现接口 VS 继承 Thread

  实现接口会更好一些，因为：

  - Java 不支持多重继承，因此继承了 Thread 类就无法继承其它类，但是可以实现多个接口；
  - 类可能只要求可执行就行，继承整个 Thread 类开销过大。

### 四、基础线程机制

- #### Executor

  Executor 管理多个异步任务的执行，而无需程序员显式地管理线程的生命周期。这里的异步是指多个任务的执行互不干扰，不需要进行同步操作。

  主要有三种 Executor：

  - CachedThreadPool：一个任务创建一个线程；

  - FixedThreadPool：所有任务只能使用固定大小的线程；

  - SingleThreadExecutor：相当于大小为 1 的 FixedThreadPool。

    ```java
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new MyRunnable());
        }
        executorService.shutdown();
    }
    ```

- #### Daemon

  守护线程是程序运行时在后台提供服务的线程，不属于程序中不可或缺的部分。

  当所有非守护线程结束时，程序也就终止，同时会杀死所有守护线程。

  main() 属于非守护线程。

  在线程启动之前使用 setDaemon() 方法可以将一个线程设置为守护线程。

  ```java
  public static void main(String[] args) {
      Thread thread = new Thread(new MyRunnable());
      thread.setDaemon(true);
  }
  ```

- #### sleep()

  Thread.sleep(millisec) 方法会休眠当前正在执行的线程，millisec 单位为毫秒。

  sleep() 可能会抛出 InterruptedException，因为异常不能跨线程传播回 main() 中，因此必须在本地进行处理。线程中抛出的其它异常也同样需要在本地进行处理。

  ```java
  public void run() {
      try {
          Thread.sleep(3000);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
  }
  ```

- #### yield()

  对静态方法 Thread.yield() 的调用声明了当前线程已经完成了生命周期中最重要的部分，可以切换给其它线程来执行。该方法只是对线程调度器的一个建议，而且也只是建议具有相同优先级的其它线程可以运行。

  ```java
  public void run() {
      Thread.yield();
  }
  ```

### 五、中断

一个线程执行完毕之后会自动结束，如果在运行过程中发生异常也会提前结束。

- #### stop()

  暴力的停掉线程，由于结果不可预期所以被废弃。

- #### InterruptedException

  通过调用一个线程的 interrupt() 来中断该线程，如果该线程处于阻塞、限期等待或者无限期等待状态，那么就会抛出 InterruptedException，从而提前结束当前状态，进入下个任务。但是不能中断 I/O 阻塞和 synchronized 锁阻塞。

  对于以下代码，在 main() 中启动一个线程之后再中断它，由于线程中调用了 Thread.sleep() 方法，因此会抛出一个 InterruptedException，从而提前结束线程，不执行之后的语句。

  ```java
  public class InterruptExample {
  
      private static class MyThread1 extends Thread {
          @Override
          public void run() {
              try {
                  Thread.sleep(2000);
                  System.out.println("Thread run");
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
      }
  }
  ```

  ```java
  public static void main(String[] args) throws InterruptedException {
      Thread thread1 = new MyThread1();
      thread1.start();
      thread1.interrupt();
      System.out.println("Main run");
  }
  ```

  ```java
  Main run
  java.lang.InterruptedException: sleep interrupted
      at java.lang.Thread.sleep(Native Method)
      at InterruptExample.lambda$main$0(InterruptExample.java:5)
      at InterruptExample$$Lambda$1/713338599.run(Unknown Source)
      at java.lang.Thread.run(Thread.java:745)
  ```

  ```java
  Thread end
  ```

- #### interrupted()

  如果一个线程的 run() 方法执行一个无限循环，并且没有执行 sleep() 等会抛出 InterruptedException 的操作，那么调用线程的 interrupt() 方法就无法使线程提前结束。

  但是调用 interrupt() 方法会设置线程的中断标记，此时调用 interrupted() 方法会返回 true。因此可以在循环体中使用 interrupted() 方法来判断线程是否处于中断状态，从而提前结束线程。

  **如果抛出 InterruptedException ，那么中断标记会重新恢复为 false。**

  ```java
  public class InterruptExample {
  
      private static class MyThread2 extends Thread {
          @Override
          public void run() {
              while (!interrupted()) {
                  // ..
              }
              System.out.println("Thread end");
          }
      }
  }
  ```

  ```
  public static void main(String[] args) throws InterruptedException {
      Thread thread2 = new MyThread2();
      thread2.start();
      thread2.interrupt();
  }
  ```

  ```
  Thread end
  ```

  

- #### Executor 的中断操作

  调用 Executor 的 shutdown() 方法会等待线程都执行完毕之后再关闭，但是如果调用的是 shutdownNow() 方法，则相当于调用每个线程的 interrupt() 方法。

  以下使用 Lambda 创建线程，相当于创建了一个匿名内部线程。

  ```java
  public static void main(String[] args) {
      ExecutorService executorService = Executors.newCachedThreadPool();
      executorService.execute(() -> {
          try {
              Thread.sleep(2000);
              System.out.println("Thread run");
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
      });
      executorService.shutdownNow();
      System.out.println("Main run");
  }
  ```

  ```
  Main run
  java.lang.InterruptedException: sleep interrupted
      at java.lang.Thread.sleep(Native Method)
      at ExecutorInterruptExample.lambda$main$0(ExecutorInterruptExample.java:9)
      at ExecutorInterruptExample$$Lambda$1/1160460865.run(Unknown Source)
      at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
      at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
      at java.lang.Thread.run(Thread.java:745)
  ```

  如果只想中断 Executor 中的一个线程，可以通过使用 submit() 方法来提交一个线程，它会返回一个 Future<?> 对象，通过调用该对象的 cancel(true) 方法就可以中断线程。

  ```java
  Future<?> future = executorService.submit(() -> {
      // ..
  });
  future.cancel(true);
  ```

### 六、互斥同步

Java 提供了两种锁机制来控制多个线程对共享资源的互斥访问，第一个是 JVM 实现的 synchronized，而另一个是 JDK 实现的 ReentrantLock。

- #### synchronized

  - **Monitor**

    同步锁的观察者，由它来决定当前访问线程能不能拿到同步代码块的决定权。

    只有多个线程同时访问同一个 Monitor 观察的代码块时才会触发同步机制。

    常见写法有：

    - 同步一个方法，Monitor 为调用方法的对象。

      ```java
      public synchronized void func () {
          // ...
      }
      ```

    - 同步一个代码块，Monitor 为小括号里面的 Object 对象。

      ```java
      private final Object lock = new Object();
      public void func() {
          synchronized (lock) {
              // ...
          }
      }
      ```

    - 同步一个静态方法，Monitor 为类的 Class 对象。

      ```
      public synchronized static void fun() {
          // ...
      }
      ```

  - **内存模型分析**

    当线程想修改内存中的变量的时候，首先会 copy 变量到自己的 CPU 高速内存中，修改完之后在写入内存（不确定什么时候写入）；所以在多个线程同时修改一个变量时，就会出现 “跟预期不一样” 的情况。synchronized 可以简单的理解为取消了这个机制，让线程同时修改内存中的同一个数据。

  - #### volatile

    由于 JVM 设计原因，一些在我们看来结果 “非黑即白” 的单条语句，在实际中是有 “中间态” 的，volatile 就是强制让这些 “中间态” 不存在，让操作具有 “原子性”。但是 volatile 只作用于基本数据类型和对象引用的赋值。从内存模型上来讲，就是取消了内存的 copy。

  - #### join()

    在线程中调用另一个线程的 join() 方法，会将当前线程挂起，而不是忙等待，直到目标线程结束。

    对于以下代码，虽然 b 线程先启动，但是因为在 b 线程中调用了 a 线程的 join() 方法，b 线程会等待 a 线程结束才继续执行，因此最后能够保证 a 线程的输出先于 b 线程的输出。

    ```java
    public class JoinExample {
    
        private class A extends Thread {
            @Override
            public void run() {
                System.out.println("A");
            }
        }
    
        private class B extends Thread {
    
            private A a;
    
            B(A a) {
                this.a = a;
            }
    
            @Override
            public void run() {
                try {
                    a.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("B");
            }
        }
    
        public void test() {
            A a = new A();
            B b = new B(a);
            b.start();
            a.start();
        }
    }
    ```

    ```java
    public static void main(String[] args) {
        JoinExample example = new JoinExample();
        example.test();
    }
    ```

    ```java
    A
    B
    ```

    

  - #### wait() notify() notifyAll()

    调用 wait() 使得线程等待某个条件满足，线程在等待时会被挂起，当其他线程的运行使得这个条件满足时，其它线程会调用 notify() 或者 notifyAll() 来唤醒挂起的线程。

    它们都属于 Object 的一部分，而不属于 Thread。

    只能用在同步方法或者同步控制块中使用，否则会在运行时抛出 IllegalMonitorStateException。

    使用 wait() 挂起期间，线程会释放锁。这是因为，如果没有释放锁，那么其它线程就无法进入对象的同步方法或者同步控制块中，那么就无法执行 notify() 或者 notifyAll() 来唤醒挂起的线程，造成死锁。

    ```java
    public class WaitNotifyExample {
    
        public synchronized void before() {
            System.out.println("before");
            notifyAll();
        }
    
        public synchronized void after() {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("after");
        }
    }
    ```

    ```java
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        WaitNotifyExample example = new WaitNotifyExample();
        executorService.execute(() -> example.after());
        executorService.execute(() -> example.before());
    }
    ```

    ```java
    before
    after
    ```

    **wait() 和 sleep() 的区别**

    - wait() 是 Object 的方法，而 sleep() 是 Thread 的静态方法；
    - wait() 会释放锁，sleep() 不会。

- #### ReentrantLock

  ReentrantLock 是 java.util.concurrent（J.U.C）包中的锁。

  ```java
  public class LockExample {
  
      private Lock lock = new ReentrantLock();
  
      public void func() {
          lock.lock();
          try {
              for (int i = 0; i < 10; i++) {
                  System.out.print(i + " ");
              }
          } finally {
              lock.unlock(); // 确保释放锁，从而避免发生死锁。
          }
      }
  }
  ```

  ```java
  public static void main(String[] args) {
      LockExample lockExample = new LockExample();
      ExecutorService executorService = Executors.newCachedThreadPool();
      executorService.execute(() -> lockExample.func());
      executorService.execute(() -> lockExample.func());
  }
  ```

  ```java
  0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9
  ```

### 七、J.U.C - AQS

java.util.concurrent（J.U.C）大大提高了并发性能，AQS 被认为是 J.U.C 的核心。

- #### CountDownLatch

  用来控制一个或者多个线程等待多个线程。

  维护了一个计数器 cnt，每次调用 countDown() 方法会让计数器的值减 1，减到 0 的时候，那些因为调用 await() 方法而在等待的线程就会被唤醒。

  ```java
  public class CountdownLatchExample {
  
      public static void main(String[] args) throws InterruptedException {
          final int totalThread = 10;
          CountDownLatch countDownLatch = new CountDownLatch(totalThread);
          ExecutorService executorService = Executors.newCachedThreadPool();
          for (int i = 0; i < totalThread; i++) {
              executorService.execute(() -> {
                  System.out.print("run..");
                  countDownLatch.countDown();
              });
          }
          countDownLatch.await();
          System.out.println("end");
          executorService.shutdown();
      }
  }
  ```

  ```java
  run..run..run..run..run..run..run..run..run..run..end
  ```

- #### ReentrantReadWriteLock

  对于一个变量来讲，多个线程同时读是不需要加锁的，多个线程写是要加锁的，如果能明确区分读和写操作，则可以把他们分别加锁。

  ```java
  public class ReentrantReadWriteLockExample{
      ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
      ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
      ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
  
      int x = 0;
  
      public void count(){
          writeLock.lock();
          try {
              x++;
          } catch (Exception e) {
              e.printStackTrace();
          } finally {
              writeLock.unlock();
          }
      }
  
      public void print(int time){
          readLock.lock();
          try {
              for (int i = 0; i < time; i++){
                  System.out.print(x + " ");
              }
              System.out.println("end");
          } catch (Exception e) {
              e.printStackTrace();
          } finally {
              readLock.unlock();
          }
      }
  }
  ```

  ```java
  public static void main(String[] args) {
          final ReentrantReadWriteLockExample example = new ReentrantReadWriteLockExample();
          ExecutorService executorService = Executors.newCachedThreadPool();
          int threadSize = 500;
          final CountDownLatch countDownLatch = new CountDownLatch(threadSize);
          for (int i = 0; i < threadSize; i++) {
              executorService.execute(() -> {
                  example.count();
                  countDownLatch.countDown();
              });
          }
          try {
              countDownLatch.await();
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
          example.print(5);
          executorService.shutdown();
  }
  ```
  
  ```java
  500 500 500 500 500 end
  ```
  
- #### CyclicBarrier

  用来控制多个线程互相等待，只有当多个线程都到达时，这些线程才会继续执行。

  和 CountdownLatch 相似，都是通过维护计数器来实现的。线程执行 await() 方法之后计数器会减 1，并进行等待，直到计数器为 0，所有调用 await() 方法而在等待的线程才能继续执行。

  CyclicBarrier 和 CountdownLatch 的一个区别是，CyclicBarrier 的计数器通过调用 reset() 方法可以循环使用，所以它才叫做循环屏障。

  CyclicBarrier 有两个构造函数，其中 parties 指示计数器的初始值，barrierAction 在所有线程都到达屏障的时候会执行一次。

  ```java
  public CyclicBarrier(int parties, Runnable barrierAction) {
      if (parties <= 0) throw new IllegalArgumentException();
      this.parties = parties;
      this.count = parties;
      this.barrierCommand = barrierAction;
  }
  
  public CyclicBarrier(int parties) {
      this(parties, null);
  }
  ```

  ```java
  public class CyclicBarrierExample {
  
      public static void main(String[] args) {
          final int totalThread = 10;
          CyclicBarrier cyclicBarrier = new CyclicBarrier(totalThread);
          ExecutorService executorService = Executors.newCachedThreadPool();
          for (int i = 0; i < totalThread; i++) {
              executorService.execute(() -> {
                  System.out.print("before..");
                  try {
                      cyclicBarrier.await();
                  } catch (InterruptedException | BrokenBarrierException e) {
                      e.printStackTrace();
                  }
                  System.out.print("after..");
              });
          }
          executorService.shutdown();
      }
  }
  ```

  ```java
  before..before..before..before..before..before..before..before..before..before..after..after..after..after..after..after..after..after..after..after..
  ```

- #### Atomic家族

  AtomicInteger、AtomicLong、AtomicLong等。

  这些都能API的提供能很方便的保证在多线程的情况下数据的原子性，同时他们也比 synchronized 更加的轻量级。

  ```java
  public class AtomicExample {
      private AtomicInteger cnt = new AtomicInteger();
  
      public void add() {
          cnt.incrementAndGet();
      }
  
      public int get() {
          return cnt.get();
      }
  }
  ```

  ```java
  public static void main(String[] args) throws InterruptedException {
      final int threadSize = 1000;
      AtomicExample example = new AtomicExample(); // 只修改这条语句
      final CountDownLatch countDownLatch = new CountDownLatch(threadSize);
      ExecutorService executorService = Executors.newCachedThreadPool();
      for (int i = 0; i < threadSize; i++) {
          executorService.execute(() -> {
              example.add();
              countDownLatch.countDown();
          });
      }
      countDownLatch.await();
      executorService.shutdown();
      System.out.println(example.get());
  }
  ```

  ```java
  1000
  ```

  

- #### BlockingQueue

  java.util.concurrent.BlockingQueue 接口有以下阻塞队列的实现

  - **FIFO 队列** ：LinkedBlockingQueue、ArrayBlockingQueue（固定长度）
  - **优先级队列** ：PriorityBlockingQueue

  提供了阻塞的 take() 和 put() 方法：如果队列为空 take() 将阻塞，直到队列中有内容；如果队列为满 put() 将阻塞，直到队列有空闲位置。

  **使用 BlockingQueue 实现生产者消费者问题**

  ```java
  public class ProducerConsumer {
  
      private static BlockingQueue<String> queue = new ArrayBlockingQueue<>(5);
  
      private static class Producer extends Thread {
          @Override
          public void run() {
              try {
                  queue.put("product");
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              System.out.print("produce..");
          }
      }
  
      private static class Consumer extends Thread {
  
          @Override
          public void run() {
              try {
                  String product = queue.take();
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              System.out.print("consume..");
          }
      }
  }
  ```

  ```java
  public static void main(String[] args) {
      for (int i = 0; i < 2; i++) {
          Producer producer = new Producer();
          producer.start();
      }
      for (int i = 0; i < 5; i++) {
          Consumer consumer = new Consumer();
          consumer.start();
      }
      for (int i = 0; i < 3; i++) {
          Producer producer = new Producer();
          producer.start();
      }
  }
  ```

  ```java
  produce..produce..consume..consume..produce..consume..produce..consume..produce..consume..
  ```

### 八、锁优化

这里的锁优化主要是指 JVM 对 synchronized 的优化。

- #### 自旋锁

  互斥同步进入阻塞状态的开销都很大，应该尽量避免。在许多应用中，共享数据的锁定状态只会持续很短的一段时间。自旋锁的思想是让一个线程在请求一个共享数据的锁时执行忙循环（自旋）一段时间，如果在这段时间内能获得锁，就可以避免进入阻塞状态。

  自旋锁虽然能避免进入阻塞状态从而减少开销，但是它需要进行忙循环操作占用 CPU 时间，它只适用于共享数据的锁定状态很短的场景。

  在 JDK 1.6 中引入了自适应的自旋锁。自适应意味着自旋的次数不再固定了，而是由前一次在同一个锁上的自旋次数及锁的拥有者的状态来决定。

- #### 锁消除

  锁消除是指对于被检测出不可能存在竞争的共享数据的锁进行消除。

  锁消除主要是通过逃逸分析来支持，如果堆上的共享数据不可能逃逸出去被其它线程访问到，那么就可以把它们当成私有数据对待，也就可以将它们的锁进行消除。

  对于一些看起来没有加锁的代码，其实隐式的加了很多锁。例如下面的字符串拼接代码就隐式加了锁：

  ```java
  public static String concatString(String s1, String s2, String s3) {
      return s1 + s2 + s3;
  }
  ```

  String 是一个不可变的类，编译器会对 String 的拼接自动优化。在 JDK 1.5 之前，会转化为 StringBuffer 对象的连续 append() 操作：

  ```java
  public static String concatString(String s1, String s2, String s3) {
      StringBuffer sb = new StringBuffer();
      sb.append(s1);
      sb.append(s2);
      sb.append(s3);
      return sb.toString();
  }
  ```

  每个 append() 方法中都有一个同步块。虚拟机观察变量 sb，很快就会发现它的动态作用域被限制在 concatString() 方法内部。也就是说，sb 的所有引用永远不会逃逸到 concatString() 方法之外，其他线程无法访问到它，因此可以进行消除。

### 九、多线程开发良好的实践

- 给线程起个有意义的名字，这样可以方便找 Bug。
- 缩小同步范围，从而减少锁争用。例如对于 synchronized，应该尽量使用同步块而不是同步方法。
- 多用同步工具少用 wait() 和 notify()。首先，CountDownLatch, CyclicBarrier 这些同步类简化了编码操作，而用 wait() 和 notify() 很难实现复杂控制流；其次，这些同步类是由最好的企业编写和维护，在后续的 JDK 中还会不断优化和完善。
- 使用 BlockingQueue 实现生产者消费者问题。
- 多用并发集合少用同步集合，例如应该使用 ConcurrentHashMap 而不是 Hashtable。
- 使用本地变量和不可变类来保证线程安全。
- 使用线程池而不是直接创建线程，这是因为创建线程代价很高，线程池可以有效地利用有限的线程来启动任务。






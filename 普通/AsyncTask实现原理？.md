## 什么是 AsyncTask？
1. 是一种轻量级的异步任务类;

2. 是一个封装了**线程池**和 **Handler** 的异步框架；

3. 使用它可以更加方便的执行后台任务以及在主线程访问 UI，但他不适合进行特别耗时的后台任务；

## 多个 AsyncTask 任务是串行还是并行？
从 Android 1.6 到 2.3 AsyncTask 是并行的，即上面我们提到的有 5 个核心线程的线程池（ThreadPoolExecutor）负责调度任务。从 Android 3.0 开始，Android 团队又把 AsyncTask 改成了串行，默认的 Executor 被指定为 SERIAL_EXECUTOR。

## AsyncTask 容易引发的 Activity 内存泄露
如果 AsyncTask 被声明为 Activity 的**非静态的内部类**，那么 AsyncTask 会保留一个对创建了 AsyncTask 的 Activity 的引用。如果 Activity 已经被销毁，AsyncTask 的后台线程还在执行，它将继续在内存里保留这个引用，导致 Activity 无法被回收，引起内存泄露。

## 如何解决内存泄漏？
1. AsyncTask 内部持有外部 Activity 的弱引用。

2. AsyncTask 改为静态内部类。

3. AsyncTask.cancel()。

## 你在项目中，会用什么方案来替换 AsyncTask 呢？

1. java.util.concurrent
2. Kotlin Coroutines
2. RxJava


## 链接
[CSDN：Android面试一天一题（13 Day: AsyncTask）](https://www.jianshu.com/p/c925b3ea1444)

[CSDN：AsyncTask面试知识小结](https://blog.csdn.net/lingguiqin/article/details/79184356)

[CSDN：郭霖：Android AsyncTask完全解析，带你从源码的角度彻底理解（好文章）](https://blog.csdn.net/guolin_blog/article/details/11711405)
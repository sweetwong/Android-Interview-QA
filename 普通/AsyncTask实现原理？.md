## 什么是AsyncTask？
1. 是一种轻量级的异步任务类;

2. 是一个封装了**线程池**和**Handler**的异步框架；

3. 使用它可以更加方便的执行后台任务以及在主线程访问UI，但他不适合进行特别耗时的后台任务；

## 多个AsyncTask任务是串行还是并行？
从Android 1.6到2.3(Gingerbread) AsyncTask是并行的，即上面我们提到的有5个核心线程的线程池（ThreadPoolExecutor）负责调度任务。从Android 3.0开始，Android团队又把AsyncTask改成了串行，默认的Executor被指定为SERIAL_EXECUTOR。

## AsyncTask容易引发的Activity内存泄露
如果AsyncTask被声明为Activity的**非静态的内部类**，那么AsyncTask会保留一个对创建了AsyncTask的Activity的引用。如果Activity已经被销毁，AsyncTask的后台线程还在执行，它将继续在内存里保留这个引用，导致Activity无法被回收，引起内存泄露。

## 如何解决内存泄漏？
1. AsyncTask内部持有外部Activity的弱引用。

2. AsyncTask改为静态内部类。

3. AsyncTask.cancel()。

## 你在项目中，会用什么方案来替换AsyncTask呢？

1. java.util.concurrent
2. Kotlin Coroutines
2. RxJava


## 链接
[CSDN：Android面试一天一题（13 Day: AsyncTask）](https://www.jianshu.com/p/c925b3ea1444)


[CSDN：AsyncTask面试知识小结](https://blog.csdn.net/lingguiqin/article/details/79184356)
# 背景

我为什么要写这个项目？作为一个 Android 开发工程师，我觉得网上最不缺的就是学习文章，特别是我们还是用 Java 的，隔壁 Java 后端的干货文章更是一堆一堆的，因此我之前也整理了大批大批的学习文章到我的印象笔记，觉得我能全部吃透。但是后面我遇到一个问题，有一次我去腾讯面试的时候，我发现我还是很多不会回答，即使我之前已经看过相关的文章了。

我发现是因为：**我没有总结出重点！**

大家都知道，面试的时候，面试官提出一个问题，你回答的往往是很简短的，而网上的长篇大论，并不能帮助你做出最好的回答，甚至有可能最基本的回答都不能实现。因此我开了这个项目，是**面向问题**的。

当提出一个问题我们如何做到：

- 最简短
- 最深入
- 回答出最重要的关键字

> 举个例子，最常被问到的 `Handler 的原理？`，我们应该回答出以下几个要点
>
> 1. Looper、Handler、MessageQueue、Message 四个角色的关系，以及它们比较重要的方法
> 2. ThreadLocal 在这之中起到的作用
> 3. 原生的 MessageQueue 和 Looper，被创建的时机，以及起到的作用
> 4. epoll 机制在原生 Looper 起到的作用

可以看到，并不需要长篇大论几百个字，列举回答要点即可

# 排版

笔记内容按照 [中文文案排版指北](https://github.com/sparanoid/chinese-copywriting-guidelines/blob/master/README.zh-CN.md) 进行排版，以保证内容的可读性。

# 问题

## 重要

[Activity的四种启动模式和常用的Flags？](重要/Activity的四种启动模式和常用的Flags？.md)

[Android中使用了哪些设计模式？实际项目中用到哪些？](重要/Android中使用了哪些设计模式？实际项目中用到哪些？.md)

[AsyncTask实现原理？](重要/AsyncTask实现原理？.md)

[Binder的优点？Binder的原理？Binder的通讯模型？](重要/Binder的优点？Binder的原理？Binder的通讯模型？.md)

[ClassLoader类加载机制？](重要/ClassLoader类加载机制？.md)

[Handler机制与底层实现？](重要/Handler机制与底层实现？.md)

[HashMap的原理？](重要/HashMap的原理？.md)

[IdleHandler的原理？](重要/IdleHandler的原理？.md)

[Java各种线程池使用的场景？](重要/Java各种线程池使用的场景？.md)

[Java的String可以有多长？](重要/Java的String可以有多长？.md)

[Java的泛型实现机制是怎样的？什么是类型擦除？](重要/Java的泛型实现机制是怎样的？什么是类型擦除？.md)

[MVC、MVP、MVVM的优缺点？](重要/MVC、MVP、MVVM的优缺点？.md)

[OkHttp的原理？](重要/OkHttp的原理？.md)

[Parcelable和Serializable的区别？为什么Parcelable高效？](重要/Parcelable和Serializable的区别？为什么Parcelable高效？.md)

[ReentrantLock的原理？](重要/ReentrantLock的原理？.md)

[Retrofit实现原理？](重要/Retrofit实现原理？.md)

[synchronized的原理？](重要/synchronized的原理？.md)

[ThreadLocal原理？](重要/ThreadLocal原理？.md)

[volatile的原理？](重要/volatile的原理？.md)

[主题切换的方案？](重要/主题切换的方案？.md)

[什么是强、软、弱、虚引用以及它们之间的区别？](重要/什么是强、软、弱、虚引用以及它们之间的区别？.md)

[做了哪些内存优化？](重要/做了哪些内存优化？.md)

[做了哪些卡顿优化？](重要/做了哪些卡顿优化？.md)

[做了哪些启动优化？](重要/做了哪些启动优化？.md)

[做了哪些布局优化？](重要/做了哪些布局优化？.md)

[如何理解Java的三大特性封装、继承和多态？](重要/如何理解Java的三大特性封装、继承和多态？.md)

[性能优化的种类有哪些？](重要/性能优化的种类有哪些？.md)

[描述下Java内存模型？](重要/描述下Java内存模型？.md)

[知道哪些单例模式？](重要/知道哪些单例模式？.md)

[知道哪些排序算法？都有什么特性？](重要/知道哪些排序算法？都有什么特性？.md)

[简单讲一下Android的Touch事件分发机制？](重要/简单讲一下Android的Touch事件分发机制？.md)

[简述下热修复和插件化的原理？](重要/简述下热修复和插件化的原理？.md)

[自定义View的过程？](重要/自定义View的过程？.md)

[讲一下View的绘制流程？View的测量、布局、绘制原理](重要/讲一下View的绘制流程？View的测量、布局、绘制原理.md)

[设计模式的6大原则是什么？](重要/设计模式的6大原则是什么？.md)

## 记忆

[Android开发有哪些节约内存的编码习惯？](记忆/Android开发有哪些节约内存的编码习惯？.md)

[Android每个版本有什么主要的新特征？](记忆/Android每个版本有什么主要的新特征？.md)

[ANR是怎么回事？如何定位ANR？](记忆/ANR是怎么回事？如何定位ANR？.md)

[GET与POST的区别？](记忆/GET与POST的区别？.md)

[HTTP1.0与HTTP1.1与HTTP2.0有什么区别？](记忆/HTTP1.0与HTTP1.1与HTTP2.0有什么区别？.md)

[HTTPS的原理？HTTP和HTTPS的区别？](记忆/HTTPS的原理？HTTP和HTTPS的区别？.md)

[Java反射为什么比较慢？](记忆/Java反射为什么比较慢？.md)

[Java有哪些锁？](记忆/Java有哪些锁？.md)

[Java锁有哪几种状态？](记忆/Java锁有哪几种状态？.md)

[ListView和RecyclerView的区别？](记忆/ListView和RecyclerView的区别？.md)

[ListView和RecyclerView的缓存机制？](记忆/ListView和RecyclerView的缓存机制？.md)

[RecyclerView怎么进行性能优化](记忆/RecyclerView怎么进行性能优化.md)

[String为什么要设计成不可变的？](记忆/String为什么要设计成不可变的？.md)

[synchronized和ReentrantLock的有什么区别？](记忆/synchronized和ReentrantLock的有什么区别？.md)

[TCPIP四层协议？](记忆/TCPIP四层协议？.md)

[如何保证线程安全？](记忆/如何保证线程安全？.md)

[如何避免OOM的产生？](记忆/如何避免OOM的产生？.md)

[常用的数据结构有哪些？](记忆/常用的数据结构有哪些？.md)

[常见的内存泄漏的场景？](记忆/常见的内存泄漏的场景？.md)

[报文、报文段、分组、包、数据报、帧、数据流的概念区别？](记忆/报文、报文段、分组、包、数据报、帧、数据流的概念区别？.md)

[有哪些多线程开发良好的实践？](记忆/有哪些多线程开发良好的实践？.md)

[红黑树特性？](记忆/红黑树特性？.md)

[计算机网路-传输层问题总结](记忆/计算机网路-传输层问题总结.md)

[计算机网路-网络层问题总结](记忆/计算机网路-网络层问题总结.md)

## 源码

[Activity启动流程？](源码/Activity启动流程？.md)

[Activity的显示原理？](源码/Activity的显示原理？.md)

[AIDL的oneway机制？](源码/AIDL的oneway机制？.md)

[Android系统启动流程？](源码/Android系统启动流程？.md)

[Binder的transact流程？](源码/Binder的transact流程？.md)

[Framework中有什么你觉得设计很巧妙的地方？请举例说明](源码/Framework中有什么你觉得设计很巧妙的地方？请举例说明.md)

[Surface传递的原理？](源码/Surface传递的原理？.md)

[SystemServer启动流程？](源码/SystemServer启动流程？.md)

[如何跨进程传递大的Bitmap？](源码/如何跨进程传递大的Bitmap？.md)

[点击Launcher图标打开app的过程？](源码/点击Launcher图标打开app的过程？.md)

[说说动态广播的注册和收发原理？](源码/说说动态广播的注册和收发原理？.md)

## 普通

[Activity的创建步骤](普通/Activity的创建步骤.md)

[AMS和WMS的主要功能是什么？](普通/AMS和WMS的主要功能是什么？.md)

[Android和JS交互有哪些方式？](普通/Android和JS交互有哪些方式？.md)

[Android的IPC有哪些方式？并比较他们的优劣](普通/Android的IPC有哪些方式？并比较他们的优劣.md)

[Android的签名机制？](普通/Android的签名机制？.md)

[Android进程的类型？](普通/Android进程的类型？.md)

[ArrayBlockingQueue和LinkedBlockingQueue的区别？](普通/ArrayBlockingQueue和LinkedBlockingQueue的区别？.md)

[ArrayMap的原理？](普通/ArrayMap的原理？.md)

[ART和Dalvik的区别？](普通/ART和Dalvik的区别？.md)

[ButterKnife的原理？](普通/ButterKnife的原理？.md)

[CAS实现原子操作会出现什么问题？](普通/CAS实现原子操作会出现什么问题？.md)

[IntentService和Service有什么区别？](普通/IntentService和Service有什么区别？.md)

[Java内存回收算法有哪些？分别有什么优缺点？](普通/Java内存回收算法有哪些？分别有什么优缺点？.md)

[Java匿名内部类有哪些限制？](普通/Java匿名内部类有哪些限制？.md)

[Java基本数据类型有哪些？](普通/Java基本数据类型有哪些？.md)

[Java的引用和C指针有什么区别？](普通/Java的引用和C指针有什么区别？.md)

[JUC用过哪些类？](普通/JUC用过哪些类？.md)

[JVM怎么判断对象是否已死？](普通/JVM怎么判断对象是否已死？.md)

[LinearLayout和RelativeLayout性能对比？](普通/LinearLayout和RelativeLayout性能对比？.md)

[Messenger和AIDL的区别？](普通/Messenger和AIDL的区别？.md)

[POST请求有哪些形式？](普通/POST请求有哪些形式？.md)

[requestLayout，invalidate，postInvalidate区别与联系？](普通/requestLayout，invalidate，postInvalidate区别与联系？.md)

[sleep和wait的区别？](普通/sleep和wait的区别？.md)

[SparseArray的原理？](普通/SparseArray的原理？.md)

[String、StringBuffer和StringBuilder的区别？](普通/String、StringBuffer和StringBuilder的区别？.md)

[SurfaceView和普通View的区别？](普通/SurfaceView和普通View的区别？.md)

[View的onAttachedToWindow，onDetachedFromWindow调用时机，使用场景是什么？](普通/View的onAttachedToWindow，onDetachedFromWindow调用时机，使用场景是什么？.md)

[为什么wait需要搭配synchronized关键字使用？](普通/为什么wait需要搭配synchronized关键字使用？.md)

[什么是MeasureSpec？](普通/什么是MeasureSpec？.md)

[什么是元编程？在Android中它有哪些应用场景？](普通/什么是元编程？在Android中它有哪些应用场景？.md)

[什么是原子性、有序性、可见性？](普通/什么是原子性、有序性、可见性？.md)

[使用过什么图片加载库，Glide的源码设计哪里很微妙？](普通/使用过什么图片加载库，Glide的源码设计哪里很微妙？.md)

[使用过哪些动画？](普通/使用过哪些动画？.md)

[创建线程有哪些方式？](普通/创建线程有哪些方式？.md)

[句柄是什么？](普通/句柄是什么？.md)

[后台Servce进程保活方案？](普通/后台Servce进程保活方案？.md)

[如何停止一个线程？](普通/如何停止一个线程？.md)

[如何获取Android设备唯一ID？](普通/如何获取Android设备唯一ID？.md)

[如何规避Android-P对访问私有API的限制？](普通/如何规避Android-P对访问私有API的限制？.md)

[如何跨App启动Activity？](普通/如何跨App启动Activity？.md)

[对象创建的过程？对象由哪些部分组成？](普通/对象创建的过程？对象由哪些部分组成？.md)

[怎么处理嵌套View的滑动冲突问题？](普通/怎么处理嵌套View的滑动冲突问题？.md)

[怎样理解Java的方法分派？](普通/怎样理解Java的方法分派？.md)

[描述一次网络请求的过程](普通/描述一次网络请求的过程.md)

[类加载的顺序？](普通/类加载的顺序？.md)

[线程和进程有什么区别？](普通/线程和进程有什么区别？.md)

[网络时延有哪些？](普通/网络时延有哪些？.md)

## 其次

[APK打包的流程？](其次/APK打包的流程？.md)

[Fragment之间通讯的方式？](其次/Fragment之间通讯的方式？.md)

[Fragment的生命周期？](其次/Fragment的生命周期？.md)

[Object有哪些方法？](其次/Object有哪些方法？.md)

[session、cookie、token的区别？](其次/session、cookie、token的区别？.md)

[什么是APK？APK文件都由那些组成？](其次/什么是APK？APK文件都由那些组成？.md)

[原码、反码和补码？](其次/原码、反码和补码？.md)

[对一些资源以及状态的操作保存，最好是保存在生命周期的哪个函数中进行？](其次/对一些资源以及状态的操作保存，最好是保存在生命周期的哪个函数中进行？.md)

[对称加密和非对称加密和哈希算法有什么区别？简单列举一下](其次/对称加密和非对称加密和哈希算法有什么区别？简单列举一下.md)

[直播的流程？](其次/直播的流程？.md)

## 汇总

[Android基础面试题](汇总/Android基础面试题.md)

[Android高级面试题](汇总/Android高级面试题.md)

[git指令大全](汇总/git指令大全.md)

[WireShark学习资料](汇总/WireShark学习资料.md)

[完成了哪些工作？](汇总/完成了哪些工作？)

[易错警告](汇总/易错警告.md)

[算法解题要点](汇总/算法解题要点.md)

[面试文章](汇总/面试文章.md)

## 待整理

[(重要)ConcurrentHashMap原理？](待整理/(重要)ConcurrentHashMap原理？.md)

[(重要)EventBus原理？](待整理/(重要)EventBus原理？.md)

[(重要)Socket编程面试题？](待整理/(重要)Socket编程面试题？.md)

[(重要)接入微信支付的流程？需要注意什么？](待整理/(重要)接入微信支付的流程？需要注意什么？.md)

[APP间传递消息安全吗？要怎么做才能安全呢？](待整理/APP间传递消息安全吗？要怎么做才能安全呢？.md)

[Fragment使用的时候有什么坑？怎么解决的？](待整理/Fragment使用的时候有什么坑？怎么解决的？.md)

[Glide原理？](待整理/Glide原理？.md)

[Service启动流程？](待整理/Service启动流程？.md)

[Shadow的原理？](待整理/Shadow的原理？.md)

[你觉得什么是JVM？md](待整理/你觉得什么是JVM？md)

[做了哪些Apk体积优化？](待整理/做了哪些Apk体积优化？.md)

[加密相关](待整理/加密相关.md)

[如果要设计一个大量图片下载的方法，如何确定并发量，有哪些指标](待整理/如果要设计一个大量图片下载的方法，如何确定并发量，有哪些指标.md)

[对WebView做了哪些性能优化？](待整理/对WebView做了哪些性能优化？.md)

[屏幕适配做了哪些措施？](待整理/屏幕适配做了哪些措施？.md)

[手写生产者消费者模式](待整理/手写生产者消费者模式.md)

[测试](待整理/测试.md)

[自己去设计一个网络请求框架，怎么做？](待整理/自己去设计一个网络请求框架，怎么做？.md)

[进程调度算法？](待整理/进程调度算法？.md)


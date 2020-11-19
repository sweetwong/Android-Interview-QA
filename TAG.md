# 目录
<!-- GFM-TOC -->
* [Android](#Android)
    * [四大组件](#四大组件)
    * [View](#View)
    * [动画](#动画)
    * [网络](#网络)
    * [图片](#图片)
    * [Handler](#Handler)
    * [Binder](#Binder)
    * [WebView](#WebView)
    * [Framework 源码](#Framework-源码)
    * [性能优化](#性能优化)
    * [组件化/插件化/热修复](#组件化/插件化/热修复)
    * [第三方库](#第三方库)
    * [MVC/MVP/MVVM](#MVC/MVP/MVVM)
    * [AOP](#AOP)
    * [Android 其他](#Android-其他)
* [Java](#Java)
    * [Java 基础](#Java-基础)
    * [Java 并发](#Java-并发)
    * [Java 集合](#Java-集合)
    * [Java 泛型](#Java-泛型)
    * [Java 反射](#Java-反射)
    * [Java 虚拟机](#Java-虚拟机)
* [计算机网络](#计算机网络)
* [数据结构与算法](#数据结构与算法)
* [操作系统](#操作系统)
* [设计模式](#设计模式)
<!-- GFM-TOC -->

# Android
## 四大组件
[Activity启动流程？](./源码/Activity启动流程？.md)

[Activity的创建步骤](./普通/Activity的创建步骤.md)

[Activity的四种启动模式和常用的Flags？](./重要/Activity的四种启动模式和常用的Flags？.md)

[Activity的显示原理？](./源码/Activity的显示原理？.md)

[IntentService和Service有什么区别？](./普通/IntentService和Service有什么区别？.md)

[Service启动流程？](./源码/Service启动流程？.md)

[如何跨App启动Activity？](./普通/如何跨App启动Activity？.md)

[说说动态广播的注册和收发原理？](./源码/说说动态广播的注册和收发原理？.md)

## View
[Activity的显示原理？](./源码/Activity的显示原理？.md)

[LinearLayout和RelativeLayout性能对比？](./普通/LinearLayout和RelativeLayout性能对比？.md)

[ListView和RecyclerView的区别？](./记忆/ListView和RecyclerView的区别？.md)

[RecyclerView怎么进行性能优化？](./记忆/RecyclerView怎么进行性能优化？.md)

[SurfaceView和普通View的区别？](./普通/SurfaceView和普通View的区别？.md)

[requestLayout，invalidate，postInvalidate区别与联系？](./普通/requestLayout，invalidate，postInvalidate区别与联系？.md)

[主题切换的方案？](./重要/主题切换的方案？.md)

[什么是MeasureSpec？](./普通/什么是MeasureSpec？.md)

[怎么处理嵌套View的滑动冲突问题？](./普通/怎么处理嵌套View的滑动冲突问题？.md)

[简单讲一下Android的Touch事件分发机制？](./重要/简单讲一下Android的Touch事件分发机制？.md)

[自定义View的过程？](./重要/自定义View的过程？.md)

[讲一下View的绘制流程？View的测量、布局、绘制原理](./重要/讲一下View的绘制流程？View的测量、布局、绘制原理.md)

## 动画
[使用过哪些动画？](./普通/使用过哪些动画？.md)

## 网络
[OkHttp的原理？](./重要/OkHttp的原理？.md)

[Retrofit实现原理？](./重要/Retrofit实现原理？.md)

## 图片
[使用过什么图片加载库，Glide的源码设计哪里很微妙？](./普通/使用过什么图片加载库，Glide的源码设计哪里很微妙？.md)

[如何跨进程传递大的Bitmap？](./源码/如何跨进程传递大的Bitmap？.md)

## Handler
[Handler机制与底层实现？](./重要/Handler机制与底层实现？.md)

[IdleHandler的原理？](./重要/IdleHandler的原理？.md)

## Binder
[AIDL的oneway机制？](./源码/AIDL的oneway机制？.md)

[Android的IPC有哪些方式？并比较他们的优劣](./普通/Android的IPC有哪些方式？并比较他们的优劣.md)

[Binder的transact流程？](./源码/Binder的transact流程？.md)

[Binder的优点？Binder的原理？Binder的通讯模型？](./重要/Binder的优点？Binder的原理？Binder的通讯模型？.md)

[Messenger和AIDL的区别？](./普通/Messenger和AIDL的区别？.md)

## WebView
[Android和JS交互有哪些方式？](./普通/Android和JS交互有哪些方式？.md)

[对WebView做了哪些性能优化？](./记忆/对WebView做了哪些性能优化？.md)

## Framework 源码
[AIDL的oneway机制？](./源码/AIDL的oneway机制？.md)

[AMS和WMS的主要功能是什么？](./普通/AMS和WMS的主要功能是什么？.md)

[Activity启动流程？](./源码/Activity启动流程？.md)

[Activity的创建步骤](./普通/Activity的创建步骤.md)

[Activity的显示原理？](./源码/Activity的显示原理？.md)

[Android系统启动流程？](./源码/Android系统启动流程？.md)

[Binder的transact流程？](./源码/Binder的transact流程？.md)

[Framework中有什么你觉得设计很巧妙的地方？请举例说明](./源码/Framework中有什么你觉得设计很巧妙的地方？请举例说明.md)

[Service启动流程？](./源码/Service启动流程？.md)

[Surface传递的原理？](./源码/Surface传递的原理？.md)

[SystemServer启动流程？](./源码/SystemServer启动流程？.md)

[点击Launcher图标打开app的过程？](./源码/点击Launcher图标打开app的过程？.md)

[简单讲一下Android的Touch事件分发机制？](./重要/简单讲一下Android的Touch事件分发机制？.md)

[讲一下View的绘制流程？View的测量、布局、绘制原理](./重要/讲一下View的绘制流程？View的测量、布局、绘制原理.md)

[说说动态广播的注册和收发原理？](./源码/说说动态广播的注册和收发原理？.md)

## 性能优化
[ANR是怎么回事？如何定位ANR？](./记忆/ANR是怎么回事？如何定位ANR？.md)

[Android开发有哪些节约内存的编码习惯？](./记忆/Android开发有哪些节约内存的编码习惯？.md)

[做了哪些内存优化？](./重要/做了哪些内存优化？.md)

[做了哪些卡顿优化？](./重要/做了哪些卡顿优化？.md)

[做了哪些启动优化？](./重要/做了哪些启动优化？.md)

[做了哪些布局优化？](./重要/做了哪些布局优化？.md)

[如何保证线程安全？](./记忆/如何保证线程安全？.md)

[如何避免OOM的产生？](./记忆/如何避免OOM的产生？.md)

[对WebView做了哪些性能优化？](./记忆/对WebView做了哪些性能优化？.md)

[常见的内存泄漏的场景？](./记忆/常见的内存泄漏的场景？.md)

[性能优化的种类有哪些？](./重要/性能优化的种类有哪些？.md)

[有哪些多线程开发良好的实践？](./记忆/有哪些多线程开发良好的实践？.md)

## 组件化/插件化/热修复
[简述下热修复和插件化的原理？](./重要/简述下热修复和插件化的原理？.md)

## 第三方库
[ButterKnife的原理？](./普通/ButterKnife的原理？.md)

[OkHttp的原理？](./重要/OkHttp的原理？.md)

[Retrofit实现原理？](./重要/Retrofit实现原理？.md)

[使用过什么图片加载库，Glide的源码设计哪里很微妙？](./普通/使用过什么图片加载库，Glide的源码设计哪里很微妙？.md)

## MVC/MVP/MVVM
[MVC、MVP、MVVM的优缺点？](./重要/MVC、MVP、MVVM的优缺点？.md)

## AOP
[什么是元编程？在Android中它有哪些应用场景？](./普通/什么是元编程？在Android中它有哪些应用场景？.md)

## Android 其他
[ART和Dalvik的区别？](./普通/ART和Dalvik的区别？.md)

[ART和Dalvik的区别？](./记忆/ART和Dalvik的区别？.md)

[Android每个版本有什么主要的新特征？](./记忆/Android每个版本有什么主要的新特征？.md)

[Android的签名机制？](./普通/Android的签名机制？.md)

[Android进程的类型？](./普通/Android进程的类型？.md)

[AsyncTask实现原理？](./重要/AsyncTask实现原理？.md)

[IntentService和Service有什么区别？](./普通/IntentService和Service有什么区别？.md)

[Parcelable和Serializable的区别？为什么Parcelable高效？](./重要/Parcelable和Serializable的区别？为什么Parcelable高效？.md)

[主题切换的方案？](./重要/主题切换的方案？.md)

[后台Servce进程保活方案？](./普通/后台Servce进程保活方案？.md)

[如何获取Android设备唯一ID？](./普通/如何获取Android设备唯一ID？.md)

[如何规避Android-P对访问私有API的限制？](./普通/如何规避Android-P对访问私有API的限制？.md)

# Java
## Java 基础
[Java匿名内部类有哪些限制？](./普通/Java匿名内部类有哪些限制？.md)

[Java基本数据类型有哪些？](./普通/Java基本数据类型有哪些？.md)

[Java的String可以有多长？](./重要/Java的String可以有多长？.md)

[Java的引用和C指针有什么区别？](./普通/Java的引用和C指针有什么区别？.md)

[String、StringBuffer和StringBuilder的区别？](./普通/String、StringBuffer和StringBuilder的区别？.md)

[String为什么要设计成不可变的？](./记忆/String为什么要设计成不可变的？.md)

[如何理解Java的三大特性封装、继承和多态？](./重要/如何理解Java的三大特性封装、继承和多态？.md)

[怎样理解Java的方法分派？](./普通/怎样理解Java的方法分派？.md)

[知道哪些单例模式？](./重要/知道哪些单例模式？.md)

## Java 并发
[CAS实现原子操作会出现什么问题？](./普通/CAS实现原子操作会出现什么问题？.md)

[JUC用过哪些类？](./记忆/JUC用过哪些类？.md)

[Java各种线程池使用的场景？](./重要/Java各种线程池使用的场景？.md)

[Java有哪些锁？](./记忆/Java有哪些锁？.md)

[Java锁有哪几种状态？](./记忆/Java锁有哪几种状态？.md)

[ReentrantLock的原理？](./重要/ReentrantLock的原理？.md)

[ThreadLocal原理？](./重要/ThreadLocal原理？.md)

[sleep和wait的区别？](./普通/sleep和wait的区别？.md)

[synchronized和ReentrantLock的有什么区别？](./记忆/synchronized和ReentrantLock的有什么区别？.md)

[synchronized的原理？](./重要/synchronized的原理？.md)

[volatile的原理？](./重要/volatile的原理？.md)

[为什么wait需要搭配synchronized关键字使用？](./普通/为什么wait需要搭配synchronized关键字使用？.md)

[什么是原子性、有序性、可见性？](./普通/什么是原子性、有序性、可见性？.md)

[创建线程有哪些方式？](./普通/创建线程有哪些方式？.md)

[如何保证线程安全？](./记忆/如何保证线程安全？.md)

[如何停止一个线程？](./普通/如何停止一个线程？.md)

[有哪些多线程开发良好的实践？](./记忆/有哪些多线程开发良好的实践？.md)

## Java 集合
[ArrayBlockingQueue和LinkedBlockingQueue的区别？](./普通/ArrayBlockingQueue和LinkedBlockingQueue的区别？.md)

[ArrayMap的原理？](./普通/ArrayMap的原理？.md)

[HashMap的原理？](./重要/HashMap的原理？.md)

[SparseArray的原理？](./普通/SparseArray的原理？.md)

[常用的数据结构有哪些？](./记忆/常用的数据结构有哪些？.md)

## Java 泛型
[Java的泛型实现机制是怎样的？什么是类型擦除？](./重要/Java的泛型实现机制是怎样的？什么是类型擦除？.md)

## Java 反射
[Java反射为什么比较慢？](./记忆/Java反射为什么比较慢？.md)

## Java 虚拟机
[ClassLoader类加载机制？](./重要/ClassLoader类加载机制？.md)

[JVM怎么判断对象是否已死？](./普通/JVM怎么判断对象是否已死？.md)

[Java内存回收算法有哪些？分别有什么优缺点？](./普通/Java内存回收算法有哪些？分别有什么优缺点？.md)

[什么是强、软、弱、虚引用以及它们之间的区别？](./重要/什么是强、软、弱、虚引用以及它们之间的区别？.md)

[对象创建的过程？对象由哪些部分组成？](./普通/对象创建的过程？对象由哪些部分组成？.md)

[描述下Java内存模型？](./重要/描述下Java内存模型？.md)

[类加载的顺序？](./普通/类加载的顺序？.md)

# 计算机网络
[GET与POST的区别？](./记忆/GET与POST的区别？.md)

[HTTP1.0与HTTP1.1与HTTP2.0有什么区别？](./记忆/HTTP1.0与HTTP1.1与HTTP2.0有什么区别？.md)

[HTTPS的原理？HTTP和HTTPS的区别？](./记忆/HTTPS的原理？HTTP和HTTPS的区别？.md)

[POST请求有哪些形式？](./普通/POST请求有哪些形式？.md)

[TCPIP四层协议？](./记忆/TCPIP四层协议？.md)

[报文、报文段、分组、包、数据报、帧、数据流的概念区别？](./记忆/报文、报文段、分组、包、数据报、帧、数据流的概念区别？.md)

[描述一次网络请求的过程](./普通/描述一次网络请求的过程.md)

[网络时延有哪些？](./普通/网络时延有哪些？.md)

[计算机网络-Http问题总结](./记忆/计算机网络-Http问题总结.md)

[计算机网路-传输层问题总结](./记忆/计算机网路-传输层问题总结.md)

[计算机网路-网络层问题总结](./记忆/计算机网路-网络层问题总结.md)

# 数据结构与算法
[知道哪些排序算法？都有什么特性？](./重要/知道哪些排序算法？都有什么特性？.md)

[红黑树特性？](./记忆/红黑树特性？.md)

# 操作系统
[什么是局部性原理？](./记忆/什么是局部性原理？.md)

[什么是快表和多级页表？](./记忆/什么是快表和多级页表？.md)

[什么是操作系统？](./记忆/什么是操作系统？.md)

[什么是系统调用呢？](./记忆/什么是系统调用呢？.md)

[什么是虚拟内存？](./记忆/什么是虚拟内存？.md)

[内存管理有哪几种方式？](./记忆/内存管理有哪几种方式？.md)

[操作系统的主要功能？](./记忆/操作系统的主要功能？.md)

[操作系统的基本特征？](./记忆/操作系统的基本特征？.md)

[有哪些进程调度算法？](./记忆/有哪些进程调度算法？.md)

[有哪些页面置换算法？](./记忆/有哪些页面置换算法？.md)

[死锁发生的必要条件？](./记忆/死锁发生的必要条件？.md)

[进程与线程的区别？](./记忆/进程与线程的区别？.md)

[进程有哪几种状态？](./记忆/进程有哪几种状态？.md)

[进程通信有哪些方式？](./记忆/进程通信有哪些方式？.md)

# 设计模式
[Android中使用了哪些设计模式？实际项目中用到哪些？](./重要/Android中使用了哪些设计模式？实际项目中用到哪些？.md)

[设计模式的6大原则是什么？](./重要/设计模式的6大原则是什么？.md)



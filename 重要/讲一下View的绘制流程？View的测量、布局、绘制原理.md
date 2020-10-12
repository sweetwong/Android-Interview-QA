### 要点
1. 要讲到几个重要的类和关键词，Choreographer、Vsync信号、SurfaceFlinger、ViewRootImpl、WindowManagerImpl、WindowManagerGlobal、Surface


### 流程

1. View的绘制始于ViewRootImpl的performTraversal()开始遍历，会先从根视图开始测量

2. 根视图（DecorView）本身是一个ViewGroup，它同时会循环调用它所有子视图的测量measure()，来决定自己的大小

3. onMeasure：方法主要有两个重要的参数，分别是宽度测量规格和高度测量规格，MeasureSpec测量规格有三种模式，分别是AT_MOST、EXACTLY、UNSPECIFIED，主要是代表父布局堆子布局的期望。我们可以重写onMeasure()，循环测量子View，根据子View确定父View，最后调用setMeasuredDimension()就行

4. onLayout：代表如何布局子View的位置，只有ViewGroup需要重写，会传入四个参数。其中布局也是自上而下，不同的是ViewGroup先在layout()中确定自己的布局，然后在onLayout()方法中再调用子View的layout()方法，让子View布局。在Measure过程中，ViewGroup一般是先测量子View的大小，然后再确定自身的大小。

5. onMeasure：传入一个Canvas，1. 绘制背景 2. 绘制自己 3. 绘制children 4. 绘制装饰


### 自定义View

1. 自绘控件：一般通过重写onDraw()，自己画出控件，例如文字的跑马灯效果，比较少用

2. 组合控件：通过将几个空间组合在一起，然后自己一些属性，组成新的调用。例如标题栏，设置页的选项，带加载动画的按钮等等

3. 继承控件：继承已有的空间，添加额外的效果。例如继承ImageView，添加点击预览的效果。继承一个View，使它的通过比例设置宽高。

### View绘制相关重要的类

* View
* ViewGroup
* DecorView
* ViewRootImpl
* ViewParent
* Window
* PhoneWindow
* WindowManager
* WindowManagerImpl
* WindowManagerGlobal
* Activity
* ActivityThread
* Choreographer


### 链接
[GitHub：View测量、布局及绘制原理](https://github.com/LRH1993/android_interview/blob/master/android/basis/custom_view.md)

[CSDN（郭霖）：Android LayoutInflater原理分析，带你一步步深入了解View(一)](https://blog.csdn.net/guolin_blog/article/details/12921889)

[CSDN（郭霖）：Android视图绘制流程完全解析，带你一步步深入了解View(二)](https://blog.csdn.net/guolin_blog/article/details/16330267)

[CSDN（郭霖）：Android视图状态及重绘流程分析，带你一步步深入了解View(三)](https://blog.csdn.net/guolin_blog/article/details/17045157)

[CSDN（郭霖）：Android自定义View的实现方法，带你一步步深入了解View(四)](https://blog.csdn.net/guolin_blog/article/details/17357967)

[从源码解析-Android中View的绘制流程及performTraversals方法](https://blog.csdn.net/qq_30993595/article/details/80931556)
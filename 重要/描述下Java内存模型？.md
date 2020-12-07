`Java 虚拟机`

# JDK 1.8 内存模型

## JVM 内存模型

<img src="https://user-gold-cdn.xitu.io/2020/5/15/17217e5339c06701?imageView2/0/w/1280/h/960/format/webp/ignore-error/1" alt="image" style="zoom: 80%;" />



## 线程隔离数据区

#### 程序计数器（Program Counter Register）

一小块内存空间，单前线程所执行的字节码行号指示器。字节码解释器工作时，通过改变这个计数器的值来选取下一条需要执行的字节码指令，分支、循环、跳转、异常处理、线程恢复等基础功能都需要依赖这个计数器来完成。。

#### 虚拟机栈

虚拟机栈的生命周期是和线程相同的，是在JVM运行时创建的，在线程方法执行的过程中会创建一个栈帧。主要用于存放局部变量表、操作栈、动态链接、方法出口等信息。

#### 本地方法栈

本地方法栈则是为执行本地方法（Native Method）服务的。

## 线程共享的数据区

#### 方法区（Method Area）

用于存储 JVM 加载的类信息、常量、静态变量、即使编译器编译后的代码等数据。

#### 运行时常量池（Runtime Constant Pool）

是方法区的一部分，用于存放编译器生成的各种字面量和符号引用，这部分内容将在类加载后存放到方法取得运行时常量池中。具备动态性，用的比较多的就是 String 类的 intern() 方法。

#### 堆

存放所有对象实例的地方。

![YD0gOI.png](https://user-gold-cdn.xitu.io/2020/5/15/17217e53e03aa740?imageView2/0/w/1280/h/960/format/webp/ignore-error/1)



##### 注意：JDK 1.8，方法区已经移动到了元空间，元空间并不存在于 JVM 虚拟机内存，而是使用本机的本地内存。

## JVM 堆

JVM 堆用于存放对象的实例

- 新生代中 Eden 与两个 survivor 区的比例时8：2。
- 新生代与老年代的比例为 1：2
  [![YDDtVx.jpg](https://user-gold-cdn.xitu.io/2020/5/15/17217e54304567f9?imageView2/0/w/1280/h/960/format/webp/ignore-error/1)](https://imgchr.com/i/YDDtVx)

## 内存分配策略

##### 1. 对象优先在 Eden 分配

大多数情况下，对象在新生代 Eden 区分配，当 Eden 区空间不够时，发起 Minor GC。

##### 2. 大对象直接进入老年代

大对象的出现会提前触发GC以获取足够的连续空间。当对象大小大于 Eden 区的时候会直接扔到 Old 区。

-XX: PretenureSizeThreshold，大于此值的对象直接在老年代分配，避免在 Eden 区和 Survivor 区之间的大量内存复制。（PretenureSizeThreshold 默认值是 0，意味着任何对象都会现在新生代分配内存）

##### 3. 长期存活的对象进入老年代

为对象定义年龄计数器，对象在 Eden 出生并经过 Minor GC 依然存活，将移动到 Survivor 中，年龄就增加 1 岁，增加到一定年龄则移动到老年代中。（默认是 15）

-XX: MaxTenuringThreshold 用来定义年龄的阈值。

##### 4. 动态对象年龄进入老年代。

当 Survivor 空间中相同年龄所有对象的大小总和大于 Survivor 空间的一半，年龄大于或等于该年龄的对象就可以直接进入老年代，而不需要达到默认的分代年龄。

##### 5. 空间分配担保

简单通俗易懂：当触发 Minor GC 时，存活的对象会转移到另外一个 Survivor 区，当这个区域无法保存还存活的对象，那么就会触发分配担保机制。直接将对象复制到老年代中。

原理：在发生 Minor GC 之前，虚拟机先检查老年代最大可用的连续空间是否大于新生代所有对象总空间，如果条件成立的话，那么  Minor GC 可以确认是安全的。如果不成立的话虚拟机会查看 HandlePromotionFailure 设置值是否允许担保失败。

如果允许那么就会继续检查老年代最大可用的连续空间是否大于历次晋升到老年代对象的平均大小。如果大于，将尝试着进行一次 Minor GC。如果小于或者 HandlePromotionFailure 设置不允许冒险，那么就要进行一次 Full GC（担保失败）。

## JDK 8 为什么要使用元空间取代永久代？

- 字符串存在永久代中，容易出现 **性能问题和内存溢出**
- 类及方法的信息等比较难确定其大小，因此对于 **永久代的大小指定比较困难**，太小容易出现永久代溢出，太大则容易导致老年代溢出
- 永久代会为 GC 带来不必要的复杂度，并且回收效率偏低
- 将 HotSpot 与 JRockit 合二为一

## 链接

[Github：CS-Notes：Java 虚拟机](http://www.cyc2018.xyz/Java/Java%20%E8%99%9A%E6%8B%9F%E6%9C%BA.html#%E4%B8%80%E3%80%81%E8%BF%90%E8%A1%8C%E6%97%B6%E6%95%B0%E6%8D%AE%E5%8C%BA%E5%9F%9F)

[掘金：01-JDK1.8内存模型](https://juejin.im/post/6844904160031145991#heading-20)

[博客园：Metaspace 之一：Metaspace整体介绍（永久代被替换原因、元空间特点、元空间内存查看分析方法）(深入好文)](https://www.cnblogs.com/duanxz/p/3520829.html)

[CSDN：JDK8为什么要使用元空间取代永久代？](https://blog.csdn.net/meism5/article/details/104936566/)
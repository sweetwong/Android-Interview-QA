`Android 其他`

# Android Runtime (ART) 和 Dalvik

Android Runtime (ART) 是 Android 上的应用和部分系统服务使用的托管式运行时。ART 及其前身 Dalvik 最初是专为 Android 项目打造的。作为运行时的 ART 可执行 Dalvik 可执行文件并遵循 Dex 字节码规范。

ART 和 Dalvik 是运行 Dex 字节码的兼容运行时，因此针对 Dalvik 开发的应用也能在 ART 环境中运作。不过，Dalvik 采用的一些技术并不适用于 ART。有关最重要问题的信息，请参阅[在 Android Runtime (ART) 上验证应用行为](http://developer.android.com/guide/practices/verifying-apps-art.html)。

## ART 功能

以下是 ART 实现的一些主要功能。

### 1. 预先 (AOT) 编译

ART 引入了预先编译机制，可提高应用的性能。ART 还具有比 Dalvik 更严格的安装时验证。

在安装时，ART 使用设备自带的 **dex2oat** 工具来编译应用。此实用工具接受 [DEX](https://source.android.com/devices/tech/dalvik/dex-format) 文件作为输入，并为目标设备生成经过编译的应用可执行文件。该工具应能够顺利编译所有有效的 DEX 文件。但是，一些后处理工具会生成无效文件，Dalvik 可以接受这些文件，但 ART 无法编译这些文件。如需了解详情，请参阅[处理垃圾回收问题](http://developer.android.com/guide/practices/verifying-apps-art.html#GC_Migration)。

### 2. 垃圾回收方面的优化

垃圾回收 (GC) 会耗费大量资源，这可能有损于应用性能，导致显示不稳定、界面响应速度缓慢以及其他问题。ART 通过以下几种方式对垃圾回收做了优化：

- 大多采用并发设计，具有一次 GC 暂停
- **并发复制**，可减少后台内存使用和碎片
- GC 暂停的时间不受堆大小影响
- 在清理最近分配的短时对象这种特殊情况中，回收器的总 GC 时间更短
- 优化了垃圾回收的工效，能够更加及时地进行并行垃圾回收，这使得 [`GC_FOR_ALLOC`](http://developer.android.com/tools/debugging/debugging-memory.html#LogMessages) 事件在典型用例中极为罕见

### 3. 开发和调试方面的优化

ART 提供了大量功能来优化应用开发和调试。

#### 支持采样分析器

一直以来，开发者都使用 [Traceview](http://developer.android.com/tools/help/traceview.html) 工具（用于跟踪应用执行情况）作为分析器。虽然 Traceview 可提供有用的信息，但每次方法调用产生的开销会导致 Dalvik 分析结果出现偏差，而且使用该工具明显会影响运行时性能。

ART 添加了对没有这些限制的专用采样分析器的支持，因而可更准确地了解应用执行情况，而不会明显减慢速度。KitKat 版本为 Dalvik 的 Traceview 添加了采样支持。

#### 支持更多调试功能

ART 支持许多新的调试选项，特别是与监控和垃圾回收相关的功能。例如，您可以：

- 查看堆栈跟踪中保留了哪些锁，然后跳转到持有锁的线程。
- 询问指定类的当前活动的实例数、请求查看实例，以及查看使对象保持有效状态的参考。
- 过滤特定实例的事件（如断点）。
- 查看方法退出（使用“method-exit”事件）时返回的值。
- 设置字段观察点，以在访问和/或修改特定字段时暂停程序执行。

#### 优化了异常和崩溃报告中的诊断详细信息

当发生运行时异常时，ART 会为您提供尽可能多的上下文和详细信息。ART 会提供 `java.lang.ClassCastException`、`java.lang.ClassNotFoundException` 和 `java.lang.NullPointerException` 的更多异常详细信息。（较高版本的 Dalvik 会提供 `java.lang.ArrayIndexOutOfBoundsException` 和 `java.lang.ArrayStoreException` 的更多异常详细信息，这些信息现在包括数组大小和越界偏移量；ART 也提供这类信息。）

例如，`java.lang.NullPointerException` 现在会显示有关应用尝试处理 null 指针时所执行操作的信息，例如应用尝试写入的字段或尝试调用的方法。一些典型示例如下：

```
java.lang.NullPointerException: Attempt to write to field 'int
android.accessibilityservice.AccessibilityServiceInfo.flags' on a null object
reference
```

```
java.lang.NullPointerException: Attempt to invoke virtual method
'java.lang.String java.lang.Object.toString()' on a null object reference
```

ART 还通过纳入 Java 和原生堆栈信息，在应用原生代码崩溃报告中提供更实用的上下文信息。

## 链接

[Android Developer: Android Runtime (ART) and Dalvik](https://source.android.com/devices/tech/dalvik)

[ART和Dalvik的区别？](https://www.zhihu.com/question/29406156)

[Android里的Dalvik、ART、JIT、AOT有什么关系？](https://www.cnblogs.com/wytiger/p/10979796.html)

[知乎：对比JIT和AOT，各自有什么优点与缺点?](https://www.zhihu.com/question/23874627)
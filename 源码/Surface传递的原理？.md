## 要点
1. Surface 的本质是其 native 层的对象，而 native 层对象的本质是 GraphicBufferProducer 而不是 Buffer，GraphicBufferProducer 是一个 native 的 binder 对象；

2. Surface 跨进程传递其本质是 GraphicBufferProducer 的传递；
3. Activity 在 performTraversals 第一次绘制时调用 relayoutWindow 获取到 SurfaceControl 进而通过其中的 GraphicBufferProducer 创建 Surface 对象；

## 链接
[Surface跨进程传递原理](https://coding.imooc.com/lesson/340.html#mid=24599)
`View`

## 相同点

三个方法都有刷新界面的效果。

## 不同点

invalidate 和 postInvalidate 只会调用 onDraw() 方法；requestLayout 则会重新调用 onMeasure、onLayout、onDraw。

1. 调用了 invalidate 方法后，会为该 View 添加一个标记位，同时不断向父容器请求刷新，父容器通过计算得出自身需要重绘的区域，直到传递到 ViewRootImpl 中，最终触发 performTraversals 方法，进行开始 View 树重绘流程(只绘制需要重绘的视图)。

2. 调用 postInvalidate 方法后，子线程会发出一条消息，最终在主线程调用 invalidate

3. 调用 requestLayout 方法，会标记当前 View 及父容器，同时逐层向上提交，直到 ViewRootImpl 处理该事件，ViewRootImpl 会调用三大流程，从 measure 开始，对于每一个含有标记位的 view 及其子 View 都会进行测量 onMeasure、布局onLayout、绘制onDraw。

## 链接
[requestLayout，invalidate，postInvalidate区别与联系](https://www.kancloud.cn/aslai/interview-guide/1126390)
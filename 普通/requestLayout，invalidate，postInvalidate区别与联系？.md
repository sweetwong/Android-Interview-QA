## 相同点

三个方法都有刷新界面的效果。

## 不同点

invalidate和postInvalidate只会调用onDraw()方法；requestLayout则会重新调用onMeasure、onLayout、onDraw。

1. 调用了invalidate方法后，会为该View添加一个标记位，同时不断向父容器请求刷新，父容器通过计算得出自身需要重绘的区域，直到传递到ViewRootImpl中，最终触发performTraversals方法，进行开始View树重绘流程(只绘制需要重绘的视图)。

2. 调用postInvalidate方法后，子线程会发出一条消息，最终在主线程调用invalidate

3. 调用requestLayout2方法，会标记当前View及父容器，同时逐层向上提交，直到ViewRootImpl处理该事件，ViewRootImpl会调用三大流程，从measure开始，对于每一个含有标记位的view及其子View都会进行测量onMeasure、布局onLayout、绘制onDraw。

## 链接
[requestLayout，invalidate，postInvalidate区别与联系](https://www.kancloud.cn/aslai/interview-guide/1126390)
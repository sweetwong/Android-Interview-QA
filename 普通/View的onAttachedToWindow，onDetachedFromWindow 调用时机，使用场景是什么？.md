## 调用时机
顾名思义，Attached 就是附加的意思，Detached：分离。 onAttachedToWindow 就是当这个 View 附加到 Window（每个 Activity 里面都对应着一个 Window）的时候，这个方法就会被回调。 onDetachedFromWindow 刚好相反，它是当 View 与 Window 分离的时候回调。

## 使用场景
自定义 View 的时候，某些比较重量级的资源，而且不能与其他 View 通用的时候，就可以重写这两个方法，并在 onAttachedToWindow 中进行初始化，onDetachedFromWindow 方法里释放掉。

比如 Bitmap，虽说现在不用主动调用 recycle 方法来回收，但在 8.0 及以上系统，手动调用是会立即释放所占用的内存的，所以个人认为还是有必要手动回收的，当然了，如果图片比较小，对内存没什么影响的就不用了。

一些用作计算的子线程，或其他跟 View 显示有关的任务，在 onDetachedFromWindow 中也可以停掉了，因为大多数情况下，这些实时数据对于被分离后 View 已经没有意义了。

## 链接

[View的onAttachedToWindow ,onDetachedFromWindow 调用时机，使用场景是什么？](https://wanandroid.com/wenda/show/8488)
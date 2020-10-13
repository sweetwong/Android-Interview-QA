## 调用时机
顾名思义，Attached就是附加的意思，Detached：分离。 onAttachedToWindow就是当这个View附加到Window（每个Activity里面都对应着一个Window）的时候，这个方法就会被回调。 onDetachedFromWindow刚好相反，它是当View与Window分离的时候回调。

## 使用场景
自定义View的时候，某些比较重量级的资源，而且不能与其他View通用的时候，就可以重写这两个方法，并在onAttachedToWindow中进行初始化，onDetachedFromWindow方法里释放掉。

比如Bitmap，虽说现在不用主动调用recycle方法来回收，但在8.0及以上系统，手动调用是会立即释放所占用的内存的，所以个人认为还是有必要手动回收的，当然了，如果图片比较小，对内存没什么影响的就不用了。

一些用作计算的子线程，或其他 跟View显示有关的任务，在onDetachedFromWindow中也可以停掉了，因为大多数情况下，这些实时数据对于被分离后View已经没有意义了。

## 链接

[View的onAttachedToWindow ,onDetachedFromWindow 调用时机，使用场景是什么？](https://wanandroid.com/wenda/show/8488)
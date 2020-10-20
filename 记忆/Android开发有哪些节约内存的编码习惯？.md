## 参考

[如何避免OOM的产生？](../记忆/如何避免OOM的产生？.md)

## 减少内存占用

1. 使用合适的**数据结构**，ArrayMap、SparseArray
2. 使用 StringBuilder 代替 "+"
3. 谨慎使用 **largeHeap** 和**多进程**
4. 谨慎使用**第三方库**
5. 内存复用，使用池化技术
6. 避免使用**枚举**，用int代替
7. **Bitmap** 的使用：
    1. 选择合适的**分辨率**
    
    2. 注意原始文件与内存**缩放**的结果
    3. 不用帧动画，用代码实现
    4. 考虑对Bitmap进行**重采样**和复用配置
8. 使用 **NDK**
9. 谨慎地使用**服务**
10. 多使用软引用，弱引用

## 链接
[如何避免OOM的产生？](https://coding.imooc.com/lesson/317.html#mid=22314)

[浅谈Android内存优化](https://juejin.im/post/6844903805931225101#heading-9)

[Manage Your App's Memory](https://developer.android.com/topic/performance/memory#Services)

[哪些情况下会导致oom问题？(好答案)](https://github.com/Moosphan/Android-Daily-Interview/issues/5)

[Java节省内存的几条建议](https://blog.csdn.net/numbibi/article/details/7492808)
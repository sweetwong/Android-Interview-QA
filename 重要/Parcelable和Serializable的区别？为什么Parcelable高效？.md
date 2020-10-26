## 回答
1. 类型不同：Seiralizable 是 Java 通用的序列化方法，Parcelable 是 Android 特有的序列化方法；

2. 作用不同：Serializable 是用来**保存数据**的，Parcelable 是用来**高效传输数据**的

3. Parcelable 比 Serializable 实现起来更复杂，因为**手动实现了一些序列化的过程**

4. Serializable 实现的原理是**反射**，并频繁在**磁盘**进行进行**I/O操作**，因此会产生大量临时对象，导致垃圾回收频繁调用；而 Parcelable 是直接在**内存**中读写

5. Parcel 对应一个原生的 Parcel 对象，具体实现都是原生代码，一般最后会调用 Parcel.cpp 的 write 方法，write 方法会直接调用 **memcpy** 内存复制

6. 不要用 Parcelable 做数据持久化，因为 Android 不同版本的 Parcelable 实现可能不同 

## 链接
[Android Parcelable原理分析](https://juejin.im/post/6844904050702434311)
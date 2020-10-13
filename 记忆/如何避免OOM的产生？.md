## OOM产生的原因？
已使用内存+新申请内存 > 可分配内存

## 如何避免OOM产生？
减少内存的占用（参考5R法则）

## 有哪些节约内存的细节？

#### 1. 使用合适的数据结构
例如用ArrayMap/SparseArray代替HashMap，用ArraySet代替HashSet
ArrayMap：查询O(logN)，插入O(logN)，1.5倍扩容，0.5倍缩容，无额外对象开销，小数组复用池
HashMap：查询接近O(1)，插入接近O(1)，2倍扩容，无缩容机制，额外的Entry开销

#### 2. 避免使用枚举
枚举：24字节，整形：4字节

#### 3. Bitmap的使用
1. 选择合适的分辨率
2. 注意原始文件分辨率和内存缩放的结果
3. 不使用帧动画，用代码实现动效
4. 考虑对Bitmap的重采样和复用配置

#### 4. 谨慎的使用多进程

#### 5. 谨慎的使用Large Heap

#### 6. 使用NDK
Native Heap没有专门的限制，内存大户核心逻辑放在Native层，例如游戏框架、OpenGL、地图


## 内存优化的5R法则

1. Reduce缩减：降低图片分辨率/重采样/抽稀策略

2. Reuse复用：池化策略/避免频繁创建对象，减小GC压力

3. Recycle回收：主动销毁、结束，避免内存泄漏/生命周期闭环

4. Refactor重构：使用更合适的数据结构/更合理的程序架构

5. Revalue重审：谨慎使用Large Heap/多进程/第三方框架

## 链接
[慕课网：如何避免OOM的产生？](https://coding.imooc.com/lesson/317.html#mid=22314)
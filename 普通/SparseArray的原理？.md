## 原理

1. SparseArray 内部维护了两个数组，分别是：mKeys，按照由小到大存放；mValues，与 mKeys 有一一对应的关系
2. 当我们执行 put() 操作，会根据 key，通过二分查找找到在 mKeys 上对应的位置，如果 index >=0 就直接修改，如果 index < 0，根据情况是来修改、回收、移动
3. 当我们执行 remove() 或者 delete() 操作时，会直接把对应的 value 置为 DELETE，并标记 mGarbage 状态为 true
4. SparseArray 初始容量为 10，每次扩容两倍
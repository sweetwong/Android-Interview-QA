## 原理

1. 维护了两个数组（mArray 和 mHashes）。mArray 用来存放键值对，奇数放 key，偶数放 value；mHashes 用来存放 哈希值
2. 哈希数组按照 hash 值排序，在调用 put() 时，先求出 key 的 hash 值，通过二分查找找到 hash 值对应的 index，然后通过 index 来找到对应的位置，执行修改或者插入
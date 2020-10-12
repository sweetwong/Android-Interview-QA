### 回答
1. 图片写到文件，路径传到另一个进程，再读出来

2. **通过 Bundle 的 putBinder**

3. 通过 ContentProvider

4. 通过 Socket

### 为什么 Intent.putExtra 会报异常？
当直接调用 Intent.putExtra 传递 Bitmap 时，会把 Bitmap 直接写到缓冲区导致 Parcel过大在 IPC 底层调用时会抛出 TransactionTooLarge 异常；但是当调用 Bundle.putBinder 时只会跨进程传递一个 Binder 对象，然后在另一端可以直接通过文件描述符以内存映射的方式读出 Bitmap；

### 链接
[慕课网：怎么跨进程传递大图片？](https://coding.imooc.com/lesson/340.html#mid=25131)
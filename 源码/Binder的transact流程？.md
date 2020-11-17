`Binder`、`Framework 源码`

## 客户端流程

1. 客户端通过 getService() 或者 bindService() 获取到远程的 Binder 对象（BinderProxy）
2. 创建两个 Parcel 对象，分别是 data 和 reply ，一个是传递参数的数据，另一个是收到回复的数据
3. 最后会调用到 BinderProxy 对象的 transact() 方法，然后调用 transactNative()，进入 native 层
4. native 的 IBinder 有两个实现，一个是 BpBinder 对应客户端，一个是 BBinder 对应服务端
5. BpBinder 的 transact() 里会调到 IPCThreadState 的 transact()
6. 这里主要有两个步骤，分别是 writeTransactionData() 写数据和 waitForResponse() 读数据
7. writeTransactionData() 会生成一个 binder_transaction_data 的对象，然后把 data 的数据写到这个对象中，然后把数据传入到 mOut 中（一次复制）
8. 由于之前服务端已经进行了内存映射 mmap()，在服务端的 mIn 能够直接读到这个这个数据（标准的生产者消费者模式）
9. waitForResponse() 会一直 talkWithDriver() 与驱动交互，实际上是通过 ioctl 命令实现的

## 服务端流程

1. 当 Zygote 进程接收到 SystemServer 进程的 Socket 的消息时，会 fork 出子进程
2. 在进入到子进程的 main() 方法之前，会有一系列的初始化操作，其中有一步就是调用 ProcessState::self() 方法
3. 在 ProcessState::self() 方法中，会打开 Binder 驱动，并且进行内存映射 mmap()
4. 然后会调用 IPCThreadState::joinThreadPool() 方法，循环从 mOut 中读取 Binder 数据，并回调给 onTransact() 方法

## 要点

1. ProcessState 是一个进程单例，IPCThreadState 是一个线程单例
2. 要说到 IPCThreadState，里面维护了两个 Parcel，分别是 mIn 和 mOut，写数据和读数据都是通过这两个 Parcel 实现的
3. talkWithDriver() 实际上是通过底层的 ioctl 实现的的
4. oneway 机制在 waitForResponse() 方法传入的参数是 null，这是最大的区别
5. IBinder 有两个实现，分别是 BpBinder 对应客户端，BBinder对应服务端

## 链接

[慕课网：一次完整的ipc通信流程是怎样的](https://coding.imooc.com/lesson/340.html#mid=24604)

[Binder机制-IPCThreadState过程（写得好呀）](https://blog.csdn.net/yanlinembed/article/details/80640791)
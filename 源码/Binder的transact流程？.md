## 流程
1. Client 端通过 getService() 或者 bindService() 获取到远程的 Binder 对象

2. 创建两个 Parcel 对象，分别是 data 和 reply ，一个是传递参数的数据，另一个是收到回复的数据
3. 最后会调用到 BinderProxy 对象的 transact() 方法，进入 native 层
4. native 的 IBinder 有两个对象，一个是 BpBinder 对应客户端，一个是 BBinder 对应服务端
5. 然后会调到 IPCThreadState 的 transact() 方法
6. 这里主要有两个步骤，分别是 writeTransactionData() 写数据和 waitForResponse() 读数据
7. writeTransactionData() 会生成一个 binder_transaction_data 的对象，然后把 data 的数据写到这个对象中，然后把数据传入到 mOut 中
8. 服务端会在 BBinder 的 onTransact() 方法收到回调
9. waitForResponse() 会一直 talkWithDriver() 与驱动交互，实际上是通过 ioctl 命令实现的

## 要点

1. 要说到 IPCThreadState，里面维护了两个 Parcel，分别是 mIn 和 mOut，写数据和读数据都是通过这两个 Parcel 实现的
2. talkWithDriver() 实际上是通过底层的 ioctl 实现的的
3. oneway 在 waitForResponse() 方法传入的参数是 null，这是最大的区别
4. IBinder 有两个实现，分别是 BpBinder 对应客户端，BBinder对应服务端

## 链接

[慕课网：一次完整的ipc通信流程是怎样的](https://coding.imooc.com/lesson/340.html#mid=24604)
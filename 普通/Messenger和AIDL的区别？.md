## 区别
#### 1. Messenger 本质也是 Binder，只是进行了封装，开发的时候不用再写 .aidl 文件。
结合我自身的使用，因为不用去写 .aidl 文件，相比起来，Messenger 使用起来十分简单。但前面也说了，Messenger 本质上也是 Binder，故在底层进程间通信这一块，两者的效率应该是一样的。

#### 2. 在 Service 端，Messenger 处理 Client 端的请求是单线程的，而 AIDL 是多线程的。
使用 AIDL 的时候，Service 端每收到一个 Client 端的请求时，就会启动一个线程（非主线程）去执行相应的操作。而 Messenger，Service 收到的请求是放在 Handler 的 MessageQueue 里面，Handler 大家都用过，它需要绑定一个 Thread，然后不断 poll message 执行相关操作，这个过程是同步执行的。

#### 3. Client 的方法，使用 AIDL 获取返回值是同步的，而 Messenger 是异步的。
Messenger 只提供了一个方法进行进程间通信，就是 send(Message msg) 方法，发送的是一个 Message，没有返回值，要拿到返回值，需要把 client 的 Messenger 作为 msg.replyTo 参数传递过去，Service 端处理完之后，在调用客户端的 Messenger 的 send(Message msg) 方法把返回值传递回 client，这个过程是异步的，而 AIDL 你可以自己指定方法，指定返回值，它获取返回值是同步的。

## 链接

[CSDN：android进程通信值Messenger和AIDL的区别](https://blog.csdn.net/hello_json/article/details/79815320)


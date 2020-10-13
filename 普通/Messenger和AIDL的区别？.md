## 区别
#### 1. Messenger本质也是AIDL，只是进行了封装，开发的时候不用再写.aidl文件。
结合我自身的使用，因为不用去写.aidl文件，相比起来，Messenger使用起来十分简单。但前面也说了，Messenger本质上也是AIDL，故在底层进程间通信这一块，两者的效率应该是一样的。

#### 2. 在Service端，Messenger处理Client端的请求是单线程的，而AIDL是多线程的。
使用AIDL的时候，Service端每收到一个Client端的请求时，就会启动一个线程（非主线程）去执行相应的操作。而Messenger，Service收到的请求是放在Handler的MessageQueue里面，Handler大家都用过，它需要绑定一个Thread，然后不断poll message执行相关操作，这个过程是同步执行的。

#### 3. Client的方法，使用AIDL获取返回值是同步的，而Messenger是异步的。
Messenger只提供了一个方法进行进程间通信，就是send(Message msg)方法，发送的是一个Message，没有返回值，要拿到返回值，需要把client的Messenger作为msg.replyTo参数传递过去，Service端处理完之后，在调用客户端的Messenger的send(Message msg)方法把返回值传递回client，这个过程是异步的，而AIDL你可以自己指定方法，指定返回值，它获取返回值是同步的。

## 链接

[CSDN：android进程通信值Messenger和AIDL的区别](https://blog.csdn.net/hello_json/article/details/79815320)
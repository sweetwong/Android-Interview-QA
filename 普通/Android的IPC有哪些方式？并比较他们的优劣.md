## Android IPC的 6 种方式
1. AIDL：AIDL 是 IPC 的一个轻量级实现

2. Messenger（信使）： Messenger 本质也是 AIDL，只是进行了封装，开发的时候不用再写 .aidl 文件，所以相对于 AIDL 较于简单。

3. ContentProvider：ContentProvider 相当于为数据存储和获取提供了一个统一的接口

4. Socket：一般用来网络数据的交换，Zygote 也是通过 Socket 接收打开进程的消息

5. 文件共享：通过 IO 方式把数据写到文件里进行通信，需要注意序列化

6. Bundle（Intent的形式）：一般用于四大组件间的进程间通信，简单易用

## 对比图
![](../assets/IPC对比图.png)

## 链接

[简书：浅谈IPC通信之各方式对比使用及场景（四）](https://www.jianshu.com/p/7abdead13e2b)


## oneway 机制的特点

1. oneway 在 client 端（调用端）是异步调用（不需要等待）
2. 在 server 端（接收端）是串行处理 
3. oneway 最大的区别在于，通过 AIDL 生成的类，其 replay 为空，所以在 IPCThreadState 的 transact 中，waitForResponse 方法传入的 null，既不需要等待服务端的响应

## 链接
[慕课网：说说binder的oneway机制](https://coding.imooc.com/lesson/340.html#mid=26032)
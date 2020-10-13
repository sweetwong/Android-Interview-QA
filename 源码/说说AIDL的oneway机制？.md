## oneway 机制的特点

1. oneway在client端是异步调用（不需要等待）
2. 在server端是串行处理 
3. oneway最大的区别在于，通过AIDL生成的类，其replay为空，所以在IPCThreadState的transact中，waitForResponse方法传入的null，既不需要等待服务端的响应

## 链接
[说说binder的oneway机制](https://coding.imooc.com/lesson/340.html#mid=26032)
`计算机网络`

1. **GET**：通常用于请求服务器发送某些资源
2. **POST**：发送数据给服务器
3. **HEAD**：请求资源的头部信息，并且这些头部与 HTTP GET 方法请求时返回的一致。该请求方法的一个使用场景是在下载一个大文件前先获取其大小再决定是否要下载，以此可以节约带宽资源
4. **PUT**：用于新增资源或者使用请求中的有效负载替换目标资源的表现形式
5. **DELETE**：用于删除指定的资源
6. **OPTIONS**：用于获取目的资源所支持的通信选项
7. **PATCH**：用于对资源进行部分修改
8. **CONNECT**：HTTP/1.1 协议中预留给能够将连接改为管道方式的代理服务器
9. **TRACE**：回显服务器收到的请求，主要用于测试或诊断

## 幂等/安全性

幂等：一次操作和多次操作的结果相同。除了 POST、PATCH 都是幂等的。

安全：不会修改服务器的资源。除了 GET、HEAD、OPTIONS 都是不安全的。

| HTTP Method | Idempotent（幂等） | Safe（安全） |
| :---------- | :----------------- | :----------- |
| GET         | yes                | **yes**      |
| POST        | **no**             | no           |
| HEAD        | yes                | **yes**      |
| PUT         | yes                | no           |
| DELETE      | yes                | no           |
| OPTIONS     | yes                | **yes**      |
| PATCH       | **no**             | no           |

## 链接

[Github：CS-Notes：HTTP](https://github.com/CyC2018/CS-Notes/blob/master/notes/HTTP.md#%E4%BA%8Chttp-%E6%96%B9%E6%B3%95)

[掘金：面试官（9）：可能是全网最全的http面试答案](https://juejin.cn/post/6844903865410650126)
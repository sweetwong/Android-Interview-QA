## 回答要点

1. Okhttp 在初始化 OkhttpClient 时，建造者模式（隐藏掉系统的复杂性，对外部提供接口）

2. **OkHttp 核心代码是由众多拦截器组成的拦截器链，这是很典型的责任链模式**，它们实现了网络请求、缓存、透明压缩等功能，例如 CacheIntercepor 负责缓存，ConnectInterceptor 负责建立连接，CallServerInterceptor 负责请求服务器。是从 getResponseWithInterceptorChain 方法开始的。

3. 对于把 Request 变成 Response 这件事来说，每个 Interceptor 都可能完成这件事，所以我们循着链条让每个 Interceptor 自行决定能否完成任务以及怎么完成任务（自力更生或者交给下一个 Interceptor），这样就可以很好地将很复杂的网络请求的职责分离成一个个拦截器

4. OkHttp 底层封装了 Okio 进行 Socket 相关的操作


## 流程

1. OkHttpClient 实现 Call.Factory，负责为 Request 创建 Call；
2. RealCall 为具体的 Call 实现，其 enqueue() 异步接口通过 Dispatcher 利用 ExecutorService 实现，而最终进行网络请求时和同步 execute() 接口一致，都是通过 **getResponseWithInterceptorChain**() 函数实现；
3. getResponseWithInterceptorChain() 中利用 Interceptor 链条，分层实现缓存、透明压缩、网络 IO 等功能；

## 拦截器

1. **RetryAndFollowUpInterceptor**：负责失败重试以及重定向
2. **BridgeInterceptor**：负责把用户构造的请求转换为发送到服务器的请求、把服务器返回的响应转换为用户友好的响应
3. **CacheInterceptor**：负责读取缓存直接返回、更新缓存
4. **ConnectInterceptor**：负责和服务器建立连接
5. **CallServerInterceptor**：向服务器发送请求数据、从服务器读取响应数据

## 链接

[拆轮子系列: 拆 OkHttp（终极好文）](https://blog.piasy.com/2016/07/11/Understand-OkHttp/index.html)
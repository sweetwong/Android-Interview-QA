`第三方库`

## 回答要点

1. Okhttp 在初始化 OkhttpClient 时，建造者模式（隐藏掉系统的复杂性，对外部提供接口）

2. **OkHttp 核心代码是由众多拦截器组成的拦截器链，这是很典型的责任链模式**，它们实现了网络请求、缓存、透明压缩等功能，例如 CacheIntercepor 负责缓存，ConnectInterceptor 负责建立连接，CallServerInterceptor 负责请求服务器。是从 getResponseWithInterceptorChain 方法开始的。

3. 对于把 Request 变成 Response 这件事来说，每个 Interceptor 都可能完成这件事，所以我们循着链条让每个 Interceptor 自行决定能否完成任务以及怎么完成任务（自力更生或者交给下一个 Interceptor），这样就可以很好地将很复杂的网络请求的职责分离成一个个拦截器

4. OkHttp 底层封装了 Okio 进行 Socket 相关的操作


## 流程

1. OkHttpClient 实现 Call.Factory，负责为 Request 创建 Call；
2. RealCall 为具体的 Call 实现，其 enqueue() 异步接口通过 Dispatcher 利用 ExecutorService 实现，而最终进行网络请求时和同步 execute() 接口一致，都是通过 **getResponseWithInterceptorChain**() 函数实现；
3. **getResponseWithInterceptorChain()** 中利用 Interceptor 链条，分层实现缓存、透明压缩、网络 IO 等功能；

## 拦截器

1. **应用拦截器**：拿到的是原始请求，可以添加一些自定义 header、通用参数、参数加密、网关接入等等
2. **RetryAndFollowUpInterceptor**：处理错误重试和重定向
3. **BridgeInterceptor**：应用层和网络层的桥接拦截器，主要工作是为请求添加 cookie、添加固定的 header，比如 Host、Content-Length、Content-Type、User-Agent 等等，然后保存响应结果的 cookie，如果响应使用 gzip 压缩过，则还需要进行解压
4. **CacheInterceptor**：缓存拦截器，如果命中缓存则不会发起网络请求
5. **ConnectInterceptor**：连接拦截器，内部会维护一个连接池，负责连接复用、创建连接（三次握手等等）、释放连接以及创建连接上的 socket 流
6. **networkInterceptors**：网络拦截器，用户自定义拦截器，通常用于监控网络层的数据传输
7. **CallServerInterceptor**：请求拦截器，在前置准备工作完成后，真正发起了网络请求

## 简单的请求流程

```java
        // 创建 OkHttpClient
        OkHttpClient client = new OkHttpClient();
        // 创建请求
        Request request = new Request.Builder()
                .url("https://gank.io/api/v2/banners")
                .build();
        // 进行请求
        try (Response response = client.newCall(request).execute()) {
            String json = response.body().string();
            GankBanners gankBanners = GsonUtils.fromJson(json, GankBanners.class);
            ThreadUtils.print(gankBanners);
        } catch (IOException e) {
            e.printStackTrace();
        }
```

## addInterceptor 与 addNetworkInterceptor的区别？

二者通常的叫法为应用拦截器和网络拦截器，从整个责任链路来看，应用拦截器是最先执行的拦截器，也就是用户自己设置request属性后的原始请求，而网络拦截器位于 ConnectInterceptor 和 CallServerInterceptor 之间，此时网络链路已经准备好，只等待发送请求数据。

1. 首先，应用拦截器在 RetryAndFollowUpInterceptor 和 CacheInterceptor 之前，所以一旦发生错误重试或者网络重定向，网络拦截器可能执行多次，因为相当于进行了二次请求，但是应用拦截器永远只会触发一次。另外如果在 CacheInterceptor 中命中了缓存就不需要走网络请求了，因此会存在短路网络拦截器的情况。
2. 其次，如上文提到除了CallServerInterceptor，每个拦截器都应该至少调用一次realChain.proceed方法。实际上在应用拦截器这层可以多次调用proceed方法（本地异常重试）或者不调用proceed方法（中断），但是网络拦截器这层连接已经准备好，可且仅可调用一次proceed方法。
3. 最后，从使用场景看，应用拦截器因为只会调用一次，通常用于统计客户端的网络请求发起情况；而网络拦截器一次调用代表了一定会发起一次网络通信，因此通常可用于统计网络链路上传输的数据。

## 链接

[拆轮子系列: 拆 OkHttp（终极好文）](https://blog.piasy.com/2016/07/11/Understand-OkHttp/index.html)

[面试官：听说你熟悉OkHttp原理？](https://juejin.im/post/6844904087788453896#comment)
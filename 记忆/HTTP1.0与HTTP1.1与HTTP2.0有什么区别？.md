## HTTP 1.0 与 HTTP 1.1 有什么区别？

1. **长连接**：HTTP 1.0 需要 keep-alive 参数来告知服务器长连接，HTTP 1.1 默认开启 **Connection： keep-alive** 长连接。
（在 HTTP 1.0 中，默认使用的是短连接。也就是说，浏览器和服务器每进行一次 HTTP 操作，就建立一次连接，但任务结束就中断连接。如果客户端浏览器访问的某个 HTML 或其他类型的 Web 页中包含有其他的 Web 资源，如 JavaScript 文件、图像文件、CSS 文件等；当浏览器每遇到这样一个 Web 资源，就会建立一个 HTTP 会话）

2. **节约带宽**：**HTTP 1.1 支持只发送 header 信息不带任何 body 信息**；HTTP 1.0 中，存在一些浪费带宽的现象，例如客户端只是需要某个对象的一部分，而服务器却将整个对象送过来了，并且不支持断点续传功能，HTTP 1.1 则在请求头引入了 **range** 头域，它允许只请求资源的某个部分，即返回码是206（Partial Content），这样就方便了开发者自由的选择以便于充分利用带宽和连接。

3. **Host头**：在 HTTP 1.0 中认为每台服务器都绑定一个唯一的IP地址，因此，请求消息中的URL并没有传递主机名（hostname）。但随着虚拟主机技术的发展，在一台物理服务器上可以存在多个虚拟主机（Multi-homed Web Servers），并且它们共享一个IP地址。HTTP1.1的请求消息和响应消息都应支持Host头域，且请求消息中如果没有Host头域会报告一个错误（400 Bad Request）。

4. **缓存处理**：在 HTTP 1.0 中主要使用 header 里的 **If-Modified-Since、Expires** 来做为缓存判断的标准，HTTP 1.1 则引入了更多的缓存控制策略例如 **Entity tag、If-Unmodified-Since、If-Match、If-None-Match** 等更多可供选择的缓存头来控制缓存策略

5. **错误通知的管理**：在 HTTP 1.1 中新增了 24 个错误状态响应码，如 409（Conflict）表示请求的资源与资源的当前状态发生冲突；410（Gone）表示服务器上的某个资源被永久性的删除。


## HTTP 1.1 与HTTP 2.0 有什么区别？

1. **二进制格式**：HTTP 1.x 的解析是基于文本。基于文本协议的格式解析存在天然缺陷，文本的表现形式有多样性，要做到健壮性考虑的场景必然很多，二进制则不同，只认 0 和 1 的组合。基于这种考虑 HTTP 2.0 的协议解析决定采用二进制格式，实现方便且健壮

2. **多路复用**：HTTP 2.0 使用了多路复用的技术，做到同一个连接并发处理多个请求，而且并发请求的数量比 HTTP 1.1 大了好几个数量级

3. **Header 压缩**：HTTP 1.1 不支持 Header 数据的压缩，HTTP 2.0 使用 HPACK(Header Compression for HTTP/2 RFC 7541) 算法对 Header 的数据进行压缩，这样数据体积小了，在网络上传输就会更快。HTTP 1.x 的 header 带有大量信息，而且每次都要重复发送，HTTP 2.0 使用 encoder 来减少需要传输的 header 大小，通讯双方各自 cache 一份 header fields 表，既避免了重复 header 的传输，又减小了需要传输的大小

4. **服务器推送**：同 SPDY 一样，HTTP 2.0 也具有 server push 功能。例如我的网页有一个 sytle.css 的请求，在客户端收到 sytle.css 数据的同时，服务端会将 sytle.js 的文件推送给客户端，当客户端再次尝试获取 style.js 时就可以直接从缓存中获取到

## 链接
[掘金：HTTP1.0、HTTP1.1 和 HTTP2.0 的区别（好文章）](https://juejin.im/entry/5981c5df518825359a2b9476)

[CSDN：HTTP1.0 HTTP 1.1 HTTP 2.0主要区别](https://blog.csdn.net/linsongbin1/article/details/54980801)

[StackOverflow：HTTP 1.0 vs 1.1](https://stackoverflow.com/questions/246859/http-1-0-vs-1-1)

[简书：HPACK的由来（HTTP2.0压缩算法）](https://www.jianshu.com/p/f44b930cfcac)

[博客园：理解HTTP之keep-alive](https://www.cnblogs.com/jtlgb/p/8805319.html)

[知乎：HTTP/2 相比 1.0 有哪些重大改进？](https://www.zhihu.com/question/34074946/answer/75364178)

[Http 2.0 演示](https://http2.akamai.com/demo)
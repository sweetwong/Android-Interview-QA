## 如何进行WebView的性能优化
#### Android端

1. **为 WebView 单开一个进程**，通过 AIDL 与应用的主进程进行通讯

2. **将 js 本地化打包进 apk中**，通过 shouildInterceptRequest 拦截加载，前端也配合用懒加载 js 脚本；

3. 采用 **X5WebView** 内核，增加版本兼容性的错误，提升速度；

4. 不在 xml 中创建 WebView，而是 **动态地创建**，引用 applicationContext，再加入到布局；这个问题是，onJsAlert 会失败

5. 因为 WebView 首次加载特别慢，**在空闲的时候创建一次 WebView 再销毁**，来加快 WebView 加载的速度

6. WebSettings 的 setCacheMode() 缓存模式，**在无网络的时候设置为 LOAD_CACHE_ONLY，有网络时设置为 LOAD_DEFAULT**，达到离线加载的结果

7. 在 WebSettings 几个重要的**缓存**功能都打开

#### 其他端

1. 让前端，使用懒加载 js 脚本

2. 使用 CDN 服务

3. 前端图片采用 webp

## WebView 的原理是什么？（Java 如何实现和 JavaScript 通讯的？）


待完成...


## 如何解决 WebView 内存泄漏的问题？

1. 为 WebView 单独开一个进程

2. 不在 XML 中定义 WebView，每次使用前 new 出来，传入 applicationContext


## 使用 WebView 的过程中遇到哪些难点？

1. 需要处理登录状态同步的问题，用户在客户端登录，需要把登录状态同步到 WebView，避免二次登录；通过 OkHttp 的 CookieJar 获取到 Cookie，保存到 SharedPreferences，然后通过 CookieSyncManager 同步到 WebView
2. 对于 WebView 内存泄露的问题，采用了两个措施，如上述
3. 关于提高 WebView 的首屏加载速度，采用了 js 本地化的方案

## Android和JS交互有哪些方式？

[Android和JS交互有哪些方式？](../普通/Android和JS交互有哪些方式？.md)


## 链接
[QQ：70%以上业务由H5开发，手机QQ Hybrid 的架构如何优化演进？（深入）](https://mp.weixin.qq.com/s/evzDnTsHrAr2b9jcevwBzA?)

[美团：WebView性能、体验分析与优化（深入）](https://tech.meituan.com/2017/06/09/webviewperf.html)

[掘金：Android WebView：性能优化不得不说的事](https://juejin.im/entry/57d6434067f3560057e50b20)

[简书：WebView加载速度优化](https://www.jianshu.com/p/427600ca2107)

[简书：Android WebView独立进程解决方案](https://www.jianshu.com/p/b66c225c19e2)


[CSDN：优化Webview加载速度 TBS\(腾讯浏览服务X5内核\) | VasSonic\(提升H5首屏加载速度\)](https://blog.csdn.net/u012982629/article/details/81357154)


[CSDN：实战 | 封装解决WebView的那些坑](https://blog.csdn.net/developandroid/article/details/73280151)

[简书：Android-X5WebView封装（Cookie管理、进度监听、适配8.1系统等策略）（踩坑系列）](https://www.jianshu.com/p/88084a66c256)
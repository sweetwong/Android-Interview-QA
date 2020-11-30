`第三方库`、`网络`

## 回答要点

1. **动态代理**，并不是为了代理某个方法，而是直接通过动态代理获取到 Method 的反射对象
2. **解析注解**，生成一个 ServiceMethod
3. 会通过 **适配器模式** 返回值的不同适配不同的 CallFactory


## 简单回答

Retrofit 通过 **动态代理**，创建声明 service 接口的实现对象。当我们调用 service 的方法时候会执行动态代理 InvocationHandler 的 invoke 方法。在这方法中：首先，通过解析注解把转换成 ServiceMethod；然后，通过 ServiceMethod 获取到 OkHttpCall 对象，这是一个 OKHttp 请求，实际调用 OkHttp 的网络请求方法就在该类中，之后会通过适配器把这个请求适配成不同类型，例如 RxJava、AndroidCall、Kotlin 的 suspend 函数、Java 8，最后通过 OkHttp 进行网络请求，并返回对应适配类型的回调。

## 设计模式
- 代理模式（创建 ServiceMethod 的时候）
- 建造者模式（创建 Retrofit 的时候）
- 外观模式（Retrofit 的封装）
- 适配器模式（适配不同类型的网络请求，比如 RxJava、2.6.0 以后还适配了 suspend）
- 策略模式

## 原理图

<img src="../assets/Retrofit原理图.png"  />

## 链接
[拆轮子系列: 拆Retrofit](https://blog.piasy.com/2016/06/25/Understand-Retrofit/index.html)

[Retrofit的实现与原理](https://www.kancloud.cn/aslai/interview-guide/1126403)

[解决Retrofit多BaseUrl及运行时动态改变BaseUrl?（终极好文）](https://www.jianshu.com/p/2919bdb8d09a)
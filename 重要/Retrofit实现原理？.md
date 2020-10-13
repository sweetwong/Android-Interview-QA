## 回答要点
1. 动态代理，并不是为了代理某个方法，而是直接通过动态代理获取到Method的反射对象
2. 解析注解，生成一个ServiceMethod
3. 会通过返回值的不同适配不同的CallFactory


## 简单回答

Retrofit通过**动态代理**，创建声明service接口的实现对象。当我们调用service的方法时候会执行动态代理InvocationHandler的invoke方法。在这方法中：首先，通过解析注解把转换成ServiceMethod；然后，通过ServiceMethod获取到OkHttpCall对象，这是一个OKHttp请求，实际调用OkHttp的网络请求方法就在该类中，之后会通过适配器把这个请求适配成不同类型，例如RxJava、AndroidCall、Kotlin的suspend函数、Java8，最后通过OkHttp进行网络请求，并返回对应适配类型的回调。

## 设计模式
代理模式（创建ServiceMethod的时候）
建造者模式（创建Retrofit的时候）
外观模式（Retrofit的封装）
适配器模式（适配不同类型的网络请求，比如RxJava、2.6.0以后还适配了suspend）
策略模式

## 原理图

<img src="../assets/Retrofit原理图.png"  />

## 链接
[拆轮子系列: 拆Retrofit:](https://blog.piasy.com/2016/06/25/Understand-Retrofit/index.html)
[Retrofit的实现与原理](https://www.kancloud.cn/aslai/interview-guide/1126403)
[解决Retrofit多BaseUrl及运行时动态改变BaseUrl?（终极好文）](https://www.jianshu.com/p/2919bdb8d09a)
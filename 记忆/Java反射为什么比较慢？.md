## 原因

1. Method的invoke方法会对参数做**装箱和拆箱**操作

2. 需要检查方法**可见性**

3. 需要**校验参数**

4. 反射方法**难以内联**

5. **JIT无法优化**

## 链接

[直面底层：说 Java 反射效率低，你知道原因吗？](https://mp.weixin.qq.com/s?__biz=MzAxMTI4MTkwNQ==&mid=2650832644&idx=2&sn=5183ed4c930e755702df1f26f6599efc&chksm=80b7ab9ab7c0228c5c2ea7359ccb7c17d562c8f3d8cd2301b0987a11de1435d372ff225375c6&scene=21#wechat_redirect)

[大家都说 Java 反射效率低，你知道原因在哪里么](https://juejin.im/post/6844903965725818887)
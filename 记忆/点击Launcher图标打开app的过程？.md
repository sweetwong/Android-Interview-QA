### 过程

1. 点击图标后，Launcher进程通过Binder IPC向system_server的AMS发起打开Activity的请求（**launcher -> system_server**）
2. AMS会**通过Socket**向zygote请求创建新进程（**system_server -> zygote**）
3. zygote使用fork()创建进程并设置好，随后进入ActivityThread的main(String[] args)方法（**zygote -> app**）
4. ActivityThread初始化Looper，Handler等，随后进入死循环等待由AMS发来的消息（**app -> system_server**）
5. AMS发送消息，让应用程序回调各组件的生命周期（**system_server -> app**）

后续的可以参考问题，[Activity的加载流程](
https://app.yinxiang.com/shard/s39/nl/26801101/fb510be9-f411-4aa2-8fb2-7d60afab18e7?title=Activity%E4%BB%8EActivityThread%E5%BC%80%E5%A7%8B%E7%9A%84%E5%8A%A0%E8%BD%BD%E7%9A%84%E6%B5%81%E7%A8%8B%EF%BC%9F)

### 链接
[Github：每日一题](https://github.com/Moosphan/Android-Daily-Interview/issues/24)
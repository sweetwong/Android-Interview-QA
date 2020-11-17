`Framework 源码`

## 过程

1. 点击图标后，Launcher 进程通过 Binder IPC 向 system_serve r的 AMS 发起打开 Activity 的请求（**launcher -> system_server**）
2. AMS 会**通过 Socket** 向 zygote 请求创建新进程（**system_server -> zygote**）
3. zygote 使用 fork() 创建进程并设置好，随后进入 ActivityThread 的 main(String[] args) 方法（**zygote -> app**）
4. ActivityThread 初始化 Looper、Handler 等，随后进入死循环等待由 AMS 发来的消息（**app -> system_server**）
5. AMS 发送消息，让应用程序回调各组件的生命周期（**system_server -> app**）

后续的可以参考问题，[Activity的加载流程](
https://app.yinxiang.com/shard/s39/nl/26801101/fb510be9-f411-4aa2-8fb2-7d60afab18e7?title=Activity%E4%BB%8EActivityThread%E5%BC%80%E5%A7%8B%E7%9A%84%E5%8A%A0%E8%BD%BD%E7%9A%84%E6%B5%81%E7%A8%8B%EF%BC%9F)

## 链接
[Github：每日一题](https://github.com/Moosphan/Android-Daily-Interview/issues/24)
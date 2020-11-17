`Framework 源码`

## 流程

1. Linux 启动，启动 init 进程，查看 init.rc 的配置文件，init 进程 fork 出 Zygote 进程（包括 ServiceManager、SurfaceFlinger 等进程）

2. 进入 Zygote 进程入口函数 app_main.cpp 的 main()
3. 调用 AndroidRuntime.start()，启动虚拟机，注册 jni 函数，预加载系统资源，进入 Zygote 进程 ZygoteInit.java 的 main() 方法
4. fork 出 SystemService 进程
5. 进入 SystemServer 进程 SystemServer.java 的 main() 方法
6. 进入无限循环，等到 Socket 通讯，fork 出应用进程

## 注意细节
1. Linux 系统是读取 init.rc 中的配置文件启动进程，如 Zygote、ServiceManager

2. Zygote 进程的入口是 ZygoteInit.java 的 main() 方法
2. Zygote 进程与 SystemServer 通讯是通过 Socket（原因和 fork() 方法的线程有关）
3. ServiceManager


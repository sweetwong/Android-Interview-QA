## 流程
1. Linux启动，启动init进程，查看init.rc的配置文件，init进程fork出Zygote进程（包括ServiceManager、SurfaceFlinger等进程）

2. 进入Zygote进程入口函数app_main.cpp的main
3. 调用AndroidRuntime.start，启动虚拟机，注册jni函数，预加载系统资源，进入Zygote进程ZygoteInit.java的main方法
4. fork出SystemService进程
5. 进入SystemServer进程SystemServer.java的main方法
6. 进入无限循环，等到Socket通讯，fork出应用进程

## 注意细节
1. Linux系统是读取init.rc中的配置文件启动进程，如Zygote、ServiceManager

2. Zygote进程的入口是ZygoteInit.java的main方法
2. Zygote进程与SystemServer通讯是通过Socket（原因和fork()方法的线程有关）
3. ServiceManager
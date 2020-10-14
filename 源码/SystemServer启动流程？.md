## 流程
1. 进入SystemServer的main方法

2. 调用Looper.prepare方法
3. 加载系统的native资源
4. 分段启动系统服务
5. 调用Looper.loop方法进入loop循环

## 要点
1. 通过分段启动来解决系统服务的之间的依赖问题（BootStrap -> Core -> Other）

2. 系统服务与Zygote进程的通讯方式是Socket
3. 所有的系统服务都是在子线程中运行
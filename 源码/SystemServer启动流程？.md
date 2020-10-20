## 流程
1. 进入 SystemServer 的 main 方法

2. 调用 Looper.prepare 方法
3. 加载系统的 native 资源
4. **分段**启动系统服务
5. 调用 Looper.loop 方法进入 loop 循环

## 要点
1. 通过分段启动来解决系统服务的之间的依赖问题（BootStrap -> Core -> Other）

2. 系统服务与 Zygote 进程的通讯方式是 Socket
3. 所有的系统服务都是在子线程中运行
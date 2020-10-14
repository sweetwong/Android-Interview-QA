## 流程

1. 首先startActivity

2. 通过Instrumentation调用ActivityTaskManagerService（也是AMS的一部分）通过跨进程通讯，从应用进程调用到系统进程，并在系统进程中进行一些列操作

3. 在系统进程处理完成后，通过IApplicationThread也进行跨进程通讯，从系统进程回到应用进程，通过ActivityThread中的Handler处理消息

4. 最后又通过Instrumentation创建要启动的Activity，并调用创建的activity的onCreate方法

也就是说启动Activity其实是经过了两次跨进程通讯才将Activity启动起来的。

## 注意细节
1. Android Q以后把很多ActivityManagerService的逻辑移到了ActivityTaskManagerService

## 链接

[【基于Android Q】Activity启动流程源码分析【一】](https://www.jianshu.com/p/827fd152a804)

[【基于Android Q】Activity启动流程源码分析【二】](https://www.jianshu.com/p/2fd50292727d)
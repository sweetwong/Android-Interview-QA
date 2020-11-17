`Binder`

## 一次完整的IPC流程

[Binder的transact流程？](../源码/Binder的transact流程？.md)

## 优点

1. **传输效率高**：传输效率的主要影响因素是内存拷贝次数，**内存拷贝次数越少，传输效率越高**。像对于消息队列、Socket、管道这些，数据从发送方的缓存区拷贝到内核的缓存区，再从内核的缓存区拷贝到接收方的缓存区，一共拷贝了两次；而对于 Binder，数据从发送方的缓存区拷贝到内核的缓存区，而接收方的缓存区与内核的缓存区是在同一物理地址的，节省了一次拷贝的过程；

2. **实现了C/S架构**：Linux 的 IPC 除了 Socket 之外都不是基于 C/S 架构的，Socket 主要用于网络传输效率低。Binder 基于 C/S 架构，Service 端与 Client 端独立，耦合度低，稳定性高

3. **安全性高**： 传统 Linux IPC 的接收方无法获得对方进程可靠的 UID/PID，从而无法鉴别对方身份；而Binder机制为每个进程分配了 UID/PID 且在 Binder 通信时会根据 UID/PID 进行有效性检测。


## 实现原理

1. Linux 的**可加载内核模块**（Loadable Kernel Module，LKM）的机制

2. **内存映射**, mmap()（内存映射简单的讲就是将用户空间的一块内存区域映射到内核空间。映射关系建立后，用户对这块内存区域的修改可以直接反应到内核空间；反之内核空间对这段区域的修改也能直接反应到用户空间。）


## 一次完整的Binder IPC过程

1. 首先 Binder 驱动在内核空间创建一个数据接收缓存区

2. 接着在内核空间开辟一块内核缓存区，建立内核缓存区和内核中数据接收缓存区之间的映射关系，以及内核中数据接收缓存区和接收进程用户空间地址的映射关系

3. 发送方进程通过系统调用 copyfromuser() 将数据 copy 到内核中的内核缓存区，由于内核缓存区和接收进程的用户空间存在内存映射，因此也就相当于把数据发送到了接收进程的用户空间，这样便完成了一次进程间的通信。

## Binder通信模型（可以类比网络模型）

1. Client（类似客户端，用户空间，进程1）

2. Service（类似服务器， 用户空间，进程2）

3. ServiceManager（类似DNS服务器，用户空间，进程3）

4. Binder驱动（类似路由器，内核空间）


## Binder模型中的通信

1. Server向ServiceManager注册服务（保存DNS地址）

2. Client向ServiceManager查询Service地址（DNS查询）

3. Client得到ServiceManager返回的Service地址（返回DNS地址）

4. Client使用服务（请求服务器）

5. Service向Client返回数据（返回数据）

注意：这之间所有的通讯都是通过Binder完成的

## 链接

[知乎：写给 Android 应用工程师的 Binder 原理剖析](https://zhuanlan.zhihu.com/p/35519585)

[CSDN：linux内存映射mmap原理分析](https://blog.csdn.net/yusiguyuan/article/details/23388771)
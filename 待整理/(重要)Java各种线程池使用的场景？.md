## 线程池主要流程

```mermaid
graph LR
    A[提交任务] --> B{核心线程是否已满}
    B --> |是| C{队列是否已满}
    C --> |是| D{线程池是否已满}
    D --> |是| E[按照策略执行无法处理的任务]

    B --> |否| F[创建线程执行任务]
    C --> |否| G[将任务存储在队列李]
    D --> |否| H[创建线程执行任务]
            
```



## 链接

[美团：Java线程池实现原理及其在美团业务中的实践](https://tech.meituan.com/2020/04/02/java-pooling-pratice-in-meituan.html)

[如何优雅的使用和理解线程池](https://crossoverjie.top/2018/07/29/java-senior/ThreadPool/)


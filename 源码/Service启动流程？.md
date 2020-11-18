`Framework 源码`、`四大组件`

## 简单流程图

```mermaid
graph LR
1{"Service启动没？"}-->|No|2{"进程启动没？"}
1-->|Yes|3["发送指令<br>回调onStartCommand()"]
2-->|Yes|4["启动Service"]
2-->|No|5["启动进程"]
5-->4
4-->3

```



## 方法调用流程图

```mermaid
graph TD
1["Context.startService()"]-->2["ContextImpl.startServiceCommon()"]
2-->|"通过AIDL跨进程通信"|3["AMS.startService()"]
3-->4["ActiveServices.startServiceLocked()"]
4-->5["ActiveServices.retrieveServiceLocked()"]
5-->|"获取到ServiceRecord"|4
4-->6["ActiveServices.startServiceInnerLocked()"]
6-->7["ActiveServices.bringUpServiceLocked()"]
7-->8{"分为4种情况"}
8-->|"第一种情况<br>Service已经启动了"|9["ActiveServices.sendServiceArgsLocked()<br>直接回调onStartCommand()"]
8-->|"第二种情况<br>已经创建了进程，但是Service没启动"|10["ActiveServices.realStartServiceLocked()<br>真正启动服务"]
8-->|"第三种情况<br>还没有创建进程"|11["mAm.startProcessLocked()<br>启动进程"]
8-->|"第四种情况<br>已经创建了进程，但还未就绪"|12["mPendingServices.add()<br>将服务添加到待启动"]
10-->|"通过AIDL让应用创建Service"|13["app.thread.scheduleCreateService()"]
13-->|"回调onStartCommand()"|14[ActiveServices.sendServiceArgsLocked]
```

### 参与者

ContextImpl：客户端真正调用 startService() 的地方

AMS：服务端给客户端的接口

ActiveServices：服务端真正处理 Service 调度的地方

## 链接

[慕课网：说说service的启动原理](https://coding.imooc.com/lesson/340.html#mid=24590)
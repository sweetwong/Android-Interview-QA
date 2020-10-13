## IntentService实现原理
在Service中封装了HandlerThread相关的操作

## IntentService与Service的区别在于

1. IntentService运行在**工作线程,** 而Service运行在主线程

2. <u>不需要stopSelf()</u>在完成所有工作后, 将**自己取消**

3. <u>不需要重写onStartCommand()而是重写**onHandleIntent()**</u>

4. 一次处理一个请求，**串行操作**，当有多个请求的时候会加入队列

## 其他描述

This is a subclass of Service that uses a worker thread to handle all of the start requests, one at a time. This is the best option if you don't require that your service handle multiple requests simultaneously. Implement onHandleIntent(), which receives the intent for each start request so that you can complete the background work.

IntentService是继承于Service并处理异步请求的一个类，在IntentService内有一个工作线程来处理耗时操作，启动IntentService的方式和启动传统Service一样，同时，当任务执行完后，IntentService会自动停止，而不需要我们去手动控制。另外，可以启动IntentService多次，而每一个耗时操作会以工作队列的方式在IntentService的onHandleIntent回调方法中执行，并且，每次只会执行一个工作线程，执行完第一个再执行第二个，以此类推

注意: 在8.0或更高的版本以后, 应该用**JobIntentService**来代替IntentService, 用法和IntentService一样
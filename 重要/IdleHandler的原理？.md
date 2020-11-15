## 调用时机？

在 messageQueue.next() 方法中，messageQueue.nativePollOnce() 返回之后，且当前没有消息可以分发了

nativePollOnce() 返回的三个条件：

1. 成功获取到 Message
2. 超时
3. 出错

## 注意点

- 调用 addIdleHandler() 后，如果当前是闲置的状态，不会立刻触发，而是等到下一次闲置状态才触发，解决的方法是 post() 一个空的 Runnable
- `return true` 表示 keep，会一直生效；`return false` 表示不 keep，只生效一次（通常使用这种）

## 适用场景

1. 延迟执行（App 启动优化，处理一些不重要的任务）
2. 批量任务

## 链接

[慕课网：说说IdleHandler的原理？](https://coding.imooc.com/lesson/340.html#mid=24612)
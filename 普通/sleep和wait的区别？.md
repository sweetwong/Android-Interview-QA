## 区别
![](../assets/sleep和wait的区别.png)

## 注意
1. sleep不会释放锁，wait会释放锁

2. wait和notify必须在synchronized中调用
3. sleep必须指定时间，进入超时等待的状态；而wait可以指定时间也可以不指定时间

## 链接
[面试题之wait\(\)和sleep\(\)方法区别](https://blog.csdn.net/Weixiaohuai/article/details/104235903)
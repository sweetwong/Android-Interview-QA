`Java 并发`

- 从语义角度来讲， 一个线程调用了`wait()`之后， 必然需要由另外一个线程调用`notify()`来唤醒该线程， 所以本质上， `wait()`与`notify()`的成对使用， 是一种线程间的通信手段。
- 进一步分析， `wait()` 操作的调用必然是在等待某种条件的成立， 而条件的成立必然是由其他的线程来完成的。 所以实际上， 我们调用 `wait()` 的时候， 实际上希望达到如下的效果

```Java
// 线程A 的代码
while(!condition){ // 不能使用 if , 因为存在一些特殊情况， 使得线程没有收到 notify 时也能退出等待状态
    wait();
}
// do something
```

```Java
// 线程 B 的代码
if(!condition){ 
	// do something ...
    condition = true;
    notify();
}
```

- 现在考虑， 如果`wait()` 和 `notify()` 的操作没有相应的同步机制， 则会发生如下情况

1. 【线程A】 进入了 while 循环后（通过了 `!condition` 判断条件， 但尚未执行 `wait` 方法）, CPU 时间片耗尽， CPU 开始执行线程B的代码
2. 【线程B】 执行完毕了 `condition = true; notify();` 的操作， 此时【线程A】的 `wait()` 操作尚未被执行， `notify()` 操作没有产生任何效果
3. 【线程A】执行`wait()` 操作， 进入等待状态，如果没有额外的 notify() 操作， 该线程将持续在 `condition = true` 的情形下， 持续处于等待状态得不到执行。

由此看出， 在使用 wait() 和 notify() 这种会挂起线程的操作时， 我们需要一种同步机制保证， `condition` 的检查与 `wait()` 操作， 以及 `condition` 的更新与 `notify()` 是互斥的。

- 那是否简单的将之前的代码包裹在一个 synchronized 代码块中就可以满足需求呢？ 像下面这样。

```Java
// 线程A 的代码
synchronized(obj_A)
{
	while(!condition){ 
	    wait();
	}
	// do something 
}
```
```Java
// 线程 B 的代码
synchronized(obj_A)
{
	if(!condition){ 
		// do something ...
	    condition = true;
	    notify();
	}
}
```

- 乍一看， 上述的代码可以解决问题， 但是仔细分析一下， 由于`wait()` 操作会挂起当前线程， 那么必然需要在挂起前释放掉 `obj_A` 的锁， 但如果 `obj_A` 允许是任意对象， `wait()` 函数作为一个没有参数输入的方法，无从得知应该释放哪个对象的锁 。于是很自然的， 语法就会被设计成 java 现在的样子。即基于对象的 `wait()` 与 `notify()` 的调用， 必须先获得该对象的锁。
- 正确的用法示例如下

```Java
// 线程 A 的代码
synchronized(obj_A)
{
	while(!condition){ 
	    obj_A.wait();
	}
	// do something 
}
```

```Java
// 线程 B 的代码
synchronized(obj_A)
{
	if(!condition){ 
		// do something ...
	    condition = true;
	    obj_A.notify();
	}
}
```

## 链接

[CSDN：阿里巴巴面试题： 为什么wait()和notify()需要搭配synchonized关键字使用](https://blog.csdn.net/lengxiao1993/article/details/52296220)
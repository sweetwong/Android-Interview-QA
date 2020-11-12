# 介绍

ThreadLocal是通过空间换取时间，从而实现每隔线程当中都会有一个变量的副本，这样每个线程都会操作该副本，从而完全规避了多线程的并发问题。ThreadLocal与当前线程紧密相关。



# 从源码分析

## 类的关系

-  [ThreadLocalMap](https://cs.android.com/android/platform/superproject/+/master:libcore/ojluni/annotations/hiddenapi/java/lang/ThreadLocal.java;bpv=1;bpt=1;l=113?q=ThreadLocalMap&gsn=ThreadLocalMap&gs=kythe%3A%2F%2Fopenjdk11%3Flang%3Djava%3Fpath%3Djava.lang.ThreadLocal.ThreadLocalMap%23b5eb23af78f2dca77fcac44ae6009c3683fcf6ebf94248af69ffe7ef4c91fb48) 为 ThreadLocal 的静态内部类。
- 在Thread类中，持有着一个ThreadLocalMap的变量。（[ThreadLocal](https://cs.android.com/android/platform/superproject/+/master:libcore/ojluni/src/main/java/java/lang/Thread.java;bpv=1;bpt=1;l=207?q=ThreadLocal.java&gsn=ThreadLocal&gs=kythe%3A%2F%2Fopenjdk11%3Flang%3Djava%3Fpath%3Djava.lang.ThreadLocal%236a109534fd022c2a058f701f1727353708003245e9836e634cf4d1c5a2d74610).[ThreadLocalMap](https://cs.android.com/android/platform/superproject/+/master:libcore/ojluni/src/main/java/java/lang/Thread.java;bpv=1;bpt=1;l=207?q=ThreadLocal.java&gsn=ThreadLocalMap&gs=kythe%3A%2F%2Fopenjdk11%3Flang%3Djava%3Fpath%3Djava.lang.ThreadLocal.ThreadLocalMap%23b5eb23af78f2dca77fcac44ae6009c3683fcf6ebf94248af69ffe7ef4c91fb48) [threadLocals](https://cs.android.com/android/platform/superproject/+/master:libcore/ojluni/src/main/java/java/lang/Thread.java;bpv=1;bpt=1;l=201?q=ThreadLocal.java&gsn=threadLocals&gs=kythe%3A%2F%2Fandroid.googlesource.com%2Fplatform%2Fsuperproject%3Flang%3Djava%3Fpath%3Djava.lang.Thread%232ff56c692f8bdf7d061b330a99c5e6d7199846ff8ebc277d0f0f661cfccb4e97&gs=kythe%3A%2F%2Fopenjdk11%3Flang%3Djava%3Fpath%3Djava.lang.Thread%232ff56c692f8bdf7d061b330a99c5e6d7199846ff8ebc277d0f0f661cfccb4e97)）
- [Entry](https://cs.android.com/android/platform/superproject/+/master:libcore/ojluni/src/main/java/java/lang/ThreadLocal.java;bpv=1;bpt=1;l=308?q=ThreadLocal.java&gsn=Entry&gs=kythe%3A%2F%2Fandroid.googlesource.com%2Fplatform%2Fsuperproject%3Flang%3Djava%3Fpath%3Djava.lang.ThreadLocal.ThreadLocalMap.Entry%238610309823795d3cf0974fb283420f5262edef3f3102958667ffb887e45d84ce&gs=kythe%3A%2F%2Fopenjdk11%3Flang%3Djava%3Fpath%3Djava.lang.ThreadLocal.ThreadLocalMap.Entry%238610309823795d3cf0974fb283420f5262edef3f3102958667ffb887e45d84ce)类为ThreadLocalMap类的静态内部类，并继承了[WeakReference](https://cs.android.com/android/platform/superproject/+/master:libcore/ojluni/src/main/java/java/lang/ref/WeakReference.java;drc=master;bpv=1;bpt=1;l=48)<[ThreadLocal](https://cs.android.com/android/platform/superproject/+/master:libcore/ojluni/src/main/java/java/lang/ThreadLocal.java;bpv=1;bpt=1;l=308?q=ThreadLocal.java&gsn=ThreadLocal&gs=kythe%3A%2F%2Fopenjdk11%3Flang%3Djava%3Fpath%3Djava.lang.ThreadLocal%235cb9b5229390f26fbdf6bc6771f78524e87a1d73aa847b866b58474066c3e8dd)<?>>。
- ThreadLocalMap里面维护着一个Entry数组，初始值大小为16；

private static final [int](https://cs.android.com/android/platform/superproject/+/master:libcore/ojluni/src/main/java/java/lang/ThreadLocal.java;bpv=1;bpt=1;l=321?q=ThreadLocal.java&gsn=int&gs=kythe%3A%3Flang%3Djava%23int%23builtin) [INITIAL_CAPACITY](https://cs.android.com/android/platform/superproject/+/master:libcore/ojluni/src/main/java/java/lang/ThreadLocal.java;bpv=1;bpt=1;l=321?q=ThreadLocal.java&gsn=INITIAL_CAPACITY&gs=kythe%3A%2F%2Fandroid.googlesource.com%2Fplatform%2Fsuperproject%3Flang%3Djava%3Fpath%3Djava.lang.ThreadLocal.ThreadLocalMap%237aec70917f5ef0d711e02c7c06241869b28c1ffb87415f7ee96e699043dfb010&gs=kythe%3A%2F%2Fopenjdk11%3Flang%3Djava%3Fpath%3Djava.lang.ThreadLocal.ThreadLocalMap%237aec70917f5ef0d711e02c7c06241869b28c1ffb87415f7ee96e699043dfb010) = 16;

private [Entry](https://cs.android.com/android/platform/superproject/+/master:libcore/ojluni/src/main/java/java/lang/ThreadLocal.java;bpv=1;bpt=1;l=327?q=ThreadLocal.java&gsn=Entry&gs=kythe%3A%2F%2Fopenjdk11%3Flang%3Djava%3Fpath%3Djava.lang.ThreadLocal.ThreadLocalMap.Entry%238610309823795d3cf0974fb283420f5262edef3f3102958667ffb887e45d84ce)[] [table](https://cs.android.com/android/platform/superproject/+/master:libcore/ojluni/src/main/java/java/lang/ThreadLocal.java;bpv=1;bpt=1;l=327?q=ThreadLocal.java&gsn=table&gs=kythe%3A%2F%2Fandroid.googlesource.com%2Fplatform%2Fsuperproject%3Flang%3Djava%3Fpath%3Djava.lang.ThreadLocal.ThreadLocalMap%23e6a632b0fcff933c0d7966f18b0f5b8139285870126ac97eec7a5b3e0eac80fb&gs=kythe%3A%2F%2Fopenjdk11%3Flang%3Djava%3Fpath%3Djava.lang.ThreadLocal.ThreadLocalMap%23e6a632b0fcff933c0d7966f18b0f5b8139285870126ac97eec7a5b3e0eac80fb) = new [Entry](https://cs.android.com/android/platform/superproject/+/master:libcore/ojluni/src/main/java/java/lang/ThreadLocal.java;bpv=1;bpt=1;l=366?q=ThreadLocal.java&gsn=Entry&gs=kythe%3A%2F%2Fopenjdk11%3Flang%3Djava%3Fpath%3Djava.lang.ThreadLocal.ThreadLocalMap.Entry%238610309823795d3cf0974fb283420f5262edef3f3102958667ffb887e45d84ce)[[INITIAL_CAPACITY](https://cs.android.com/android/platform/superproject/+/master:libcore/ojluni/src/main/java/java/lang/ThreadLocal.java;bpv=1;bpt=1;l=366?q=ThreadLocal.java&gsn=INITIAL_CAPACITY&gs=kythe%3A%2F%2Fopenjdk11%3Flang%3Djava%3Fpath%3Djava.lang.ThreadLocal.ThreadLocalMap%237aec70917f5ef0d711e02c7c06241869b28c1ffb87415f7ee96e699043dfb010)];

Entry里面保存着键值对，value为我们put的时候传入的值，key为当前的ThreadLocal。



## get方法

```
    static class Entry extends WeakReference<ThreadLocal<?>> {
        /** The value associated with this ThreadLocal. */
            Object value;

            Entry(ThreadLocal<?> k, Object v) {
                super(k);
                value = v;
            }
        }



    /**
     * Returns the value in the current thread's copy of this
     * thread-local variable.  If the variable has no value for the
     * current thread, it is first initialized to the value returned
     * by an invocation of the {@link #initialValue} method.
     *
     * @return the current thread's value of this thread-local
     */
    public T get() {
        Thread t = Thread.currentThread();
        ThreadLocalMap map = getMap(t);
        if (map != null) {
            ThreadLocalMap.Entry e = map.getEntry(this);
            if (e != null) {
                @SuppressWarnings("unchecked")
                T result = (T)e.value;
                return result;
            }
        }
        return setInitialValue();
    }


    /**
     * Get the map associated with a ThreadLocal. Overridden in
     * InheritableThreadLocal.
     *
     * @param  t the current thread
     * @return the map
     */
    ThreadLocalMap getMap(Thread t) {
        return t.threadLocals;
    }


    /**
     * Variant of set() to establish initialValue. Used instead
     * of set() in case user has overridden the set() method.
     *
     * @return the initial value
     */
    private T setInitialValue() {
        T value = initialValue();
        Thread t = Thread.currentThread();
        ThreadLocalMap map = getMap(t);
        if (map != null)
            map.set(this, value);
        else
            createMap(t, value);
        return value;
    }

   /**
     * Create the map associated with a ThreadLocal. Overridden in
     * InheritableThreadLocal.
     *
     * @param t the current thread
     * @param firstValue value for the initial entry of the map
     */
    void createMap(Thread t, T firstValue) {
        t.threadLocals = new ThreadLocalMap(this, firstValue);
    }
```

1. 首先获取到当前的线程。
2. 从当前的线程中获取到对应的ThreadLocalMap。
3. 如果ThreadLocalMap不为空，就以ThreadLocal对象为key，去计算他在Entry数据中的位置，获取到之前保存的value。
4. 如果ThreadLocalMap为空，则创建一个新的ThreadLocalMap对象，并赋值给Thread中的threadLocals变量。





## set方法





```
    /**
     * Sets the current thread's copy of this thread-local variable
     * to the specified value.  Most subclasses will have no need to
     * override this method, relying solely on the {@link #initialValue}
     * method to set the values of thread-locals.
     *
     * @param value the value to be stored in the current thread's copy of
     *        this thread-local.
     */
    public void set(T value) {
        Thread t = Thread.currentThread();
        ThreadLocalMap map = getMap(t);
        if (map != null)
            map.set(this, value);
        else
            createMap(t, value);
    }

   /**
     * Create the map associated with a ThreadLocal. Overridden in
     * InheritableThreadLocal.
     *
     * @param t the current thread
     * @param firstValue value for the initial entry of the map
     */
    void createMap(Thread t, T firstValue) {
        t.threadLocals = new ThreadLocalMap(this, firstValue);
    }
```



# 原理图

![image.png](https://cdn.nlark.com/yuque/0/2020/png/2669602/1605106001379-ce7ec835-fb9a-4c7e-8caf-a75f8439b7fa.png)





# 总结

- 一个Thread都维护着一个ThreadLcoalMap对象。
- 一个ThreadLocalMap可以拥有多个Entry，所以一个ThreadLocalMap可以保存多个不同ThreadLocal对象。





# 补充

## Java中存在的四种类型的引用

1. 强引用（strong）：不会被垃圾回收。
2. 软引用（soft）：内存不足的时候，GC才会将软引用指向的对象回收。
3. 弱引用（weak）：会再第二次GC的时候，被GC回收器会将弱引用指向的对象回收。（前提是没有其他强引用指向该对象）
4. 虚引用（phantom）：对象被回收的时候，会收到通知，并不会指向任何对象。



如果一个对象有一个强引用和一个弱引用指向它，那也是不会被回收的。



![image.png](https://cdn.nlark.com/yuque/0/2020/png/2669602/1605144290215-cbc90c76-dbed-4c4e-9c10-bdf8bf1ca9d9.png)

当创建mThreadLocal变量的类被销毁后。栈指向堆中的ThreadLocal引用就会被切断，这时候指向堆中的ThreadLocal的只有table数组中的Entry的key。

但是由于该Entry为弱引用，所以下次GC扫描的时候，会将堆中的ThreadLocal给回收，此时Entry中的key将会为null。

![image.png](https://cdn.nlark.com/yuque/0/2020/png/2669602/1605144476158-c80a3c67-3be8-4c61-b2b1-cccf792835ee.png)



如果不进行处理，在table中将会出现越来越多的key为null的Entry。所以针对这种情况，ThreadLocal有进行相应的处理。

这里以ThreadLocal的set方法来说明：

```
   public void set(T value) {
        Thread t = Thread.currentThread();
        ThreadLocalMap map = getMap(t);
        if (map != null)
            map.set(this, value);
        else
            createMap(t, value);
    }
```

当线程中的ThreadLocalMap不为空。则会调用ThreadLocalMap的set方法。

```
        private void set(ThreadLocal<?> key, Object value) {

            // We don't use a fast path as with get() because it is at
            // least as common to use set() to create new entries as
            // it is to replace existing ones, in which case, a fast
            // path would fail more often than not.

            Entry[] tab = table;
            int len = tab.length;
            int i = key.threadLocalHashCode & (len-1);

            for (Entry e = tab[i];
                 e != null;
                 e = tab[i = nextIndex(i, len)]) {
                ThreadLocal<?> k = e.get();

                if (k == key) {
                    e.value = value;
                    return;
                }

                if (k == null) {
                    replaceStaleEntry(key, value, i);
                    return;
                }
            }

            tab[i] = new Entry(key, value);
            int sz = ++size;
            if (!cleanSomeSlots(i, sz) && sz >= threshold)
                rehash();
        }
```

遍历Entry数组，如果发现里面的ThreadLocal为null，则调用replaceStaleEntry

```
        private void replaceStaleEntry(ThreadLocal<?> key, Object value,
                                       int staleSlot) {
            Entry[] tab = table;
            int len = tab.length;
            Entry e;
            
            ...省略...
            expungeStaleEntry(slotToExpunge)
            ...省略...
        }
     private int expungeStaleEntry(int staleSlot) {
            Entry[] tab = table;
            int len = tab.length;

            // expunge entry at staleSlot
            tab[staleSlot].value = null;
            tab[staleSlot] = null;
            size--;

            // Rehash until we encounter null
            Entry e;
            int i;
            for (i = nextIndex(staleSlot, len);
                 (e = tab[i]) != null;
                 i = nextIndex(i, len)) {
                ThreadLocal<?> k = e.get();
                if (k == null) {
                    e.value = null;
                    tab[i] = null;
                    size--;
                } else {
                    int h = k.threadLocalHashCode & (len - 1);
                    if (h != i) {
                        tab[i] = null;

                        // Unlike Knuth 6.4 Algorithm R, we must scan until
                        // null because multiple entries could have been stale.
                        while (tab[h] != null)
                            h = nextIndex(h, len);
                        tab[h] = e;
                    }
                }
            }
            return i;
        }
```

对数组中的无效数据进行清除。
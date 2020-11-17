## 事件从手指点击到产生然后分发的全过程

事件分发的传递顺序：用户点击 -> 手机硬件 -> WMS  -> InputEventReceiver -> 传递到 ViewRootImpl（通过 InputEventReceiver 来接收事件通知，在 setView() 时注册监听） -> DecorView -> Activity（从这里开始常规的分发） -> PhoneWindow -> DecorView（本质上也是 ViewGroup，最顶层的 ViewGroup ） -> ViewGroup -> View

## MotionEvent事件分发

#### 三大方法

1. **dispatchTouchEvent**：它就是事件分发的重要方法。那么很明显，如果一个 MotionEvent 传递给了 View，那么 dispatchTouchEvent 方法一定会被调用。返回值：表示是否消费了当前事件 。可能是 View 本身的 onTouchEvent 方法消费，也可能是子 View 的 dispatchTouchEvent方 法中消费。返回 true 表示事件被消费，本次的事件终止。返回 false 表示 View 以及子 View 均没有消费事件，将调用父 View 的 onTouchEvent 方法

2. **onInterceptTouchEvent**：事件拦截，当一个 ViewGroup 在接到 MotionEvent 事件序列时候，首先会调用此方法判断是否需要拦截。特别注意，这是ViewGroup 特有的方法，View 并没有拦截方法返回值：是否拦截事件传递，返回 true 表示拦截了事件，那么事件将不再向下分发而是调用 View 本身的 onTouchEvent 方法。返回 false 表示不做拦截，事件将向下分发到子 View 的 dispatchTouchEvent 方法。

3. **onTouchEvent**：真正对 MotionEvent 进行处理或者说消费的方法。在 dispatchTouchEvent 进行调用。返回值：返回 true 表示事件被消费，本次的事件终止。返回 false 表示事件没有被消费，将调用父 View 的 onTouchEvent 方法

#### 伪代码

```java
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean consume = false; // 事件是否被消费
        if (onInterceptTouchEvent(ev)){ // 调用onInterceptTouchEvent判断是否拦截事件
            consume = onTouchEvent(ev); // 如果拦截则调用自身的onTouchEvent方法
        } else {
            consume = child.dispatchTouchEvent(ev); // 不拦截调用子View的dispatchTouchEvent方法
        }
        return consume; // 返回值表示事件是否被消费，true事件终止，false调用父View的onTouchEvent方法
    }
```
#### 特别强调

1. 对于 View（注意：ViewGroup 也是 View）而言，如果设置了 onTouchListener，那么 OnTouchListener 方法中的 onTouch 方法会被回调。onTouch 方法返回 true，则 onTouchEvent 方法不会被调用（onClick 事件是在 onTouchEvent 中调用）所以三者优先级是：
   **onTouch（通过 setOnTouchListener() 设置） > onTouchEvent > onClick**
2. View 的 onTouchEvent 方法默认都会消费掉事件（返回 true），除非它是不可点击的（clickable 和 longClickable 同时为 false），View 的 longClickable 默认为 false，clickable 需要区分情况，如 Button 的 clickable 默认为 true，而 TextView 的 clickable 默认为 false

## 如何解决View的事件冲突 ？ 举个开发中遇到的例子 ？

- 常见开发中事件冲突的有 ScrollView 与 RecyclerView 的滑动冲突、RecyclerView 内嵌同时滑动同一方向
- 滑动冲突的处理规则：
  - 对于由于外部滑动和内部滑动方向不一致导致的滑动冲突，可以根据滑动的方向判断谁来拦截事件。
  - 对于由于外部滑动方向和内部滑动方向一致导致的滑动冲突，可以根据业务需求，规定何时让外部 View 拦截事件，何时由内部 View 拦截事件。
  - 对于上面两种情况的嵌套，相对复杂，可同样根据需求在业务上找到突破点。
- 滑动冲突的实现方法：
  - **内部拦截法**：子 View 可以通过调用 **mParent.requestDisallowInterceptTouchEvent()** 方法干预父 ViewGroup 的事件分发过程（ACTION_DOWN 事件除外），而这就是我们处理滑动冲突常用的关键方法
  - **外部拦截法**：指点击事件都先经过父容器的拦截处理，如果父容器需要此事件就拦截，否则就不拦截。具体方法：需要重写父容器的onInterceptTouchEvent() 方法，在内部做出相应的拦截

## 事件从点击到产生到分发的过程
<img src="../assets/事件从点击到产生到分发的过程.png" style="zoom:80%;" />

## MotionEvent的时间分发

<img src="../assets/MotionEvent的时间分发.png" style="zoom:80%;" />

## 链接
[GitHub：Android事件分发机制](https://github.com/LRH1993/android_interview/blob/master/android/basis/Event-Dispatch.md)

[简书：初探Android事件分发机制源码上之从硬件出发（从硬件开始分析）](https://www.jianshu.com/p/59615d0c9e7d)


[简书：一文读懂Android View事件分发机制（有笔记）](https://www.jianshu.com/p/238d1b753e64)


[CSDN：Android Window 机制探索](https://blog.csdn.net/qian520ao/article/details/78555397#window%E7%9A%84%E6%A6%82%E5%BF%B5)

[Android Touch事件传递机制全面解析（从WMS到View树）](https://blog.csdn.net/ns_code/article/details/49848801?utm_medium=distribute.pc_relevant.none-task-blog-title-2&spm=1001.2101.3001.4242)

[2020跳槽大厂，最常问的9个自定义View面试题！](https://blog.csdn.net/chuhe1989/article/details/104848602)


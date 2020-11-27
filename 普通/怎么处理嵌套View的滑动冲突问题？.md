`View`

## 1. 外部拦截法

即父 View 根据需要对事件进行拦截。逻辑处理放在 **父 View** 的 **onInterceptTouchEvent** 方法中。我们只需要重写父 View 的 onInterceptTouchEvent 方法，并根据逻辑需要做相应的拦截即可。

## 2. 内部拦截法
即父 View 不拦截任何事件，所有事件都传递给子 View，子 View 根据需要决定是自己消费事件还是给父 View 处理。这需要子 **View** 使用 **requestDisallowInterceptTouchEvent** 方法才能正常工作。

## 注意

#### 使用外部拦截法需要注意：

1. 根据业务逻辑需要，在 ACTION_MOVE 方法中进行判断，如果需要父 View 处理则返回 true，否则返回 false，事件分发给子 View 去处理。

2. ACTION_DOWN 一定返回 false，不要拦截它，否则根据 View 事件分发机制，后续 ACTION_MOVE 与 ACTION_UP 事件都将默认交给父 View 去处理

3. 原则上 ACTION_UP 也需要返回 false，如果返回 true，并且滑动事件交给子 View 处理，那么子 View 将接收不到 ACTION_UP 事件，子 View 的 onClick 事件也无法触发。而父 View 不一样，如果父 View 在 ACTION_MOVE 中开始拦截事件，那么后续 ACTION_UP 也将默认交给父 View 处理

#### 使用内部拦截法需要注意：
1. **内部拦截法要求父 View 不能拦截 ACTION_DOWN 事件**，由于 ACTION_DOWN 不受 FLAG_DISALLOW_INTERCEPT 标志位控制，一旦父容器拦截 ACTION_DOWN 那么所有的事件都不会传递给子 View。

2. **滑动策略的逻辑放在子 View 的 dispatchTouchEvent 方法的 ACTION_MOVE 中，如果父容器需要获取点击事件则调用  parent.requestDisallowInterceptTouchEvent(false) 方法，让父容器去拦截事件。**

## 链接
[简书：一文解决Android View滑动冲突](https://www.jianshu.com/p/982a83271327)
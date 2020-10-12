#### 1. 外部拦截法
即父View根据需要对事件进行拦截。逻辑处理放在**父View**的**onInterceptTouchEvent**方法中。我们只需要重写父View的onInterceptTouchEvent方法，并根据逻辑需要做相应的拦截即可。

#### 2. 内部拦截法
即父View不拦截任何事件，所有事件都传递给子View，子View根据需要决定是自己消费事件还是给父View处理。这需要子**View**使用**requestDisallowInterceptTouchEvent**方法才能正常工作。

#### 使用外部拦截法需要注意：

1. 根据业务逻辑需要，在ACTION_MOVE方法中进行判断，如果需要父View处理则返回true，否则返回false，事件分发给子View去处理。

2. ACTION_DOWN 一定返回false，不要拦截它，否则根据View事件分发机制，后续ACTION_MOVE 与 ACTION_UP事件都将默认交给父View去处理！

3. 原则上ACTION_UP也需要返回false，如果返回true，并且滑动事件交给子View处理，那么子View将接收不到ACTION_UP事件，子View的onClick事件也无法触发。而父View不一样，如果父View在ACTION_MOVE中开始拦截事件，那么后续ACTION_UP也将默认交给父View处理！

#### 使用内部拦截法需要注意：
1. <u>内部拦截法要求父View不能拦截ACTION_DOWN事件</u>，由于ACTION_DOWN不受FLAG_DISALLOW_INTERCEPT标志位控制，一旦父容器拦截ACTION_DOWN那么所有的事件都不会传递给子View。

2. <u>滑动策略的逻辑放在子View的dispatchTouchEvent方法的ACTION_MOVE中，如果父容器需要获取点击事件则调用 parent.requestDisallowInterceptTouchEvent(false)方法，让父容器去拦截事件</u>。

### 链接
[简书：一文解决Android View滑动冲突](https://www.jianshu.com/p/982a83271327)
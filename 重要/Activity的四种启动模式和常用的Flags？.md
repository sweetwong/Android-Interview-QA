### 启动模式

1. standard 标准模式：每次启动一个Activity就会创建一个新的实例

2. singleTop 栈顶复用模式：如果新Activity已经位于任务栈的栈顶，就不会重新创建，并回调 onNewIntent(intent) 方法

3. singleTask 栈内复用模式：只要该Activity在一个任务栈中存在，都不会重新创建，并回调 onNewIntent(intent) 方法。如果不存在，系统会先寻找是否存在需要的栈（**如果有设置taskAffinity，就启动对应的任务栈；如果没有，就启用相同的任务栈**）；如果存在，就会创建到已经存在的栈中

4. singleInstance 单实例模式：具有此模式的Activity只能单独位于一个任务栈中（**一定会启动一个新的任务栈**），且此任务栈中只有唯一一个实例

### FLAG

1. FLAG_ACTIVITY_SINGLE_TOP：与launchMode = "singleTop"行为一致

2. FLAG_ACTIVITY_NEW_TASK：如果没有设置taskAffinity，就打开新的Activity；如果有设置taskAffinity，如果Activity不存在，就在指定的taskAffinity打开Activity，如果存在，则不能跳转。

3. FLAG_ACTIVITY_CLEAR_TOP：如果目标存在，直接复用，并清除目标以上的活动，否则重建。可以组合`FLAG_ACTIVITY_NEW_TASK |FLAG_ACTIVITY_CLEAR_TOP`

4. FLAG_ACTIVITY_CLEAR_TASK：只能组合使用，`FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_CLEAR_TASK`，用来清除目前所有的任务，并打开新任务

### 应用场景
1. standard 标准模式：同一个Activity会显示不同的场景的时候，例如套壳Activity

2. singleTop 栈顶复用模式：大部分的Activity都会使用这个，为了防止重复点击

3. singleTask 栈内复用模式：一般用作首页

4. singleInstance 单实例模式：用于独立站操作的页面，例如闹钟提醒页面

### 链接

[简书：2019校招Android面试题解1.0（上篇）](https://www.jianshu.com/p/718aa3c1a70b)

[LaunchMode 的应用场景？](https://github.com/Moosphan/Android-Daily-Interview/issues/4)
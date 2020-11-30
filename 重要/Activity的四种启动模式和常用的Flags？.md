`四大组件`

## 启动模式

1. standard 标准模式：每次启动一个 Activity 就会创建一个新的实例
2. singleTop 栈顶复用模式：如果新 Activity 已经位于任务栈的栈顶，就不会重新创建，并回调 onNewIntent() 方法
   `防止重复点击；或者当前的 Activity 又要启动类似的场景，例如推送页面（我们通过第一次打开 Activity 并处理第一条，后面几条的都在 onNewIntent() 复用处理）`
3. singleTask 栈内复用模式：如果 Activity 已存在，则复用 Activity，并清除它上面的 Activity；如果 Activity 不存在，就在对应的 Task 新建一个 Activity
   `应用首页（当你进行了很多操作，点击了主页按钮，会跳转到主页，并清除掉上面所有的 Activity）、浏览器（全局只有一个浏览器 Activity）`
4. singleInstance 单实例模式：和 SingleTask 的行为比较一致，不过限制该 Activity 只能存在一个 Task 中；且该 Task 只能有一个 Activity
   `提供独立功能，如：闹钟`

## 注意细节

- 被标记为 singleTask 的 Activity 被创建的时候，只会出现在属于自己的 Task 中，并把这整个 Task 压到调用者 Task 的上面。而当用户切换到后台，这个 Activity 存在的 Task又会被放平（不压到之前调用者的 Task 上）
- 在最近任务列表中，同一个 taskAffinity 只能有一个列表，但是同一个 taskAffinity 可能对应多个 Task（当你使用了 singleInstance 时），因此可能出现你创建了一个 singleInstance 的 Activity，然后按了 Home 键，但是在最近任务列表中，并没有发现这个 Activity，并不是因为这个 Activity 被杀死了，只是因为 taskAffinity 冲突了，所以使用 singleInstance 时，最好指定一个 taskAffinity 

## FLAG

1. FLAG_ACTIVITY_SINGLE_TOP：与 singleTop 行为一致
2. FLAG_ACTIVITY_NEW_TASK：如果没有设置 taskAffinity，就打开新的 Activity；如果有设置 taskAffinity，如果 Activity 不存在，就在指定的 taskAffinity 打开 Activity，如果存在，则不能跳转。
3. FLAG_ACTIVITY_CLEAR_TOP：如果目标存在，直接复用，并清除目标以上的活动，否则重建。可以组合`FLAG_ACTIVITY_NEW_TASK |FLAG_ACTIVITY_CLEAR_TOP`
4. FLAG_ACTIVITY_CLEAR_TASK：只能组合使用，`FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_CLEAR_TASK`，用来清除目前所有的任务，并打开新任务

## 链接

[Bilibili：Android 面试黑洞——当我按下 Home 键再切回来，会发生什么？（好视频）](https://www.bilibili.com/video/BV1CA41177Se)

[简书：2019校招Android面试题解1.0（上篇）](https://www.jianshu.com/p/718aa3c1a70b)

[Github：每日一问：LaunchMode 的应用场景？](https://github.com/Moosphan/Android-Daily-Interview/issues/4)


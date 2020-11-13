## 流程
1. 我们在 setContentView 会执行以下操作，创建 DecorView，和主显示区域 content，把我们定义的 layout 添加到 content 中

2. 在 handleResumeAcitivty 时，会调用 WindowManager 的 addView，最终会调到 WindowManagerGlobal 的 addView
3. 随后会创建 ViewRootImpl，并把 DecorView 传入到 ViewRootImpl 中作为其子 View，ViewRootImpl 作为整个层级（Hierarchy）的最顶层
4. 随后执行 Activity 的 makeVisible，要求重新布局
5. ViewRootImpl 开始遍历，先向系统请求 Surface，然后执行三个绘制流程，最后把 Surface 传到 WMS，WMS 传到 SurfaceFlinger 显示到屏幕上

## 要点

1. ViewRootImpl 作为层级的最顶层，只能有一个子 View（不一定要求是 DecorView，不过通常都是 DecorView）

2. ViewRootImpl 有一个 checkThread 方法，检查 UI 线程，注意：**检查的是创建 ViewRootImpl 的线程，而不一定要求是主线程，意思是UI线程不一定要求是主线程**
3. ViewRootImpl 创建的时机的 ActivityThread 执行 handleResumedActivity 最后执行 WindowManagerGlobal 的 addView
4. ViewRootImpl 集成了 ViewParent，和 ViewGroup 一样可以作为 View 的 Parent，是 View 的实现类（类似 ContextImpl）
5. 每个 Activity 对应一个 PhoneWindow，每个 PhoneWindow 对应一个 WindowManagerImpl，WindowManagerGlobal 是一个单例，里面有具体的实现方法

## 架构图
<img src="../assets/Activity Window DecorView关系.png" style="zoom:130%;" />

## 类图

```mermaid
classDiagram
WindowManager <|.. WindowManagerImpl : 实现
WindowManager <|.. WindowManagerGlobal : 实现
WindowManagerGlobal <-- WindowManagerImpl : 代理
IWindowSession <-- WindowManagerGlobal
Window -- WindowManager : 互相持有
Window <|-- PhoneWindow: 唯一的实现类
Window -- DecorView : 互相持有
Activity -- Window : 互相持有
Activity -- WindowManager : 互相持有
ViewRootImpl <-- WindowManagerGlobal : 持有ArrayList<ViewRootImpl> mRoots
DecorView <-- ViewRootImpl : 通常是这种情况，当然可能有不是DecorView的情况
ViewGroup -- DecorView
View -- ViewGroup

class WindowManager {
<<interface>>
}

class WindowManagerImpl {
<<一般通过createLocalWindowManager创建，与Window一一对应>>
}

class WindowManagerGlobal {
<<全局单例>>
}

class IWindowSession {
<<Binder对象，通过这个与WMS通信>>
}
```



## 链接

[慕课网：说说Activity的显示原理](https://coding.imooc.com/lesson/340.html#mid=24588)
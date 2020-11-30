`四大组件`

## ContentProvider 创建的时机

- handleBindApplication() -> installContentProviders() -> installProvider() -> 创建 ContentProvider 的实例 -> 创建 ContextImpl -> 执行 attachInfo() 方法 -> 回调 oncreate()
- 在 Application 创建之后
- 在 Application 的 onCreate() 方法之前

## 注意

- 由于 ContentProvider 的 onCreate() 一定会被执行，且先于 Application 的 onCreate()，因此很多第三方库可以在此做文章，做到无代码侵入，执行注册一个 ContentProvider 并在 onCreate() 进行相应的操作即可
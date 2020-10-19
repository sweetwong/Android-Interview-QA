## MVC
MVC 分为 Model、View、Controller 层

#### 优点

- 对于中小型项目，开发更加简单、快捷、直观

#### 缺点
- Activity 和 Fragment 同时作为 View 和 Controller，过度臃肿，不好维护；职责不分离，不易测试


## MVP
MVP分为 Model、View、Presenter 层

#### 优点
- View 层和 Model 层分离，职责分离，代码更加健壮
- Presenter 层是纯 Java 代码，可以做单元测试
- Presenter 层可以复用（通过抽象出一些 Presenter，继承来复用）
- MVP 模式代码的可阅读性特别高

#### 缺点
- Presenter 层的生命周期不好管理
- 虽然 View 层和 Model 层解耦了，但是 View 层和 Presenter 层互相持有，耦合度非常高
- 会多出很多接口和文件，使项目变得复杂
- Presenter 层的代码会变得很多

## MVVM

MVVM 分为 Model、View、ViewModel

#### 优点
- 解决了 MVP 模式 View 层和 Presenter 层互相持有，高耦合度的问题；MVVM 模式 ViewModel 层并不持有 View 的引用，而是通过数据绑定的形势来改变 UI，因此耦合度是3种模式中最低的；
- 不用担心生命周期的问题，通过 LiveData 动态加载数据，不会造成内存泄漏；
- 对于多 Fragment 的情况，可以通过宿主 Activity 的 ViewModel 实现数据共享。
- ViewModel 层可以复用，抽象出相同数据类型的 ViewModel ，实现 ViewModel 层的复用

#### 缺点

- DataBinding 后期难以调试

## MVP如何解决生命周期的问题？
待处理...

## 链接

[慕课网：教你认清MVC，MVP和MVVM三者的区别](http://www.imooc.com/article/254232)

[高级MVP架构封装演变全过程（这篇文章讲述了MVP生命周期的问题）](http://www.androidchina.net/7961.html)
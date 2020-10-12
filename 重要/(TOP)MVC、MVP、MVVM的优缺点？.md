### MVC
MVC分为Model，View，Controller层

##### 优点：
1. 对于中小型项目，开发更加快捷，直观

##### 缺点：
1. Activity和Fragment同时作为View和Controller，过度臃肿，不好维护；职责不分离，不易测试


### MVP
MVP分为Model，View，Presenter层

##### 优点：
1. View层和Model层分离，职责分离，代码更加健壮
2. Presenter层是纯Java代码，可以做单元测试
3. Presenter层可以复用（通过抽象出一些Presenter，继承来复用）
4. MVP模式代码的可阅读性特别高

##### 缺点：
1. Presenter层的生命周期不好管理
2. 虽然View层和Model层解耦了，但是View层和Presenter层互相持有，耦合度非常高
3. 会多出很多接口和文件
4. Presenter层的代码会变得很多


### MVVM
MVVM分为Model，View，ViewModel

##### 优点：
1. 解决了MVP模式View层和Presenter层互相持有，高耦合度的问题；MVVM模式ViewModel层并不持有View的引用，而是通过数据绑定的形势来改变UI；因此耦合度是3种模式中最低的；
2. 不用担心生命周期的问题，通过LiveData动态加载数据；
3. 对于多Fragment的情况，可以通过宿主Activity的View Model实现数据共享。
4. ViewModel层可以复用，抽象出相同数据类型的ViewModel，实现ViewModel层的复用



### MVP如何解决生命周期的问题？
待处理...

### 链接

[慕课网：教你认清MVC，MVP和MVVM三者的区别](http://www.imooc.com/article/254232)

[高级MVP架构封装演变全过程（这篇文章讲述了MVP生命周期的问题）](http://www.androidchina.net/7961.html)
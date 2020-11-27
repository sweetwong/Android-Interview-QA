`设计模式`

## 重点回答

1. 代理模式
2. 观察者模式
3. 组合模式
4. 

## 创建型模式

1. **单例模式（Singleton）**：让你能够保证一个类只有一个实例， 并提供一个访问该实例的全局节点。
   - `Kotlin 的 object、系统服务各种 Manager、SharedPreferences`
2. **简单工厂模式（Static Factory Method）**：由一个工厂对象决定创建出哪一种产品类的实例，这不属于 23 种设计模式之一。
   - `getSystemService() 获取系统服务、BitmapFactory`
3. **工厂方法模式（Factory Method）**：其在父类中提供一个创建对象的方法， 允许子类决定实例化对象的类型
4. **抽象工厂模式（Abstract Factory）**：它能创建一系列相关的对象， 而无需指定其具体类
5. **建造者模式（Builder）**：使你能够分步骤创建复杂对象
   - `StringBuilder、AlertDialog、Notification、Retrofit、OkHttp`
6. **原型模式（Prototype）**：使你能够复制已有对象， 而又无需使代码依赖它们所属的类
   - `object.clone()、Cloneable`

## 结构型模式

1. **适配器模式（Adapter）**：它能使接口不兼容的对象能够相互合作
   - `RecyclerView、ListView、Retrofit`
2. **桥接模式（Bridge）**：可将一个大类或一系列紧密相关的类拆分为抽象和实现两个独立的层次结构， 从而能在开发时分别使用
3. **组合模式（Composite）**：将对象组合成树形结构以表示“部分-整体”的层次结构
   - `ViewGroup 和 View`
4. **装饰模式（Decorator）**：允许你通过将对象放入包含行为的特殊封装对象中来为原对象绑定新的行为
   - `BufferedRead、InputStream 下所有的实现`
5. **外观模式（Facade）**：能为程序库、 框架或其他复杂类提供一个简单的接口
   - `几乎所有的库都会遵循这个原则，Retrofit、Glide`
6. **享元模式（Flyweight）**：它摒弃了在每个对象中保存所有数据的方式， 通过共享多个对象所共有的相同状态， 让你能在有限的内存容量中载入更多对象
   - `Message.obtainMessage()、线程池`
7. **代理模式（Proxy）**：让你能够提供对象的替代品或其占位符。 代理控制着对于原对象的访问， 并允许在将请求提交给对象前后进行一些处理
   - `动态代理、BinderProxy、ContextWrapper 和 ContextImpl、ViewRootImpl、WindowManagerGlobal`
   - `在项目中会用到动态代理，在一些比较关键的类，会通过动态代理的方式代理所有方法，在方法的前后进行埋点，以及异常处理`

## 行为型模式

1. **责任链模式（Chain Of Responsibility）**：允许你将请求沿着处理者链进行发送。 收到请求后， 每个处理者均可对请求进行处理， 或将其传递给链上的下个处理者
   - `OkHttp 的 Interceptor，Touch 事件的分发`
2. **命令模式（Command）**：它可将请求转换为一个包含与请求相关的所有信息的独立对象。 该转换让你能根据不同的请求将方法参数化、 延迟请求执行或将其放入队列中， 且能实现可撤销操作
   - `Intent、Handler 机制、Runnable`
3. **迭代器模式（Iterator）**：让你能在不暴露集合底层表现形式 （列表、 栈和树等） 的情况下遍历集合中所有的元素
   - `Iterable、Enumeration、Cursor`
4. **中介者模式（Mediator）**：能让你减少对象之间混乱无序的依赖关系。 该模式会限制对象之间的直接交互， 迫使它们通过一个中介者对象进行合作
   - `Binder、MVP`
5. **备忘录模式（Memento）**：允许在不暴露对象实现细节的情况下保存和恢复对象之前的状态
   - `onSaveInstanceState、onRestoreInstanceState，Canvas 的 save() 和 resotre()，Serialization `
6. **观察者模式（Observer）**：允许你定义一种订阅机制， 可在对象事件发生时通知多个 “观察” 该对象的其他对象
   - `ContentObsever、LiveData、RxJava、各种Listener（例如点击事件）、EventBus、BroadcastReceiver`
7. **状态模式（State）**：让你能在一个对象的内部状态变化时改变其行为， 使其看上去就像改变了自身所属的类一样
8. **策略模式（Strategy）**：它能让你定义一系列算法， 并将每种算法分别放入独立的类中， 以使算法的对象能够相互替换
   `RecyclerView 的 LayoutManager、动画的 Interpolator、Comparator`
9. **模板方法模式（Template）**：它在超类中定义了一个算法的框架， 允许子类在不修改结构的情况下重写算法的特定步骤
   `InputStream、OutputStream、Reader、Writer、List、Set、Map`
10. **访问者模式（Visitor）**：它能将算法与其所作用的对象隔离开来
    - `Files.walkFileTree()、ElementVisitor`
11. **解释器模式（Interpreter ）**：给定一个语言，定义它的文法的一种表示，并定义一个解释器，这个解释器使用该表示来解释语言中的句子

## 注意

- **设计模式无处不在**，我们经常会有意无意用到设计模式，而源码中几乎全是设计模式，所以我们通常只是列举一些，来体现它们的思想
- **设计模式学习的思想，而不是模板**，最终的状态应该时**手中无剑、心中有剑**，即我们写出来的代码，会很自然的融合场景用到对应的设计模式
- **设计模式不要乱用**，需要结合场景，我们可以有意去使用，但切忌过度设计

## 链接

[Refactoring.Guru（牛逼的网站）](https://refactoringguru.cn/)

[stack overflow: Examples of GoF Design Patterns in Java's core libraries](https://stackoverflow.com/questions/1673841/examples-of-gof-design-patterns-in-javas-core-libraries)

[CSDN：Android中用到的设计模式](https://blog.csdn.net/goodlixueyong/article/details/51365294)

[CSDN：Android设计模式之23种设计模式一览](https://blog.csdn.net/happy_horse/article/details/50908439)
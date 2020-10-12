### 23种设计模式

##### 创建型模式

1. 单例模式
2. 工厂方法模式
3. 抽象工厂模式
4. 建造者模式
5. 原型模式

##### 结构型模式

1. 适配器模式
2. 装饰者模式
3. 代理模式
4. 外观模式
5. 桥接模式
6. 组合模式
7. 享元模式

##### 行为型模式

1. 策略模式
2. 模板方法模式
3. 观察者模式
4. 迭代子模式
5. 责任链模式
6. 命令模式
7. 备忘录模式
8. 状态模式
9. 访问者模式
10. 中介者模式
11. 解释器模式


### 在Android中的实现

1. 建造者模式：AlertDialog，Retrofit，OkHttp

2. 观察者模式：ContentObsever，LiveData，RxJava，各种Listener（例如点击事件），EventBus

3. 单例模式：无处不在，Kotlin没有静态方法，都是通过单例模式实现的；Android中各种系统级的全局变量都用了单例模式，如Application，各种Manager（例如WindowManager），SharedPreferences，状态栏，输入法，EventBus

4. 适配器模式，RecyclerView，ListView，Retrofit

5. 命令模式：Android中两个典型的使用命令模式的类是Handler和ExecutorService。Handler类提供了post方法发送命令，ExecutorService类通过submit方法提交命令。EventBus

6. 代理模式：WindowManagerImpl和WindowManagerGlobal，ActivityManager通过代理与AMS交互。AIDL通过产生代理类来进行进程间通讯。

7. 备忘录模式：备忘录模式主要功能是实现状态或数据的备份和恢复。那么，很容易想到在Android中有与它对应的一套机制，即onSaveInstanceState和onRestoreInstanceState，这两个方法分别实现View或者Activity关键状态的保存和恢复，具体用法相信大家已经很清楚了；Canvas类中的save和restore两个方法也是使用了备忘录模式。

8. 责任链模式：OkHttp的Interceptor，Touch事件的分发

9. 外观模式：Android源码和各种第三方库大部分都用了外观模式，例如Glide、OkHttp、Retrofit、EventBus，隐藏其复杂的逻辑，暴露出简单的接口

10. 简单工厂模式：getSystemService

11. 享元模式：享元模式用于重复对象的复用，例如各种“池”，防止频繁的创建对象。Message的obtainMessage方法可以实现Message对象的重用，以避免大量的Message对象被频繁的创建和销毁。线程池。

12. 装饰模式：动态地给一个对象添加一些额外的职责。就扩展功能而言， 它比生成子类方式更为灵活；例如一些列Wrapper，ContextWrapper，例如AppCompatActivity

13. 组合模式：将对象组合成树形结构以表示“部分-整体”的层次结构，View和ViewGroup的组合。我们平时写UI的时候，就一直会用到组合模式。

14. 中介者模式：用一个中介对象来封装一系列的对象交互。中介者使各对象不需要显式地相互引用，从而使其耦合松散，而且可以独立地改变它们之间的交互。最典型的就是Binder机制，通过Binder来连通Client、Service、ServiceManager这个三个进程。我们平时构建项目的时候，稍微大型一点的，会使用MVP或者MVVM这种模式来代替传统的MVC，MVP就是典型的中介者模式，Presenter是纯Java代码，作为中介者，来调度View和Model

15. 策略模式：定义了一系列封装了算法、行为的对象，他们可以相互替换，策略模式本身就是Java多态最直接的表现。ava.util.List就是定义了一个增（add）、删（remove）、改（set）、查（indexOf）策略，至于实现这个策略的ArrayList、LinkedList等类，只是在具体实现时采用了不同的算法。但因为它们策略一样，不考虑速度的情况下，使用时完全可以互相替换使用

### 链接

[菜鸟教程：设计模式](https://www.runoob.com/design-pattern/design-pattern-tutorial.html)

[CSDN：Android中用到的设计模式](https://blog.csdn.net/goodlixueyong/article/details/51365294)

[CSDN：Android设计模式之23种设计模式一览（写得太好了，总结得非常对）](https://blog.csdn.net/happy_horse/article/details/50908439)
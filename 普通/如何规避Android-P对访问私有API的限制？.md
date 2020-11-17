`Android 其他`

## 回答

1. 对于 public 的代码，直接编译 frameworks.jar 到项目下，骗过编译器，直接调用

2. 对于 private 或者其他被限制的代码，通过反射 Hook 系统的 API

3. 关于如何绕过 Android 9.0 对反射的限制，使用 **FreeReflection** 框架，描述其实现细节

## Android P 做了哪些限制？
限制了系统反射的调用

* 白名单：可以直接调用
* 浅灰名单：通过反射可以调用
* 深灰名单：API 28 以下通过反射可以调用，API 28 以上不可以调用
* 黑名单：任何时候都不能调用

## FreeReflection 框架的原理？
Hook 了反射相关的 native 代码

## 链接
[Github：FreeReflection](https://github.com/tiann/FreeReflection)

[一种绕过Android P对非SDK接口限制的简单方法](http://weishu.me/2018/06/07/free-reflection-above-android-p/)

[另一种绕过 Android P以上非公开API限制的办法](http://weishu.me/2019/03/16/another-free-reflection-above-android-p/)
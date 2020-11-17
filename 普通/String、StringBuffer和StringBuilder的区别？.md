`Java 基础`

## 答案一

String 为**字符串常量**，一旦创建不可以被修改，是线程安全的；String 类使用 **final** 修饰符，不可以被继承；String 的长度是不变的。适用于少量操作的字符串。

StringBuffer 为**字符串变量**，长度是可变的，**线程安全**。适用于多线程下在字符缓冲区进行大量字符串操作

StringBuilder 为**字符串变量**，长度是可变的，**线程不安全**。适用于单线程下在字符缓冲区进行大量字符串操作。

## 答案二
String 是 Java 语言非常基础和重要的类，提供了构造和管理字符串的各种基本逻辑。它是典型的 Immutable 类，被声明成为 final class，所有属性也都是 final 的。也由于它的不可变性，类似拼接、裁剪字符串等动作，都会产生新的 String 对象。由于字符串操作的普遍性，所以相关操作的效率往往对应用性能有明显影响。

StringBuffer 是为解决上面提到拼接产生太多中间对象的问题而提供的一个类，我们可以用 append 或者 add 方法，把字符串添加到已有序列的末尾或者指定位置。StringBuffer 本质是一个线程安全的可修改字符序列，它保证了线程安全，也随之带来了额外的性能开销，所以除非有线程安全的需要，不然还是推荐使用它的后继者，也就是StringBuilder。

StringBuilder 是 Java 1.5 中新增的，在能力上和 StringBuffer 没有本质区别，但是它去掉了线程安全的部分（synchronized），有效减小了开销，是绝大部分情况下进行字符串拼接的首选。

## 链接
[每日一题：请简述一下String、StringBuffer和StringBuilder的区别？](https://github.com/Moosphan/Android-Daily-Interview/issues/22)
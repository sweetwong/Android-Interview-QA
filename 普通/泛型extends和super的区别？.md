`Java 泛型`

### 问题：<? extends T>、<? super T> 的区别？

- <? extends T>：是指 “上界通配符（Upper Bounds Wildcards）”，**指的是 ? 是 T 的子类或者就是 T**
- <? super T>：是指 “下界通配符（Lower Bounds Wildcards）”，**指的是 ? 是 T 的父类或者就是 T**

### 什么是 PECS 原则？

PECS（Producer Extends Consumer Super）原则：

- 频繁往外读取内容的，适合用上界Extends
- 经常往里插入的，适合用下界Super

## 链接

[知乎：Java 泛型 <? super T> 中 super 怎么 理解？与 extends 有何不同？（终极好问题）](https://www.zhihu.com/question/20400700/answer/117464182)

[Stack Overflow: What is PECS (Producer Extends Consumer Super)?](https://stackoverflow.com/questions/2723397/what-is-pecs-producer-extends-consumer-super)
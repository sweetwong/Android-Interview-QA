## Java String栈
**直接写在代码中的字面量**
```
String s = "aaa...aaa";
```
* 受字节码(\*.class)限制，字符串最终的MUTF-8不超过65535
* Latin字符，受Javac代码限制，最多65534个
* 非Latin字符最终对应字节个数差异较大，最多字节个数是65535
* 如果运行时方法区设置较小，也会受到方法区大小限制

## Java String堆
从文件中读取的，其他**动态获取**的
```
byte[] bytes = loadFromFile(new File("superLongText.txt"));
String superLongString = new String(bytes);
```

* 受虚拟机指令限制，字符数理论上限为Integer.MAX_VALUE
* 受虚拟机实现限制，实际上限可能会小于Integer.MAX_VALUE
* 如果堆内存较小，也会受到堆内存的限制

## 注意点
1. Java的String在内存中的存储形式是，UTF-8，既最少是一个字节

## 链接
[慕课网：Java String 可以有多长？](https://coding.imooc.com/lesson/317.html#mid=22289)
## Java String 栈
**直接写在代码中的字面量**

```
String s = "aaa...aaa";
```
* 受字节码 (\*.class) 限制，字符串最终的 MUTF-8 不超过 65535
* Latin 字符，受 Javac 代码限制，最多 65534 个
* 非 Latin 字符最终对应字节个数差异较大，最多字节个数是 65535
* 如果运行时方法区设置较小，也会受到方法区大小限制

## Java String 堆
从文件中读取的，其他**动态获取**的
```
byte[] bytes = loadFromFile(new File("superLongText.txt"));
String superLongString = new String(bytes);
```

* 受虚拟机指令限制，字符数理论上限为 Integer.MAX_VALUE
* 受虚拟机实现限制，实际上限可能会小于 Integer.MAX_VALUE
* 如果堆内存较小，也会受到堆内存的限制

## 链接
[慕课网：Java String 可以有多长？](https://coding.imooc.com/lesson/317.html#mid=22289)

[编码格式简介（ANSI、GBK、GB2312、UTF-8、GB18030和 UNICODE）](https://blog.csdn.net/ldanduo/article/details/8203532/)

[CSDN：Java中String类型与默认字符编码](https://blog.csdn.net/Sugar_Rainbow/article/details/76945323)
## 要点总结

- Java 泛型采用类型擦除实现
- 类型编译时被擦除为 **Object**，不兼容基本类型
- 类型擦除的实现方案主要考虑兼容性
- 泛型类型签名信息特定场景下反射可获取

## 类型擦除有哪些好处？

- 运行时的**内存**负担小
- **兼容**性好，向以前的 Java 版本兼容

## 类型擦除有哪些问题？

- **基本类型**无法作为泛型实参，会有装箱和拆箱的开销

- 泛型类型无法用作**方法重载**

- 泛型类型无法当作真实类型使用

  ```java
  static <T> void genericMethod(T t) {
      T newInstance = new T(); // 编译不通过，T 并不是真实的类型
      T[] array = new T[0]; // 编译不通过，T 并不是真实的类型
      Class c = T.class; // 编译不通过，T 并不是真实的类型
      List<T> list = new ArrayList<>(); // 编译通过，类型擦除了
      if (list instanceof List<String>) {} // 编译不通过，List<String> 并不是真实的类型
  }
  ```

- 静态方法无法引用类泛型参数

- **类型强转的运行时开销**

## 什么是附加的签名信息？

Java 虚拟机规范：

> Signatures encode declarations written in the Java programming language that use types outside the type system of the Java Virtual Machine. They support reflection and debugging, as well as compilation when only class files are available.
>
> A Java compiler must emit a signature for any class, interface, constructor, method, field, or record component whose declaration uses type variables or parameterized types.

## 有哪些使用签名信息的例子？

Gson：

```java
Type type = new TypeToken<Collection<Integer>>(){}.getType();
Collection<Integer> ints = gson.fromJson(json, type);
```

Retrofit：

```java
interface GithubServiceApi {
    @GET("user/{login}")
    Call<User> getUserCallback(@Path("login")String login);
}
```

## 注意点

- 混淆时要保留签名信息

## 链接

[慕课网：Java 泛型的实现机制是怎样的？](https://coding.imooc.com/lesson/317.html#mid=22292)
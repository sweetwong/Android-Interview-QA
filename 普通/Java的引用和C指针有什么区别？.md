`Java 基础`

> **References might be implemented by storing the address.** *Usually* Java references will be implemented as pointers, but that's not required by the specification. They may be using an additional layer of indirection to enable easier garbage collection. But in the end it will (almost always) boil down to (C-style) pointers being involved in the implementation of (Java-style) references.
>
> **You can't do pointer arithmetic with references.** The most important difference between a pointer in C and a reference in Java is that you can't actually get to (and manipulate) the underlying value of a reference in Java. In other words: you can't do pointer arithmetic.
>
> In C you can add something to a pointer (i.e. the address) or substract something to point to things that are "nearby" or point to places that are at *any* place.
>
> In Java, a reference points to one thing and that thing only. You can make a variable hold a *different* reference, but you can't just ask it to point to "the thing after the original thing".
>
> **References are strongly typed.** Another difference is that the type of a reference is *much* more strictly controlled in Java than the type of a pointer is in C. In C you can have an `int*` and cast it to a `char*` and just re-interpret the memory at that location. That re-interpretation doesn't work in Java: you can only interpret the object at the other end of the reference as something that it *already is* (i.e. you can cast a `Object` reference to `String` reference *only if* the object pointed to is actually a `String`).
>
> **Those differences make C pointers more powerful, but also more dangerous.** Both of those possibilities (pointer arithmetic and re-interpreting the values being pointed to) add flexibility to C and are the source of some of the power of the language. But they are *also* big sources of problems, because if used incorrectly they can easily break assumptions that your code is built around. And it's pretty easy to use them incorrectly.

## 链接

[How is a Java reference different from a C pointer?](https://softwareengineering.stackexchange.com/questions/141834/how-is-a-java-reference-different-from-a-c-pointer)
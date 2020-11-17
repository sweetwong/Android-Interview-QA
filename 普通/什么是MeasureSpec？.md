`View`

## 什么是 MeasureSpec？

MeasureSpec 代表一个 32 位 int 值，**高 2 位**代表 **SpecMode**（测量模式），**低 30 位**代表 **SpecSize**（具体大小）。

## SpecMode有三类

1. **UNSPECIFIED**：表示父容器不对 View 有任何限制，一般用于系统内部，表示一种测量状态

2. **EXACTLY**：父容器已经检测出 View 所需的精确大小，这时候 View 的最终大小 SpecSize 所指定的值，相当于 match_parent 或指定具体数值

3. **AT_MOST**：父容器指定一个可用大小即 SpecSize，View 的大小不能大于这个值，具体多大要看 View 的具体实现，相当于 wrap_content

## 链接

[什么是MeasureSpec？](https://www.kancloud.cn/aslai/interview-guide/1126388)
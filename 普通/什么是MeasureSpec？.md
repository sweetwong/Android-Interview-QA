### 什么是MeasureSpec？
MeasureSpec代表一个32位int值，**高2位**代表**SpecMode**（测量模式），**低30位**代表**SpecSize**（具体大小）。

### SpecMode有三类

1. **UNSPECIFIED**：表示父容器不对View有任何限制，一般用于系统内部，表示一种测量状态

2. **EXACTLY**：父容器已经检测出View所需的精确大小，这时候View的最终大小SpecSize所指定的值，相当于match_parent或指定具体数值

3. **AT_MOST**：父容器指定一个可用大小即SpecSize，View的大小不能大于这个值，具体多大要看View的具体实现，相当于wrap_content

### 链接

[什么是MeasureSpec？](https://www.kancloud.cn/aslai/interview-guide/1126388)
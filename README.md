# Android QA

[Android QA - 按照 TAG 分类](./TAG.md)

[Android QA - 正序版](./QA.md)

[Android QA - 乱序版](./QA-shuffle.md)

# 背景

我为什么要写这个项目？作为一个 **Android 开发工程师**，我觉得网上最不缺的就是学习文章，特别是我们还是用 Java 作为主要开发语言的，隔壁 Java 后端的干货文章更是一堆一堆的。因此我之前也整理了大批大批的学习文章到我的印象笔记，觉得我能全部吃透。但是后面我遇到一个问题，有一次我去腾讯面试的时候，我发现我还是很多不会回答，即使我之前已经看过许多相关的文章了。

我发现是因为：**我没有总结出重点！**

大家都知道，面试的时候，面试官提出一个问题，你回答的往往是很简短的，而网上的长篇大论，并不能帮助你做出最好的回答，甚至有可能最基本的回答都不能实现，因为你没有抓中重点，只是走马观花地把知识点过了一遍。因此我开了这个项目，是 **面向问题** 的。

当提出一个问题我们如何做到：

- 最简短；
- 最深入；
- 回答出最重要的关键字。

因此，我在写这个项目的时候，按照这个流程在走：

1. 筛选出热门的问题；
2. 总结出最简短、深入的回答。

举个例子：

> 最常被问到的 `Handler 的原理？`，我们应该回答出以下几个要点
>
> 1. Looper、Handler、MessageQueue、Message 四个角色的关系，以及它们比较重要的方法
> 2. ThreadLocal 在这之中起到的作用
> 3. 原生的 MessageQueue 和 Looper，被创建的时机，以及起到的作用
> 4. Linux 的 epoll 机制在原生 Looper 起到的作用
> 5. 拓展到 IdleHandler 的原理和应用
> 6. 拓展到同步屏障的原理和应用

可以看到，并不需要长篇大论几百个字，列举回答要点即可。

# 排版

笔记内容按照 [中文文案排版指北](https://github.com/sparanoid/chinese-copywriting-guidelines/blob/master/README.zh-CN.md) 进行排版，以保证内容的可读性。

主要是要遵循两点：

1. 中文和英文之间要加空格；
2. 用全角的中文标点。

举个例子：

> Android 框架可以管理界面控制器（如 Activity 和 Fragment）的生命周期。

# 其他

### 使用什么工具来阅读和编辑这个项目？

- 这里推荐使用 [Typora](https://typora.io/)，是一款漂亮简洁实用的 Markdown 编辑工具，你可以直接 clone 整个项目，然后用 Typora 打开。

### 无法预览 Mermaid（流程图、时序图、类图）怎么办？

- 安装 Chrome 插件：[mermaid-diagrams](https://chrome.google.com/webstore/detail/mermaid-diagrams/phfcghedmopjadpojhmmaffjmfiakfil?utm_source=chrome-ntp-icon)。

### 关于算法与数据结构

- 可以很明显看到，我项目中关于算法的问题并不多，主要是这个知识点不大好提问，不属于那种可以列举出来的知识点。还是建议看书和刷题，书的话无非就是经典的《算法  第四版》和《算法导论》，刷题的话 LeetCode 刷 500 道应该就无所畏惧了。

### 发现 QA 中有错误/不全的地方

- 希望你可以给我提个 Pull Request，我们来一起完善这个项目:smile:。
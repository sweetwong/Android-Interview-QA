`第三方库`

## 原理

ButterKnife 的原理就是利用注解和**注解处理器**针对每个 Activity **生成一个相对应的类**，将原本需要手动编写的 findViewById 的 view 绑定代码和点击事件监听及资源id的绑定逻辑，利用注解处理器自动生成代码。


## ButterKnife 的实现步骤

1. 定义注解

2. 编写注解处理器（Java项目）
    1. 扫描所有的 ButterKnife 注解
    2. 根据文件将注解分开
    3. 生成辅助类文件，调用 findViewById 方法，绑定 View

3. 在 app 中使用注解，并绑定当前的 Activity 即可使用
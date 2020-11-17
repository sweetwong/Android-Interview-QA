`View`

## 步骤

1. 自定义属性（attr）的声明
2. 在构造函数中获取 attr 和初始化
2. 重写 onMeasure
3. 重写 onLayout
4. 重写 onDraw
5. 重写 onTouchEvent
7. 开放一些接口

## 注意点

TODO

## 自定义控件的类型
1. 自绘控件（ImageView、TextView）
2. 组合控件（如设置的 Item，Launcher 的 Item）
3. 继承控件（拓展、修改某些功能）

## 为什么要自定义控件?

1. 特定的显示风格
2. 处理特有的用户交互（例如滑动冲突）
3. 优化布局
4. **封装**, 方便复用（例如设置页的Item选项）

## 具体实现

#### 自定义属性的声明与获取
1. 分析需要的自定义属性
2. 在 res/values/attr.xml 定义声明
3. 在 layout.xml 中使用
4. 在 View 的构造方法中获取
`TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyTextViewStyle);`

#### 测量onMeasure
1. 测量模式分三种：EXACTLY、AT_MOST、UNSPECIFIED
2. 注意 MeasureSpec（测量规格）的内容
3. 需要设置 setMeasuredDimentsion
4. 通过 requestLayout() 触发

#### 布局 onLayout
1. onLayout() 是父控件决定子View的位置，如果是重写 View 而不是 ViewGroup 可以不考虑 onLayout()
2. 尽可能将 onMeasure() 的一些操作移到此方法中，因为 onLayout() 只会触发一次，而 onMeasure() 会触发两次
3. 通过 requestLayout() 来触发

#### 绘制onDraw
1. 绘制内容区域
2. 通过invalidate()、postInvalidate() 触发
3. Canvas.drawXX() 的方法
4. save、restore()

## 链接
[慕课：自定义View](https://www.imooc.com/video/10768)

[Android LayoutInflater原理分析，带你一步步深入了解View\(一\)](https://blog.csdn.net/guolin_blog/article/details/12921889)

[Android视图绘制流程完全解析，带你一步步深入了解View\(二\)](https://blog.csdn.net/guolin_blog/article/details/16330267)

[Android视图状态及重绘流程分析，带你一步步深入了解View\(三\)](https://blog.csdn.net/guolin_blog/article/details/17045157)

[Android自定义View的实现方法，带你一步步深入了解View\(四\)](https://blog.csdn.net/guolin_blog/article/details/17357967)
## 步骤

1. 自定义属性(attr)的声明
2. 在构造函数中获取attr和初始化
2. 重写onMeasure
3. 重写onLayout
4. 重写onDraw
5. 重写onTouchEvent

## 自定义控件的类型
1. 自绘控件
2. 组合控件
3. 继承控件

## 为什么要自定义控件?

1. 特定的显示风格
2. 处理特有的用户交互（例如滑动冲突）
3. 优化布局
4. **封装**, 方便复用（例如设置页的Item选项）

## 具体实现

#### 自定义属性的声明与获取
1. 分析需要的自定义属性
2. 在res/values/attr.xml定义声明
3. 在layout.xml中使用
4. 在View的构造方法中获取
TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyTextViewStyle);

#### 测量onMeasure
1. 测量模式分三种, EXACTLY, AT_MOST, UNSPECIFIED
2. MeasureSpec
3. setMeasuredDimentsion
4. requestLayout()

#### 布局onLayout
1. onLayout是父控件决定子View的位置, 如果是重写View而不是ViewGroup可以不考虑onLayout
2. 尽可能将onMeasure的一些操作移到此方法中, 因为onLayout只会触发一次, onLayout会触发两次
3. requestLayout()来触发

#### 绘制onDraw
1. 绘制内容区域
2. invalidate(), postInvalidate()
3. Canvas.drawXX()的方法
4. save, restore()

## 链接
[慕课：自定义View](https://www.imooc.com/video/10768)

[Android LayoutInflater原理分析，带你一步步深入了解View\(一\)](https://blog.csdn.net/guolin_blog/article/details/12921889)

[Android视图绘制流程完全解析，带你一步步深入了解View\(二\)](https://blog.csdn.net/guolin_blog/article/details/16330267)

[Android视图状态及重绘流程分析，带你一步步深入了解View\(三\)](https://blog.csdn.net/guolin_blog/article/details/17045157)

[Android自定义View的实现方法，带你一步步深入了解View\(四\)](https://blog.csdn.net/guolin_blog/article/details/17357967)
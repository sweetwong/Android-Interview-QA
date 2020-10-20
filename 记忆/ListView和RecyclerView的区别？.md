## 区别

1. 缓存层级不同，ListView 是两级缓存，RecyclerView 是四级缓存；因为这个的关系，RecyclerView 的性能优于 ListView

2. 缓存对象不同，ListView 缓存的是 View，RecyclerView 缓存的是 ViewHolder

3. ListView 有添加头部和尾部的方法，RecyclerView 没有

4. RecyclerView 封装了动画，ListView 没有

5. 关于 ViewHolder 的编写规范化，ListView 需要自己定义，RecyclerView 已经定义好了

6. RecyclerView 多了一个 LayoutManager，实现布局效果的多样化

7. 刷新数据不同，ListView 通常是调用 notifyDataSetChanged() 全局刷新，RecyclerView 可以局部刷新
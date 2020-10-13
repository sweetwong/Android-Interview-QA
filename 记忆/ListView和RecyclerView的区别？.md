## 区别

1. 缓存层级不同，ListView是两级缓存，RecyclerView是四级缓存；因为这个的关系，RecyclerView的性能优于ListView

2. 缓存对象不同，ListView缓存的是View，RecyclerView缓存的是ViewHolder

3. ListView有添加头部和尾部的方法，RecyclerView没有

4. RecyclerView封装了动画，ListView没有

5. 关于ViewHolder的编写规范化，ListView需要自己定义，RecyclerView已经定义好了

6. RecyclerView多了一个LayoutManager，实现布局效果的多样化

7. 刷新数据不同，ListView通常是调用notifyDataSetChanged()全局刷新，RecyclerView可以局部刷新
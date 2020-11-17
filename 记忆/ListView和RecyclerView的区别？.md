`View`

## 区别

1. **缓存层级不同**，ListView 是两级缓存，RecyclerView 是四级缓存；因为这个的关系，RecyclerView 的性能优于 ListView
2. **缓存对象不同**，ListView 缓存的是 View，RecyclerView 缓存的是 ViewHolder
3. ListView 有**添加头部和尾部的方法**，RecyclerView 没有
4. RecyclerView 封装了**动画**，ListView 没有
5. 关于 ViewHolder 的编写规范化，ListView 需要自己定义，RecyclerView 已经定义好了
6. RecyclerView 多了一个 **LayoutManager**，实现布局效果的多样化
7. **刷新数据不同**，ListView 通常是调用 notifyDataSetChanged() 全局刷新，RecyclerView 可以局部刷新

## ListView缓存

#### 1. Active View

是**缓存在屏幕内的 ItemView**，当列表数据发生变化时，屏幕内的数据可以直接拿来复用，无须进行数据绑定。

#### 2. Scrap view

**缓存屏幕外的 ItemView，这里所有的缓存的数据都是'脏的'，也就是数据需要重新绑定**，也就是说屏幕外的所有数据在进入屏幕的时候都要走一遍 getView() 方法。
再来一张图，看看 ListView 的缓存流程


## RecyclerView缓存

RecyclerView的缓存分为四级，由上到下分别为：

1. Scrap（废料）
2. Cache
3. ViewCacheExtension
4. RecycledViewPool

#### 1. Scrap（`ArrayList<ViewHolder> mAttachedScrap`）

> A "scrapped" view is a view that is still attached to its parent RecyclerView but that has been marked for removal or reuse.

对应 ListView 的 Active View ，就是**屏幕内的缓存数据**，就是相当于换了个名字，可以直接拿来复用。


#### 2. Cache（`ArrayList<ViewHolder> mCachedViews`）

**刚刚移出屏幕的缓存数据，默认大小是 2 个**，当其容量被充满同时又有新的数据添加的时候，会根据 FIFO 原则，把优先进入的缓存数据移出并放到下一级缓存中，然后再把新的数据添加进来。Cache 里面的数据是干净的，也就是携带了原来的 ViewHolder 的所有数据信息，数据可以直接来拿来复用。需要注意的是，cache 是根据 position 来寻找数据的，这个 postion 是根据第一个或者最后一个可见的 item 的 position 以及用户操作行为（上拉还是下拉）。
举个栗子：当前屏幕内第一个可见的 item 的 position 是1，用户进行了一个下拉操作，那么当前预测的 position 就相当于（1 - 1 = 0），也就是 position=0 的那个item 要被拉回到屏幕，此时 RecyclerView 就从 Cache 里面找 position=0 的数据，如果找到了就直接拿来复用。

#### 3. ViewCacheExtension

> ViewCacheExtension is a helper class to provide an additional layer of view caching that can be controlled by the developer.

**是 Google 留给开发者自己来自定义缓存的**，这个 ViewCacheExtension 我个人建议还是要慎用，因为我扒拉扒拉网上其他的博客，没有找到对应的使用场景，而且这个类的 api 设计的也有些奇怪，只有一个 public abstract View getViewForPositionAndType(@NonNull Recycler recycler, int position, int type)；让开发者重写通过 position 和 type 拿到 ViewHolder 的方法，却没有提供如何产生 ViewHolder 或者管理 ViewHolder 的方法，给人一种只出不进的赶脚，还是那句话慎用。

#### 4. RecycledViewPool

> RecycledViewPool lets you share Views between multiple RecyclerViews.

刚才说了 Cache 默认的缓存数量是 2 个，**当 Cache 缓存满了以后会根据 FIFO（先进先出）的规则把 Cache 先缓存进去的 ViewHolder 移出并缓存到 RecycledViewPool 中，RecycledViewPool 默认的缓存数量是 5 个**。RecycledViewPool 与 Cache 相比不同的是，从 Cache 里面移出的 ViewHolder 再存入 RecycledViewPool 之前 ViewHolder 的数据会被全部重置，相当于一个新的 ViewHolder，而且 Cache 是根据 position 来获取 ViewHolder，而 RecycledViewPool 是根据 itemType 获取的，如果没有重写 getItemType() 方法，itemType 就是默认的。因为 RecycledViewPool 缓存的 ViewHolder 是全新的，所以取出来的时候需要走 onBindViewHolder() 方法。

**注意：由于可以多个 ReyclerView 共享 ViewHolder，因此 ViewHolder 需要定义成静态的，且需要对 Adapter 持有弱引用**

## 链接

[简书：让你彻底掌握RecyclerView的缓存机制](https://www.jianshu.com/p/3e9aa4bdaefd)

[掘金：RecyclerView缓存原理，有图有真相](https://juejin.im/post/6844903661726859271)


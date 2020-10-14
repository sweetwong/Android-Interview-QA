## ListView缓存

#### 1. Active View

是**缓存在屏幕内的 ItemView **，当列表数据发生变化时，屏幕内的数据可以直接拿来复用，无须进行数据绑定。

#### 2. Scrap view
**缓存屏幕外的 ItemView ，这里所有的缓存的数据都是'脏的'，也就是数据需要重新绑定**，也就是说屏幕外的所有数据在进入屏幕的时候都要走一遍 getView() 方法。
再来一张图，看看 ListView 的缓存流程


## RecyclerView缓存

RecyclerView的缓存分为四级，由上到下分别为：

1. Scrap（废料）
2. Cache
3. ViewCacheExtension
4. RecycledViewPool

#### 1. Scrap
对应 ListView 的 Active View ，就是**屏幕内的缓存数据**，就是相当于换了个名字，可以直接拿来复用。


#### 2. Cache
**刚刚移出屏幕的缓存数据，默认大小是2个**，当其容量被充满同时又有新的数据添加的时候，会根据 FIFO 原则，把优先进入的缓存数据移出并放到下一级缓存中，然后再把新的数据添加进来。Cache 里面的数据是干净的，也就是携带了原来的 ViewHolder 的所有数据信息，数据可以直接来拿来复用。需要注意的是，cache 是根据 position 来寻找数据的，这个 postion 是根据第一个或者最后一个可见的 item 的 position 以及用户操作行为（上拉还是下拉）。
举个栗子：当前屏幕内第一个可见的 item 的 position 是1，用户进行了一个下拉操作，那么当前预测的 position 就相当于（1-1=0），也就是 position=0 的那个item 要被拉回到屏幕，此时 RecyclerView 就从 Cache 里面找 position=0 的数据，如果找到了就直接拿来复用。


#### 3. ViewCacheExtension
**是google留给开发者自己来自定义缓存的**，这个ViewCacheExtension我个人建议还是要慎用，因为我扒拉扒拉网上其他的博客，没有找到对应的使用场景，而且这个类的api设计的也有些奇怪，只有一个public abstract View getViewForPositionAndType(@NonNull Recycler recycler, int position, int type);让开发者重写通过position和type拿到ViewHolder的方法，却没有提供如何产生ViewHolder或者管理ViewHolder的方法，给人一种只出不进的赶脚，还是那句话慎用。


#### 4. RecycledViewPool
刚才说了Cache默认的缓存数量是2个，**当Cache缓存满了以后会根据FIFO（先进先出）的规则把Cache先缓存进去的ViewHolder移出并缓存到RecycledViewPool中，RecycledViewPool默认的缓存数量是5个**。RecycledViewPool与Cache相比不同的是，从Cache里面移出的ViewHolder再存入RecycledViewPool之前ViewHolder的数据会被全部重置，相当于一个新的ViewHolder，而且Cache是根据position来获取ViewHolder，而RecycledViewPool是根据itemType获取的，如果没有重写getItemType（）方法，itemType就是默认的。因为RecycledViewPool缓存的ViewHolder是全新的，所以取出来的时候需要走onBindViewHolder（）方法。

## 链接
[简书：让你彻底掌握RecyclerView的缓存机制](http://www.360doc.com/content/19/0712/11/36367108_848240455.shtml)
## ListView缓存

#### 1. Active View
是<u>缓存在屏幕内的ItemView</u>，当列表数据发生变化时，屏幕内的数据可以直接拿来复用，无须进行数据绑定。

#### 2. Scrap view
<u>缓存屏幕外的ItemView，这里所有的缓存的数据都是'脏的'，也就是数据需要重新绑定</u>，也就是说屏幕外的所有数据在进入屏幕的时候都要走一遍getView（）方法。
再来一张图，看看ListView的缓存流程


## RecyclerView缓存

RecyclerView的缓存分为四级，由上到下分别为：

1. Scrap（废料）
2. Cache
3. ViewCacheExtension
4. RecycledViewPool

#### 1. Scrap
对应ListView的Active View，就是<u>屏幕内的缓存数据</u>，就是相当于换了个名字，可以直接拿来复用。


#### 2. Cache
<u>刚刚移出屏幕的缓存数据，默认大小是2个</u>，当其容量被充满同时又有新的数据添加的时候，会根据FIFO原则，把优先进入的缓存数据移出并放到下一级缓存中，然后再把新的数据添加进来。Cache里面的数据是干净的，也就是携带了原来的ViewHolder的所有数据信息，数据可以直接来拿来复用。需要注意的是，cache是根据position来寻找数据的，这个postion是根据第一个或者最后一个可见的item的position以及用户操作行为（上拉还是下拉）。
举个栗子：当前屏幕内第一个可见的item的position是1，用户进行了一个下拉操作，那么当前预测的position就相当于（1-1=0），也就是position=0的那个item要被拉回到屏幕，此时RecyclerView就从Cache里面找position=0的数据，如果找到了就直接拿来复用。


#### 3. ViewCacheExtension
<u>是google留给开发者自己来自定义缓存的</u>，这个ViewCacheExtension我个人建议还是要慎用，因为我扒拉扒拉网上其他的博客，没有找到对应的使用场景，而且这个类的api设计的也有些奇怪，只有一个public abstract View getViewForPositionAndType(@NonNull Recycler recycler, int position, int type);让开发者重写通过position和type拿到ViewHolder的方法，却没有提供如何产生ViewHolder或者管理ViewHolder的方法，给人一种只出不进的赶脚，还是那句话慎用。


#### 4. RecycledViewPool
刚才说了Cache默认的缓存数量是2个，<u>当Cache缓存满了以后会根据FIFO（先进先出）的规则把Cache先缓存进去的ViewHolder移出并缓存到RecycledViewPool中，RecycledViewPool默认的缓存数量是5个</u>。RecycledViewPool与Cache相比不同的是，从Cache里面移出的ViewHolder再存入RecycledViewPool之前ViewHolder的数据会被全部重置，相当于一个新的ViewHolder，而且Cache是根据position来获取ViewHolder，而RecycledViewPool是根据itemType获取的，如果没有重写getItemType（）方法，itemType就是默认的。因为RecycledViewPool缓存的ViewHolder是全新的，所以取出来的时候需要走onBindViewHolder（）方法。

## 链接
[简书：让你彻底掌握RecyclerView的缓存机制](http://www.360doc.com/content/19/0712/11/36367108_848240455.shtml)
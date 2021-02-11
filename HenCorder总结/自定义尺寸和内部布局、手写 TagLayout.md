# 自定义尺寸和内部布局、手写 TagLayout

### 一、布局过程

- 确定每个 View 的位置和尺寸
- 作用：为绘制和触摸范围做支持
  - 绘制：知道往哪里绘制
  - 触摸反馈：知道用户点的是哪里

### 二、流程：整体

- 从整体看：
  - 测量流程：从根 View 递归调用每一级子 View 的 measure() 方法，对他们进行测量
    - 为什么有时候需要测量多次？
      - 垂直布局的LinearLayout 自身的约束是 warp_content，子 View A 宽度的约束是 match_parent，那么子 View A 第一次测量将会被置为 0，等待其他子 View 测量结束之后再确定子 View A 的宽度
      - 比如在 TagLayout 中，在第一行末尾处，如果子 View 第一次自己测量的宽度小于父 View 的可用宽度；正确的做法则需要父 View 布局的时候把该子 View 放置在第二行，这个时候需传给子 View 不一样的布局要求并让子 View 进行第二次测量。(具体 TagLayout 可见文章下面内容) 
  - 布局流程：从根 View 递归调用每一级子 View 的 layout() 方法，把测量过程得出的子 View的位置和尺寸传给子 View，子 View保存
  - 为什么要分测量和布局两个流程？
    - 测量过程过于复杂（多次测量）

### 三、流程：个体

从个体看，对于每个View：

1. 运行前，开发者在 xml 文件里写入对 View 的布局要求 layout_xxx
2. 父 View 在自己的 onMeasure() 中，根据开发者在 xml 中写的对子 View 的要求，和自己的可用空间，得出对子 View 的具体尺寸要求
3. 子 View 在自己的 onMeasure() 中，根据自己的特性算出自己的期望尺寸
   - 如果是 ViewGroup，还会在这里调用每个子 View 的 measure()进行测量
4. 父 View 在子 View 计算出期望尺寸后，得出子 View 的实际尺寸和位置
5. 子 View 在自己的 layout() 方法中，将父 View 传进来的自己的实际尺寸和位置保存
   - 如果是 ViewGroup，还会在 onLayout() 里调用每个子 View 的 layout() 把它们的尺寸为值传给它们

### 四、实战演练

1. 修改已有View的大小 

   SquareImage

   ```java
   public class SquareImage extends ImageView {
       public SquareImage(Context context, @Nullable AttributeSet attrs) {
           super(context, attrs);
       }
   
       @Override
       protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
           super.onMeasure(widthMeasureSpec, heightMeasureSpec);
           int measureWidth = getMeasuredWidth();
           int measureHeight = getMeasuredHeight();
           int width = Math.min(measureHeight,measureWidth);
           setMeasuredDimension(width,width);
       }
   }
   ```

   ```xml
   <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
       xmlns:app="http://schemas.android.com/apk/res-auto"
       xmlns:tools="http://schemas.android.com/tools"
       android:layout_width="match_parent"
       android:layout_height="match_parent"
       tools:context=".MainActivity"
       >
       <com.example.view.SquareImage
           android:layout_width="300dp"
           android:layout_height="400dp"
           android:src="@mipmap/ic_launcher"
           android:scaleType="centerCrop"
           />
   
   </LinearLayout>
   ```

   ![](..\assets\squareImage.png)

   虽然我在 xml 里面声明 ImageView 的宽高不一样，但是代码里面获取到了 ImageView 自己测量的结果后，手动修改了其宽高，然后调用 setMeasuredDimension(width，height) 设置了 ImageView 的实际宽高为里面的最小值，所以显示的也就是正方形了。

2. 完全自定义 View 的尺寸

   - 重写 onMeasure()
   - 计算出自己的尺寸
   - 用 resolveSize() 或者 resolveSizeAndState()修正结果
   - 使用 setMeasuredDimension(width，height) 保存结果 

3. 完全自定义View 

   CircleView

   一个距离边距有一定宽度的圆，但是需要根据 xml 的限制来确定其大小

   ```java
   public class CircleView extends View {
   
       private float PADDING = 0;
       private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
       private float mRadius = 0;
   
       public CircleView(Context context, @Nullable AttributeSet attrs) {
           super(context, attrs);
           PADDING = Utils.dip2px(context,10);
           mRadius = Utils.dip2px(context,80);
       }
   
       @Override
       protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
           int measureWidth, measureHeight;
           int size = (int) ((PADDING + mRadius) * 2);
           /**
           int widthMode = MeasureSpec.getMode(widthMeasureSpec);//通过位运算获得 mode
           int widthSize = MeasureSpec.getSize(widthMeasureSpec);//通过为运算获得 size
           switch (widthMode){
               //精确类型,父 View 要求子 View 的宽度是精确类型，那么子 View只能照做；除非有“不听话”的时候，情况很少
               case MeasureSpec.EXACTLY:
                   measureWidth = widthSize;
                   break;
               //最大值类型，父 View 要求子 View 的宽度是最大值类型，如果子 View超过了最大值，那么以最大值为准，
               //如果没超过，则以自己算的值为准。
               case MeasureSpec.AT_MOST:
                   measureWidth = Math.min(size,widthSize);
                   break;
               //不限制类型，父 View 要求子 View 的宽度是不限制类型，子 View 以自己算的值为准
               case MeasureSpec.UNSPECIFIED:
                   measureWidth = size;
                   break;
           }
           //高度同 宽度算法
           //因为以上方法是“固定”的，Android API已经把他们封装了，直接调用 resolveSize();
            **/
           measureWidth = resolveSize(size,widthMeasureSpec);
           measureHeight = resolveSize(size,heightMeasureSpec);
           /**其实 resolveSizeAndState(size,widthMeasureSpec,0) 更加好用;
              因为它不仅可以实现 resolveSize() 的功能，还能让父 View 知道这个子 View 的数值
              是自己算的这么多还是由于父 View 对它的限制导致它“压小”了的状态；
              通过 child.getMeasuredState() & MEASURED_STATE_TOO_SMALL 获得
              但是它没有广泛使用的原因是因为大家不知道这个方法，并且很多原生 View 都不支持该方法。**/
           setMeasuredDimension(measureWidth,measureHeight);
       }
   
       @Override
       protected void onDraw(Canvas canvas) {
           super.onDraw(canvas);
           mPaint.setColor(Color.RED);
           canvas.drawCircle(PADDING + mRadius,PADDING + mRadius,mRadius,mPaint);
       }
   }
   ```

   ```xml
   <?xml version="1.0" encoding="utf-8"?>
   <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
       xmlns:app="http://schemas.android.com/apk/res-auto"
       xmlns:tools="http://schemas.android.com/tools"
       android:layout_width="match_parent"
       android:layout_height="match_parent"
       tools:context=".MainActivity"
       >
       <com.example.view.CircleView
           android:layout_width="200dp"
           android:layout_height="300dp"
           android:background="@android:color/darker_gray"
           />
   
   </LinearLayout>
   ```

   ![](..\assets\circleView.png)

4. 自定义 Layout

   - 重写 onMesure()
     - 遍历每个子 View，用 measureChildWidthMargins() 测量子 View 
       - MarginLayoutParams 和 generateLayoutParams()
       - 有些子 View 可能 要重写测量
       - 测量完成后，得出子 View 的实际位置和尺寸，并暂时保存
     - 测量出所有子 View 的位置和尺寸后，计算出自己的尺寸，并用 setMeasuredDimension(width,height) 保存
   - 重写 onLayout()
     - 遍历每个子 View，调用它们的 layout() 方法来将位置和尺寸传给它们

5. TagLayout

   ```java
   public class TagLayout extends ViewGroup {
   
       private static final String TAG = TagLayout.class.getSimpleName();
       private List<Rect> mRects;
   
       public TagLayout(Context context, AttributeSet attrs) {
           super(context, attrs);
           mRects = new ArrayList<>();
       }
   
       @Override
       protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
           super.onMeasure(widthMeasureSpec, heightMeasureSpec);
           int widthSize = MeasureSpec.getSize(widthMeasureSpec);
           int widthUsed = 0, heightUsed = 0;
           int itemLineHeight = 0, lineWidth = 0;
           for (int i = 0; i < getChildCount(); i++){
               if(mRects.size() == i){
                   mRects.add(new Rect());
               }
               Rect childRect = mRects.get(i);
               View child = getChildAt(i);
               /**
               int childWidthMode;
               int childWidthSize;
               LayoutParams layoutParams = child.getLayoutParams();
               switch (layoutParams.width){
                   //子 View 要填满该 ViewGroup
                   case LayoutParams.MATCH_PARENT :
                       switch (widthMode){
                           //ViewGroup 的父 View 对它的要求是精确类型，所以子 View 应该充满 ViewGroup 剩下的全部空间
                           case MeasureSpec.EXACTLY:
                               childWidthMode = MeasureSpec.EXACTLY;
                               childWidthSize = widthSize - widthUsed;
                               break;
                           //ViewGroup 的父 View 对它的要求是最大值类型，所以子 View 应该充满 ViewGroup 剩下的全部空间
                           case MeasureSpec.AT_MOST:
                               childWidthMode = MeasureSpec.EXACTLY;
                               childWidthSize = widthSize - widthUsed;
                               break;
                           //ViewGroup 的父 View 对它的要求是不限制类型，所以子 View 应该是不限制
                           case MeasureSpec.UNSPECIFIED:
                               childWidthMode = MeasureSpec.UNSPECIFIED;
                               childWidthSize = 0;
                               break;
                       }
   
                       break;
                   case LayoutParams.WRAP_CONTENT:
   
                       break;
   
               }
               int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(childWidthSize,childWidthMode);
               child.measure(childWidthMeasureSpec,childHeightMeasureSpec);
               **/
               //以上代码由于是固定的，所以 Android API 也封装了方法 measureChildMargins();
               measureChildWithMargins(child,widthMeasureSpec,0,heightMeasureSpec,heightUsed);
               Log.d(TAG," onMeasure widthSize - widthUsed : "+(widthSize - widthUsed) + " child.getMeasuredWidth() : "+child.getMeasuredWidth());
               if (widthSize - widthUsed < child.getMeasuredWidth()){
                   widthUsed = 0;
                   lineWidth = widthSize;
                   heightUsed += itemLineHeight;
                   measureChildWithMargins(child,widthMeasureSpec,0,heightMeasureSpec,heightUsed);
               }
               childRect.set(widthUsed,heightUsed,widthUsed + child.getMeasuredWidth(),heightUsed + child.getMeasuredHeight());
   
               widthUsed += child.getMeasuredWidth();
               Log.d(TAG," widthUsed : "+widthUsed+" i : "+i);
               itemLineHeight = Math.max(itemLineHeight,child.getMeasuredHeight());
               lineWidth = Math.max(lineWidth,widthUsed);
           }
           heightUsed += itemLineHeight;
           Log.d(TAG," lineWidth : "+lineWidth+" heightUsed : "+heightUsed);
           setMeasuredDimension(lineWidth,heightUsed);
       }
   
       @Override
       protected void onLayout(boolean changed, int l, int t, int r, int b) {
           for (int i = 0; i < getChildCount(); i++){
               View child = getChildAt(i);
               Rect rect = mRects.get(i);
               child.layout(rect.left,rect.top,rect.right,rect.bottom);
           }
   
       }
   
       //当调用 measureChildWithMargins 的时候，系统会把 LayoutParams 强转为 MarginLayoutParams，所以需要我们支持
       @Override
       public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
           return new MarginLayoutParams(getContext(),attributeSet);
       }
   }
   ```

   ```xml
   <?xml version="1.0" encoding="utf-8"?>
   <com.example.view.TagLayout xmlns:android="http://schemas.android.com/apk/res/android"
       xmlns:app="http://schemas.android.com/apk/res-auto"
       xmlns:tools="http://schemas.android.com/tools"
       android:layout_width="match_parent"
       android:layout_height="match_parent"
       tools:context=".MainActivity"
       android:background="@android:color/holo_green_dark"
       >
       <com.example.view.ColoredTextView
           android:layout_width="wrap_content"
           android:layout_height="wrap_content"
           android:text="杭州"
           />
       <com.example.view.ColoredTextView
           android:layout_width="wrap_content"
           android:layout_height="wrap_content"
           android:text="杭州"
           />
       <!--若干....-->
   
   </com.example.view.TagLayout>
   ```

   ![](..\assets\tagLayout.png)

   


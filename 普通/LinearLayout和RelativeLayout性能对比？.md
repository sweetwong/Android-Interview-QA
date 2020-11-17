`View`

## LinearLayout 和 RelativeLayout 性能对比？

LinearLayout 比 RelativeLayout 快，因为 RelativeLayout 会在 onMeasure 方法中调用两次子 View 的 measure（横向纵向分别进行一次排序测量）；而 LinearLayout 只用一次，但是当 LinearLayout 有 weight 属性时，也会 measure 两次
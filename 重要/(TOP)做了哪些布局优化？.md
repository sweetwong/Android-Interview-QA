### 方法

1. 合理运用布局，**减少布局层级**，避免布局嵌套

2. 善用标签，**include**、**merge**、**viewstub**

3. 避免**重叠绘制**，例如background

4. 使用**ConstraintLayout**来减小布局层级

5. 不使用xml文件，直接new出来

6. 异步inflate


### 工具

##### 1. 开发者模式打开Profile GPU Rendering
橙色代表处理的时间，是CPU告诉GPU渲染一帧的地方，这是一个阻塞调用，因为CPU会一直等待GPU发出接到命令的回复，如果橙色柱状图很高，则表明GPU很繁忙。

 红色代表执行的时间，这部分是Android进行2D渲染 Display List的时间。如果红色柱状图很高，可能是由重新提交了视图而导致的。还有复杂的自定义View也会导致红的柱状图变高。

 蓝色代表测量绘制的时间，也就是需要多长时间去创建和更新DisplayList。如果蓝色柱状图很高，可能是需要重新绘制，或者View的onDraw方法处理事情太多。

##### 2. Systrace
Systrace是Android4.1中新增的性能数据采样和分析工具。它可帮助开发者收集Android关键子系统（SurfaceFlinger、WindowManagerService等Framework部分关键模块、服务，View体系系统等）的运行信息。Systrace的功能包括跟踪系统的I/O操作、内核工作队列、CPU负载以及Android各个子系统的运行状况等。对于UI显示性能，比如动画播放不流畅、渲染卡顿等问题提供了分析数据。

##### 3. TraceView
TraceView是Android SDK中自带的数据采集和分析工具。一般来说，通过TraceView我们可以得到以下两种数据：
  1. 单次执行耗时的方法。
  2. 执行次数多的方法。

##### 4. Lint
监测不合理的布局
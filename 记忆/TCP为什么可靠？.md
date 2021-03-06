`计算机网络`

## 什么是可靠的传输？

> TCP 的可靠数据传输服务确保一个进程从其接收缓存中读出的数据流是**无损坏、无间隙、非冗杂和按序**的数据流；即该字节流与连接的另一方端系统发送出的字节流是完全相同的
>
> `《计算机网络-自顶向下方法》第 7 版 第 159 页`

## TCP为什么可靠？

1. **超时重传：** 当 TCP 发出一个段后，它启动一个定时器，等待目的端确认收到这个报文段。如果不能及时收到一个确认，将重发这个报文段。
2. **校验和：** TCP 将保持它首部和数据的检验和。这是一个端到端的检验和，目的是检测数据在传输过程中的任何变化。如果收到段的检验和有差错，TCP 将丢弃这个报文段和不确认收到此报文段。
3. **流量控制：** TCP 连接的每一方都有固定大小的缓冲空间，TCP的接收端只允许发送端发送接收端缓冲区能接纳的数据。当接收方来不及处理发送方的数据，能提示发送方降低发送的速率，防止包丢失。TCP 使用的流量控制协议是可变大小的滑动窗口协议。 （TCP 利用滑动窗口实现流量控制）
4. **拥塞控制：** 当网络拥塞时，减少数据的发送。
5. 应用数据被分割成 TCP 认为最适合发送的数据块。
6. TCP 有**序号**，TCP 给发送的每一个包进行编号，接收方对数据包进行排序，把有序数据传送给应用层。
7. TCP 的接收端会**丢弃重复的数据**。

## 链接

[Github：JavaGuide：计算机网络](https://github.com/Snailclimb/JavaGuide/blob/master/docs/network/%E8%AE%A1%E7%AE%97%E6%9C%BA%E7%BD%91%E7%BB%9C.md)
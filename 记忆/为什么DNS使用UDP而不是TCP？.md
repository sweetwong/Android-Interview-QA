`计算机网络`

## 什么是 DNS？

DNS 是一个分布式数据库，提供了主机名和 IP 地址之间相互转换的服务。这里的分布式数据库是指，每个站点只保留它自己的那部分数据。

域名具有层次结构，从上到下依次为：根域名、顶级域名、二级域名。

![img](https://cs-notes-1256109796.cos.ap-guangzhou.myqcloud.com/b54eeb16-0b0e-484c-be62-306f57c40d77.jpg)



DNS 可以使用 UDP 或者 TCP 进行传输，使用的端口号都为 53。大多数情况下 DNS 使用 UDP 进行传输，这就要求域名解析器和域名服务器都必须自己处理超时和重传从而保证可靠性。在两种情况下会使用 TCP 进行传输：

- 如果返回的响应超过的 512 字节（UDP 最大只支持 512 字节的数据）。
- 区域传送（区域传送是主域名服务器向辅助域名服务器传送变化的那部分数据）。

## 为什么 DNS 使用 UDP 而不是 TCP？

- 使用 UDP 传输是由于效率高，传输小于等于 512 字节报文。
- 使用 TCP 传输是由于可以传输大于 512 字节报文。
- 使用签名是保证数据来源的可靠性。
- 使用 TCP 传输，同样是可以传输证书链、签名。
- 使用 UDP 同样可以传输远远大于 576 字节的数据，只要应用程序可以标识数据ID。 

## 链接

[知乎：为什么DNS使用UDP而不是TCP？](https://www.zhihu.com/question/310145373)

[公众号：车小胖：为什么DNS使用UDP而不是TCP?](https://mp.weixin.qq.com/s/BF0EOyN2PtqN9ec0oyYS8g)

[Github：CS-Notes：计算机网络 - 应用层](http://www.cyc2018.xyz/%E8%AE%A1%E7%AE%97%E6%9C%BA%E5%9F%BA%E7%A1%80/%E7%BD%91%E7%BB%9C%E5%9F%BA%E7%A1%80/%E8%AE%A1%E7%AE%97%E6%9C%BA%E7%BD%91%E7%BB%9C%20-%20%E5%BA%94%E7%94%A8%E5%B1%82.html)
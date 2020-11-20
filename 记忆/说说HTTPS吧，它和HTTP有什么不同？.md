`计算机网络`

## HTTP 有以下安全性问题

- 使用 **明文** 进行通信，内容可能会被窃听；
- 不验证通信方的 **身份**，通信方的身份有可能遭遇伪装；
- 无法证明报文的 **完整性**，报文有可能遭篡改。

## HTTPS 流程

1. TCP 三次握手（1.5 RTT）
2. TLS 加密（TLS/1.2 是 1.5 RTT；TLS/1.3 是第一次 1 RTT，后面 0 RTT）：
   1. 客户端发起 TLS 会话（Client Hello）
   2. 服务端把公钥传给客户端（Server Hello）
   3. 客户端通过 CA 验证公钥的合理性（Key Exchange）（防止中间人攻击）
   4. 客户端随机选择一个对称加密算法，用服务端的公钥加密对称加密生成的密钥，并传给服务端（用非对称加密加密数据开销太大，因此用非对称加密传输对称加密的密钥，用对称加密加密数据）
   5. 客户端和服务端都持有对称加密的密钥，且只有它们知道
3. 传输数据（1 RTT）


## 链接
[知乎：彻底搞懂HTTPS的加密机制（终极好文）](https://zhuanlan.zhihu.com/p/43789231)

[Github：CS-Notes：HTTP](http://www.cyc2018.xyz/%E8%AE%A1%E7%AE%97%E6%9C%BA%E5%9F%BA%E7%A1%80/HTTP/HTTP.html#%E5%85%AD%E3%80%81https)

[Github：JavaGuide：计算机网络](https://github.com/Snailclimb/JavaGuide/blob/master/docs/network/%E8%AE%A1%E7%AE%97%E6%9C%BA%E7%BD%91%E7%BB%9C.md)


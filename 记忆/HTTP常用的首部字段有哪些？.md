`计算机网络`

通用首部字段（General Header Fields）：请求报文和响应报文两方都会使用的首部

- Cache-Control 控制缓存 ✨
- Connection 连接管理、逐条首部 ✨
- Upgrade 升级为其他协议
- via 代理服务器的相关信息
- Wraning 错误和警告通知
- Transfor-Encoding 报文主体的传输编码格式 ✨
- Trailer 报文末端的首部一览
- Pragma 报文指令
- Date 创建报文的日期

请求首部字段（Reauest Header Fields）:客户端向服务器发送请求的报文时使用的首部

- Accept 客户端或者代理能够处理的媒体类型 ✨
- Accept-Encoding 优先可处理的编码格式
- Accept-Language 优先可处理的自然语言
- Accept-Charset 优先可以处理的字符集
- If-Match 比较实体标记（ETage） ✨
- If-None-Match 比较实体标记（ETage）与 If-Match相反 ✨
- If-Modified-Since 比较资源更新时间（Last-Modified）✨
- If-Unmodified-Since比较资源更新时间（Last-Modified），与 If-Modified-Since相反 ✨
- If-Rnages 资源未更新时发送实体byte的范围请求
- Range 实体的字节范围请求 ✨
- Authorization web的认证信息 ✨
- Proxy-Authorization 代理服务器要求web认证信息
- Host 请求资源所在服务器 ✨
- From 用户的邮箱地址
- User-Agent 客户端程序信息 ✨
- Max-Forwrads 最大的逐跳次数
- TE 传输编码的优先级
- Referer 请求原始放的url
- Expect 期待服务器的特定行为

响应首部字段（Response Header Fields）:从服务器向客户端响应时使用的字段

- Accept-Ranges 能接受的字节范围
- Age 推算资源创建经过时间
- Location 令客户端重定向的URI ✨
- vary 代理服务器的缓存信息
- ETag 能够表示资源唯一资源的字符串 ✨
- WWW-Authenticate 服务器要求客户端的验证信息
- Proxy-Authenticate 代理服务器要求客户端的验证信息
- Server 服务器的信息 ✨
- Retry-After 和状态码503 一起使用的首部字段，表示下次请求服务器的时间

实体首部字段（Entiy Header Fields）:针对请求报文和响应报文的实体部分使用首部

- Allow 资源可支持http请求的方法 ✨
- Content-Language 实体的资源语言
- Content-Encoding 实体的编码格式
- Content-Length 实体的大小（字节）
- Content-Type 实体媒体类型
- Content-MD5 实体报文的摘要
- Content-Location 代替资源的yri
- Content-Rnages 实体主体的位置返回
- Last-Modified 资源最后的修改资源 ✨
- Expires 实体主体的过期资源 ✨

## 链接

[掘金：面试官（9）：可能是全网最全的http面试答案](https://juejin.cn/post/6844903865410650126#heading-8)
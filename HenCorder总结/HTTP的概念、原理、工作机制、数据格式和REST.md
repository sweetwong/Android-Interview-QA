# HTTP 的概念、原理、工作机制、数据格式和 REST

### 一. Http 到底是什么?

HyperText Transfer Protocol超文本传输协议（Http协议可以理解为传输Html的协议）。

### 二. Request Methods

#####      1. GET 

获取资源,没有 Body。

#####      2. POST

增加或者修改资源；有 Body。

#####      3. PUT

修改资源；有 Body；相对于 POST 来说，它具有幂等性，也就是说一次调用和多次调用结果一样。

#####      4. DELETE

删除资源；没有 Body。

#####      5. HEAD

没有Body。

### 三. Status Code

对结果做出类型化描述（如"获取成功"、"内容未找到"）。

#####     1. 1XX

100,请求拆分时，客户端 Header 加上 **Expect:100-continue** ，此时服务器会返回100。

101， HTTP 协议切换，典型的1.1和2切换，询问是否支持 HTTP2 ，如果支持会返回101（**Upgrade:h2c**）。

#####     2. 2XX

成功。

#####     3. 3XX

301，重定向；304网页无修改。

#####     4. 4XX

客户端错误。

#####     5. 5XX

服务端错误。



### 四. Header

HTTP消息的元数据（metadata）。

##### 1. Host

服务器主机地址 ，eg：  

```java
 GET/user/1 HTTP/1.1
 Host:  hencoder.com                                  
```

**注意**： Host 不是用来找寻服务器地址的，服务器的查找工作在请求之前就已经通过 DNS 查询（Domain Name System）完成，他的作用是给目标主机用来定位具体的子主机（一个主机可能包含多个服务器）。

##### 2. Content-Length

内容的长度（字节）；如果内容是二进制的，需要 Content-Length 来判断是否结果。

##### 3. Content-Type 

内容类型。

- **text/html**：HTML 文本，用于浏览器页面响应。

- **application/x-www-form-urlencoded**：普e通表单，encoded URL 格式。

- **multipart/form-data**：多部分形式,一般用于传输包含二进制内容的多项内容,  boundary 用来区分每部分，一般由浏览器自动生成。

- **application/json**：JSON 形式，用于 Web Api 的响应或者POST/PUT 请求。

- **image/jpeg或者application/zip**：单文件，用于 Web Api 相应或者 POST/PUT 请求。

  

##### 4. Chunked Transfer Encoding 

分块内容传输

- **transfer-Encoding: chunked**

- **Body 格式**

  ```java
  <length1>
  <data1>
  <length2>
  <data2>
  0
  (最后传输0表示内容结束)
  ```

##### 5. Location 

重定向的目标URL。

##### 6. User-Agent

用户代理。

##### 7. Range/Accept-Range

指定Body的内容范围。

##### 8. Cookie/Set-Cookie

发送 Cookie / 设置 Cookie。

##### 9. Authorization

授权信息。

##### 10. Accept

客户端能接受的数据类型，如 text/html。

##### 11. Accept-Charset

客户端能接受的字符集，如 utf-8。

##### 12. Accept-Encoding

客户端能接受的压缩编码类型，如 gzip。

##### 13. Content-Encoding

压缩类型，如 gzip。

### 五. Cache

- ##### Cache 和 Buffer 的区别:

- **Cache**：缓存，这次用完了下次还可能会接着用。Http Cache 主要是各个中间节点把数据缓存起来，降低直接服务器取数据的频率。
- **Buffer**：缓冲，针对工作流上游多生产了一些给下游使用，一般是上游生产太快(路由器)和下游现在还没消费，待会可能会猛消费，现在上游先生产一些备着。
- **Cache-Control:no-cache、no-store、max-age**： **no-cache**，服务器告诉客户端，你可以缓存，但是你下次请求时先问我失效了没有，如果没失效你再用本地的数据，否则你用我给你的最新的数据；**no-store**，不要缓存；**max-age**，有一个时间，只要时间没到你就尽管用缓存，不需要询问我。
- **Last-Modified**：在服务器返回资源的时候，会告诉客户端它最近修改的时间，然后客户端下次使用资源的时候会询问服务端，这个资源它在你给我的那个时间之后有没有修改**if-Modified-Since**。
- **Etag**：服务器给客户端一个指纹，下次客户端会使用 **if-Mone-Match** 询问服务端这指纹对应的资源有没有修改，如果有修改，需要服务端提供新的资源，没有就是使用本地 Cache。
- **Cache-Control:private/public**：服务器告诉客户端是公共缓存还是私有缓存，服务器询问中间节点是否缓存。



### 六. REST

##### 1. RESTful HTTP

- **正确使用HTTP**
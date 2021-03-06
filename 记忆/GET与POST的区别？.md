`计算机网络`

## 区别

#### 1. 作用/语义不同

GET 用于获取资源，而 POST 用于传输实体主体。

#### 2. 参数

GET 和 POST 的请求都能使用额外的参数，但是 GET 的参数是以查询 **字符串** 出现在 URL 中，而 POST 的参数存储在 **实体主体** 中。

不能因为 POST 参数存储在实体主体中就认为它的安全性更高，因为照样可以通过一些抓包工具（Fiddler）查看。因为 URL 只支持 ASCII 码，因此 GET 的参数中如果存在中文等字符就需要先进行编码。例如 中文 会转换为 %E4%B8%AD%E6%96%87，而空格会转换为 %20。POST 参数支持标准字符集。

#### 3. 安全性

**安全的 HTTP 方法不会改变服务器状态，也就是说它只是可读的**。GET 方法是安全的，而 POST 却不是，因为 POST 的目的是传送实体主体内容，这个内容可能是用户上传的表单数据，上传成功之后，服务器可能把这个数据存储到数据库中，因此状态也就发生了改变。安全的方法除了 GET 之外还有：HEAD、OPTIONS。不安全的方法除了 POST 之外还有 PUT、DELETE。

#### 4. 幂等性

幂等的 HTTP 方法，**同样的请求被执行一次与连续执行多次的效果是一样的**，服务器的状态也是一样的。换句话说就是，幂等方法不应该具有副作用（统计用途除外）。所有的安全方法也都是幂等的。在正确实现的条件下，GET，HEAD，PUT 和 DELETE 等方法都是幂等的，而 POST 方法不是。

#### 5. 可缓存

如果要对响应进行缓存，需要满足以下条件：

* 请求报文的 HTTP 方法本身是可缓存的，包括 GET 和 HEAD，但是 PUT 和 DELETE 不可缓存，POST 在多数情况下不可缓存的。
* 响应报文的状态码是可缓存的，包括：200, 203, 204, 206, 300, 301, 404, 405, 410, 414, and 501。
* 响应报文的 Cache-Control 首部字段没有指定不进行缓存。

## 链接

[Github：CS-Notes：GET 和 POST 比较](https://cyc2018.github.io/CS-Notes/#/notes/HTTP?id=%e4%b9%9d%e3%80%81get-%e5%92%8c-post-%e6%af%94%e8%be%83)

[知乎问题：GET 和 POST 到底有什么区别？](https://www.zhihu.com/question/28586791)
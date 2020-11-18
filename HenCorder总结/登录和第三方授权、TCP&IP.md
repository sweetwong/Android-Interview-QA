# 登录和第三方授权、TCP/IP

### 一、登录和授权

- Cookie

  - 起源：购物车。
  - 储存方式：目标服务器加 Cookie 原封不动的储存。
  - 作用：
    - 会话管理：登录状态、购物车等。
    - 个性化：用户偏好、主题。
    - Tracking：分析用户行为。
  - XSS(Cross-site scripting)：HttpOnly（使js拿不到该 Cookie）。
  - XSRF(Cross-site request forgery)：Referer（标记从哪个网站跳转）。

- Authorization

  - Basic

    - Authorization：Basic < username:password(Base64ed) >

  - Bearer

    - Authorization：Bearer < bearer token >

    - OAuth2

      - OAuth2 流程
      - 微信登录
        - 在用户点击微信登录之后，用微信Api跳转至微信授权界面。
        - 授权界面会显示相关信息，用户点击确认登录。
        - 微信会通知微信服务器，跳转回原始应用，然后把 Authorization Code 返回给原始应用。
        - 原始应用把 Authorization Code 传递给自家服务器，然后自家服务器去微信服务器通信，获取 token 。
        - 自家服务器换完 token 之后，再用 token 请求具体的微信用户信息。
        - 再用上步骤获取到的信息在自家的账号体系中注册用户或者登录。
        - 自己服务器把登录信息返回给自家客户端。
        - 用户体验到了微信登录成功。

    - 自家 App 中使用 Bearer token

      - 登录之后拿到 token 直接在后续请求中携带该 token。
      - 因为不是第三方授权所以不属于 OAuth2 流程，最多只能说模拟 OAuth2。

    - refresh token

      用户刷新 access_token。

      ```json
      {
      	"token_type":"Bearer",
      	"access_token":"XXXXX",
      	"refresh_token":"XXXXX",
      	"expires_time":"XXXXXX"
      }
      ```

    

    

    

    

    

    

    

    


`WebView`

## Android 调用JS代码

1. 通过 WebView 的 loadUrl()

2. 通过 WebView 的 evaluateJavascript()


## JS 调用 Android代码

1. 通过 WebView 的 addJavascriptInterface() 进行对象映射

2. 通过 WebViewClient 的 shouldOverrideUrlLoading() 方法回调拦截 url

3. 通过 WebChromeClient 的 onJsAlert()、onJsConfirm()、onJsPrompt() 方法回调拦截 JS 对话框 alert()、confirm()、prompt() 消息

## 链接
[简书：最全面总结 Android WebView与 JS 的交互方式](https://www.jianshu.com/p/345f4d8a5cfa)


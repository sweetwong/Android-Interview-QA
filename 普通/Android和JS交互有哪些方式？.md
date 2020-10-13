## Andrdoi调用JS代码

1. 通过WebView的loadUrl()

2. 通过WebView的evaluateJavascript()


## JS调用Android代码

1. 通过WebView的addJavascriptInterface()进行对象映射

2. 通过WebViewClient 的shouldOverrideUrlLoading()方法回调拦截 url

3. 通过WebChromeClient 的onJsAlert()、onJsConfirm()、onJsPrompt()方法回调拦截JS对话框alert()、confirm()、prompt()消息

## 链接
[简书：最全面总结 Android WebView与 JS 的交互方式](https://www.jianshu.com/p/345f4d8a5cfa)
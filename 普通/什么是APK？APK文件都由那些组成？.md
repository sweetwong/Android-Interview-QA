### APK简介与描述

APK是Android Package的缩写，即Android安装包（apk）。APK文件其实是zip格式，但后缀名被修改为apk，通过UnZip解压后，可以看到Dex文件，Dex是Dalvik VM executable的全称，即Android Dalvik执行程序，并非Java字节码而是Dalvik字节码。在Android平台中dalvik vm的执行文件被打包为apk格式，最终运行时加载器会解压然后获取编译后的AndroidManifest.xml文件中的permission分支相关的安全访问，但仍然存在很多安全限制，如果你将apk文件到/system/app文件夹下会发现执行是不受限制的。安装的文件可能不是这个文件夹，而在android rom中系统的apk文件默认会放入这个文件夹，它们拥有着root权限。


### APK的文件结构

1. assets\
2. lib\
3. META-INF\ （注：Jar文件中常可以看到）；
4. res\ （注：存放资源文件的目录） ；
5. AndroidManifest.xml （注：程序全局配置文件） ；
6. classes.dex （注：Dalvik字节码）；
7. resources.arsc （注：编译后的二进制资源文件）。

### 链接
[百度经验：什么是APK？APK文件都由那些组成？](https://jingyan.baidu.com/article/414eccf61a4ac96b431f0a3f.html)
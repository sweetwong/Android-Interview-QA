## APK简介与描述

APK是 Android Package 的缩写，即 Android 安装包（apk）。APK 文件其实是 zip 格式，但后缀名被修改为 apk，通过 UnZip 解压后，可以看到 Dex 文件，Dex 是 Dalvik VM executable 的全称，即 Android Dalvik 执行程序，并非 Java 字节码而是 Dalvik 字节码。在 Android 平台中 dalvik vm 的执行文件被打包为 apk 格式，最终运行时加载器会解压然后获取编译后的 AndroidManifest.xml 文件中的 permission 分支相关的安全访问，但仍然存在很多安全限制，如果你将apk文件到 /system/app 文件夹下会发现执行是不受限制的。安装的文件可能不是这个文件夹，而在 android rom 中系统的 apk 文件默认会放入这个文件夹，它们拥有着 root 权限。


## APK的文件结构

1. assets\
2. lib\
3. META-INF\ （注：Jar文件中常可以看到）；
4. res\ （注：存放资源文件的目录） ；
5. AndroidManifest.xml （注：程序全局配置文件） ；
6. classes.dex （注：Dalvik字节码）；
7. resources.arsc （注：编译后的二进制资源文件）。

## 链接
[百度经验：什么是APK？APK文件都由那些组成？](https://jingyan.baidu.com/article/414eccf61a4ac96b431f0a3f.html)
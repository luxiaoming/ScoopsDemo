# ScoopsDemo
多主题风格代码demo
本文属代码GG原创，非经本人同意，禁止转载。

需要交流，联系微信：code_gg_boy
更多精彩，时时关注微信公众号code_gg_home

![一个显示图](https://github.com/luxiaoming/dagger2Demo/raw/master/images/0.jpg)
![](http://upload-images.jianshu.io/upload_images/1603789-c4636a111ee03306?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
简要说明


 Scoops是一个android应用库，主要解决的是多主题实现方案。通过配置多个R.style.Theme ，代码进行动态设置主题，重启当前界面实现。


原理分析


 主要通过设置主题（存储设置值）， 重启activity，通过setTheme方式设置style实现。


使用方法


1 项目的build.gradle里面

![](http://upload-images.jianshu.io/upload_images/1603789-1d36a1748d91e1e6?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

2 模块的build.gradle里面

![](http://upload-images.jianshu.io/upload_images/1603789-db8eddc7254f137e?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![](http://upload-images.jianshu.io/upload_images/1603789-684a4f6cadb39ed6?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

3 values里面加入themes.xml

配置一些主题，为了多主题更换使用


4 AndroidManifest.xml里面
配置上默认主题，这里要注意的是配置的和5里面写的默认的那个要一致


![](http://upload-images.jianshu.io/upload_images/1603789-4b45e036e724934f?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

5 MainApp里面
完成初始化，主要设置一系列的主题。其中addToppings为的是注释的时候使用，后面会讲它的用法。setSharedPreferences 配置默认的存储key值addDayNightFlavor 设置DayNight的主题风格，这个随后会讲到。

![](http://upload-images.jianshu.io/upload_images/1603789-e89eee1f07775a02?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


6 在MainAcitvity里面

1:需要继承AppCompatActivity2：需要在setContentView前面调用Scoop.getInstance().apply(this);将主题设置进来。


![](http://upload-images.jianshu.io/upload_images/1603789-bb1c4f16abbcd1de?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

3：这里说下为什么需要在AndroidManifest.xml里面配置默认主题，因为Scoops设计，在判断主题没有更改的情况下，不进行设置主题，因此第一次进来不会设置。所以我们要配置默认主题。并且和MainApp里面设置的默认值一样。

![](http://upload-images.jianshu.io/upload_images/1603789-89a53c9bc7db24e9?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

这里的true第三个参数说明设置为默认主题，和我们在AndroidManifest.xml设置的主题一致即可。


7 效果如下
![](http://upload-images.jianshu.io/upload_images/1603789-bbff73b2025a4a9a?imageMogr2/auto-orient/strip)

8 代码地址

官网 地址 https://github.com/52inc/Scoops
demo地址   https://github.com/luxiaoming/ScoopsDemo

![](http://upload-images.jianshu.io/upload_images/1603789-e901d5c4c79b1642?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

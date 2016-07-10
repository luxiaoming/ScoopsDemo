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

简要说明

通过注释方式，完成多主题的效果更换。可以减少一些代码，使得代码更清晰一些。![](http://upload-images.jianshu.io/upload_images/1603789-3f4cb364baa7e98b?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
使用说明

1 首先写一个注释类
![](http://upload-images.jianshu.io/upload_images/1603789-2ed64f45c3b3d195?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
2 在MainApp
初始化的时候，将注释通过addToppings方式设置进去

![](http://upload-images.jianshu.io/upload_images/1603789-4cd88281c311743e?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)3 在MainActivity
使用注释@BindToppingStatus 标记下状态栏的颜色跟随
使用注释@BindTopping 标记下某个控件的颜色跟随
@BindTopping标记里面还可以有参数
1 value对应Toppings里面的某个值
2 adapter 跟上一个适配器，主要是为了实现某些控件自定义的设置颜色方案
3 interpolator 可以配置颜色切换过程效果，比如AccelerateInterpolator.class
具体效果如下
代码可以看到还有一个注释，叫 @BindView(R.id.fab) 这个就是之前讲的Butter Knife ，不了解的可以在文章末尾看到地址

![](http://upload-images.jianshu.io/upload_images/1603789-7bb6b289b42693ed?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)4使用
在ButterKnife.bind(this);绑定了界面view后，使用 Scoop.sugarCone().bind(this);将view ，适配器和动画切换效果设置进来
在需要更新颜色的地方，使用Scoop.sugarCone().update即可，参数为第一个值为更新的注释类型，比如Toppings.PRIMARY_DARK参数二为颜色值。

![](http://upload-images.jianshu.io/upload_images/1603789-a3c6748c590fdd36?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
5 ColorAdapter适配器
如果需要自己自定义主题更换后的view颜色变换动作（主要是有时候不符合我们的需求，比如我们想动态改文本颜色，高亮色等），我们就需要自定义一个实现ColorAdapter的类，比如ButtonColorAdapter接口 ColorAdapter定义了两个方法
设置新的颜色值
void applyColor(T view, @ColorInt int color);
获取当前颜色值
int getColor(T view);

![](http://upload-images.jianshu.io/upload_images/1603789-03b7dfca5280e55b?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
6 主题bug
1 使用 @BindTopping 注释的时候，必须加上adapter=XXX.class 如果用默认的 ，请加上adapter = DefaultColorAdapter.class
2 默认的设置ScoopSettingsActivity界面，当设置为默认主题的时候，会出现bug。原因是这个界面指定了主题android:theme=”@style/Theme.AppCompat.Light” 如果我们默认的界面指定的跟这个不一样，则这个设置界面则会在默认主题的时候出错，和设置的效果不一致。解决方案：自己写一个设置界面呗。

7 demo地址
https://github.com/luxiaoming/ScoopsDemo
Butter Knife 注释编程阅读地址：
http://mp.weixin.qq.com/s?__biz=MzI1MjMyOTU2Ng==&mid=2247483660&idx=1&sn=9fc37fdb8f84763896b2696c03070db0&scene=4#wechat_redirect
![](http://upload-images.jianshu.io/upload_images/1603789-cf139f71e77e5843?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

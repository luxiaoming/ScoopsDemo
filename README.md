# ScoopsDemo
多主题风格代码demo
本文属代码GG原创，非经本人同意，禁止转载。

需要交流，联系微信：code_gg_boy
更多精彩，时时关注微信公众号code_gg_home

![一个显示图](https://github.com/luxiaoming/dagger2Demo/raw/master/images/0.jpg)
![](http://upload-images.jianshu.io/upload_images/1603789-c4636a111ee03306?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
#Scoops android app多主题架构

![](http://upload-images.jianshu.io/upload_images/1603789-c4636a111ee03306?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

##基本用法



- **简要说明**

 Scoops是一个android应用库，主要解决的是多主题实现方案。通过配置多个R.style.Theme ，代码进行动态设置主题，重启当前界面实现。

- **原理分析**

 主要通过设置主题（存储设置值）， 重启activity，通过setTheme方式设置style实现。

- **使用方法**

**1项目的build.gradle里面**

![](http://upload-images.jianshu.io/upload_images/1603789-1d36a1748d91e1e6?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


**2 模块的build.gradle里面**

![](http://upload-images.jianshu.io/upload_images/1603789-db8eddc7254f137e?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![](http://upload-images.jianshu.io/upload_images/1603789-684a4f6cadb39ed6?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

**3 values里面加入themes.xml**

配置一些主题，为了多主题更换使用

**4 AndroidManifest.xml里面**

配置上默认主题，这里要注意的是配置的和5里面写的默认的那个要一致

![](http://upload-images.jianshu.io/upload_images/1603789-4b45e036e724934f?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

**5 MainApp里面**

完成初始化，主要设置一系列的主题。其中addToppings为的是注释的时候使用，后面会讲它的用法。setSharedPreferences 配置默认的存储key值addDayNightFlavor 设置DayNight的主题风格，这个随后会讲到。

![](http://upload-images.jianshu.io/upload_images/1603789-e89eee1f07775a02?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

**6 在MainAcitvity里面**

1:需要继承AppCompatActivity

2：需要在setContentView前面调用Scoop.getInstance().apply(this);将主题设置进来。

![](http://upload-images.jianshu.io/upload_images/1603789-bb1c4f16abbcd1de?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

3：这里说下为什么需要在AndroidManifest.xml里面配置默认主题，因为Scoops设计，在判断主题没有更改的情况下，不进行设置主题，因此第一次进来不会设置。所以我们要配置默认主题。并且和MainApp里面设置的默认值一样。
![](http://upload-images.jianshu.io/upload_images/1603789-89a53c9bc7db24e9?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

这里的true第三个参数说明设置为默认主题，和我们在AndroidManifest.xml设置的主题一致即可。

**7 效果如下**

![](http://upload-images.jianshu.io/upload_images/1603789-bbff73b2025a4a9a?imageMogr2/auto-orient/strip)

**8 代码地址**

官网 地址 https://github.com/52inc/Scoops

demo地址   https://github.com/luxiaoming/ScoopsDemo

##注解用法

- **简要说明**

通过注释方式，完成多主题的效果更换。可以减少一些代码，使得代码更清晰一些。

- **使用说明**

**1 首先写一个注释类**

![](http://upload-images.jianshu.io/upload_images/1603789-2ed64f45c3b3d195?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

**2 在MainApp**

初始化的时候，将注释通过addToppings方式设置进去

![](http://upload-images.jianshu.io/upload_images/1603789-4cd88281c311743e?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

**3 在MainActivity**

使用注释@BindToppingStatus 标记下状态栏的颜色跟随

使用注释@BindTopping 标记下某个控件的颜色跟随

@BindTopping标记里面还可以有参数

1 value对应Toppings里面的某个值

2 adapter 跟上一个适配器，主要是为了实现某些控件自定义的设置颜色方案

3 interpolator 可以配置颜色切换过程效果，比如AccelerateInterpolator.class

代码可以看到还有一个注释，叫 @BindView(R.id.fab) 这个就是之前讲的Butter Knife ，不了解的可以在文章末尾看到地址
具体效果如下

![](http://upload-images.jianshu.io/upload_images/1603789-7bb6b289b42693ed?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


**4使用**

在ButterKnife.bind(this);绑定了界面view后，使用 Scoop.sugarCone().bind(this);将view ，适配器和动画切换效果设置进来
在需要更新颜色的地方，使用Scoop.sugarCone().update即可，参数为第一个值为更新的注释类型，比如Toppings.PRIMARY_DARK参数二为颜色值。

![](http://upload-images.jianshu.io/upload_images/1603789-a3c6748c590fdd36?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

**5 ColorAdapter适配器**

如果需要自己自定义主题更换后的view颜色变换动作（主要是有时候不符合我们的需求，比如我们想动态改文本颜色，高亮色等），我们就需要自定义一个实现ColorAdapter的类，比如ButtonColorAdapter接口 ColorAdapter定义了两个方法

设置新的颜色值

void applyColor(T view, @ColorInt int color);

获取当前颜色值

int getColor(T view);


![](http://upload-images.jianshu.io/upload_images/1603789-03b7dfca5280e55b?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

**6 主题bug**

1 使用 @BindTopping 注释的时候，必须加上adapter=XXX.class 如果用默认的 ，请加上adapter = DefaultColorAdapter.class

2 默认的设置ScoopSettingsActivity界面，当设置为默认主题的时候，会出现bug。原因是这个界面指定了主题android:theme=”@style/Theme.AppCompat.Light” 如果我们默认的界面指定的跟这个不一样，则这个设置界面则会在默认主题的时候出错，和设置的效果不一致。解决方案：自己写一个设置界面呗。

**7 demo地址**

https://github.com/luxiaoming/ScoopsDemo

Butter Knife 注释编程阅读地址：

http://mp.weixin.qq.com/s?__biz=MzI1MjMyOTU2Ng==&mid=2247483660&idx=1&sn=9fc37fdb8f84763896b2696c03070db0&scene=4#wechat_redirect



##白天夜间主题

很多应用都有这个功能，可以选择白天和夜间模式，给用户更好的体验。为此，系统也提供了一种机制，来实现此功能。

模式主要分为四种 夜间 白天 跟随系统 和自动。具体实现可以去看com.android.support:appcompat-v7:23.0.0源码

夜间和白天模式很好理解。自动就是根据时间判断是否为白天和夜间，自动选择对应主题。跟随系统则使用系统的值，依赖系统。
实现原理

通过两组主题，分别放在values和values-night下面，系统根据你设置的模式，对应的去找主题配置值，然后设置上去即可。
使用说明

###**不使用Scoops方式**

**1自定义一个属性Attrs.xml**

![](http://upload-images.jianshu.io/upload_images/1603789-e455d23570850be0?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

这里可以看到定义了两个属性textColor和textSize 格式分别为color和dimension

**2创建一个目录values-night**

**3在values和values-night下面**

新建一个文件themes.xml，同时配置相同的主题，加入我们自定义的属性textColor，给它配置值。

values下的属性

![](http://upload-images.jianshu.io/upload_images/1603789-b497fc6b18f4221f?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

values-night下的属性

![](http://upload-images.jianshu.io/upload_images/1603789-aace2c1e2b3c3951?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

**4使用的地方**

![](http://upload-images.jianshu.io/upload_images/1603789-3b677c4ceb24798c?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

这里mMode的值可以取以下几个

MODE_NIGHT_FOLLOW_SYSTEM

MODE_NIGHT_YES

MODE_NIGHT_NO

MODE_NIGHT_AUTO


**5我们配置下使用的地方**

![](http://upload-images.jianshu.io/upload_images/1603789-235ad23ebb6c24b3?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

这里的 android:textColor=”?attr/textColor” 记住用的是?attr来使用自定义的属性

**6具体的结果，看Primary Color颜色变化**

![](http://upload-images.jianshu.io/upload_images/1603789-5a0240625b5fec5f?imageMogr2/auto-orient/strip)

**7代码地址**

https://github.com/luxiaoming/ScoopsDemo

###**使用Scoops方式**

**1：MainApp**

![](http://upload-images.jianshu.io/upload_images/1603789-994eeee4c1b78e12?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

使用addDayNightFlavor将一个主题设置为DayNight模式

**2：在对应的主题**里面配置上文本颜色

values里面

![](http://upload-images.jianshu.io/upload_images/1603789-491305e122b4a00e?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

values-night里面

![](http://upload-images.jianshu.io/upload_images/1603789-3200737bf8062e5c?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

**3：进入app**，在设置里面设置成dayNight模式后，点击*按钮Scoops方式*查看效果即可。

**4：效果如下**

![](http://upload-images.jianshu.io/upload_images/1603789-a3fa2564e5613b32?imageMogr2/auto-orient/strip)

**5：代码地址**

https://github.com/luxiaoming/ScoopsDemo

###**原理分析**

**核心代码位置**

https://github.com/52inc/Scoops/tree/master/scoops/src/main/java/com/ftinc/scoop可以看到主要文件

![](http://upload-images.jianshu.io/upload_images/1603789-c2c7adefbf64d462?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

Flavor.java 存储每个主题信息

Scoop.java 主文件，主要提供接口，作为对外调用。

SugarCone.java 解析注释的主文件，通过使用bind方法，去反射找到编译出来的Toppings类，通过这个来关联上来。

Topping.java 自定义注释方法。标记在属性上面，然后通过编译时候，使用插件scoops-compiler，生成后缀为Toppings的绑定类，随后我们使用bind函数，将这些类和界面关联起来。

**关键方法**

Scoop.apply函数
![](http://upload-images.jianshu.io/upload_images/1603789-f3c1ebca4375e88f?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

我们来看下public void apply(Activity activity)的具体代码流程找到当前的主题getCurrentFlavor

![](http://upload-images.jianshu.io/upload_images/1603789-c8af6f3f15793d42?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

判断主题是否是白天夜间模式，如果是，设置模式然后使用

![](http://upload-images.jianshu.io/upload_images/1603789-5315a39e736ce52c?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

另一个apply方法，参数是activity和主题id设置主题，找到设置的背景色，设置窗体背景，然后返回去，我们自己recreate 重新启动当前界面，实现切换。

**编译插件**

https://github.com/52inc/Scoops/tree/master/scoops-compiler

主要目的是解析我们自定义的注解。解析的主要是我们在初始化时候使用addToppings加入的我们的注解。找到后，解析出来注解的标记值，切换过场特效，和适配器。标记值的目的是更新值的时候，用这个来做区分。

![](http://upload-images.jianshu.io/upload_images/1603789-52e7f59cec4afb0e?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

代码逻辑：找到所有的绑定在这个标记的view，然后逐个调用对应的适配器，进行对应的颜色更新。我们来看下核心的bind函数

![](http://upload-images.jianshu.io/upload_images/1603789-5eafab84d683bc96?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

我们看下bind 代码，主要看下getViewBinder这个方法即可。继续去看findViewBinderForClass 函数

![](http://upload-images.jianshu.io/upload_images/1603789-2fd3567b1a1a8401?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

代码主要流程为：从缓存能找到，直接返回。找不到，然后查找Class.forName(clsName + "_ToppingBinder")类，构造一个，然后将这个返回，并缓存下来。然后调用里面的bind进行关联view我们反编译可以看到MainActivity_ToppingBinder.smali这个smali文件，这个就是插件生成出来的代码。我们反编译下，看下这个文件做了什么。

![](http://upload-images.jianshu.io/upload_images/1603789-fb67190bb7c7d50b?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

看到了吧，将注释的全部归类进来，为后面更新的时候查找使用。注释的原理就是如此了。


![](http://upload-images.jianshu.io/upload_images/1603789-e0981277069ec305?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

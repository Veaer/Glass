# Glass
a library about status and theme
不知道什么叫做"沉浸式状态栏"、"translucent status bar"、"translucent system bar"

在5.0以下解放状态栏的App其实并不多，例如ES文件管理器、豌豆荚、最美应用

<img src="/images/es.png" width="200" height="350" />
<img src="/images/wandoujia.png" width="200" height="350" />
<img src="/images/zuimei.png" width="200" height="350" />

这三个就是完全的典型了，可以看到ES状态栏还是会有一道半透明的灰条，而豌豆荚在上滑的时候会发生奇怪的事情，我觉得最美反倒是避开了这个事情。。。

然后动手写了个实现4.4到5.0之间的版本解放状态栏的东西(黑科技)，当然并没有什么了不得的东西。。。如果大家有更好的解决方法，希望多多交流，欢迎大家多多issues和pr

也可以用来实现"快速换肤"的效果

机型：IUNI U3,安卓版本4.4.4

<img src="/images/iuni.gif" width="200" height="350" />

机型：Nexus5,安卓版本5.1.1

<img src="/images/n5.gif" width="200" height="350" />

使用方法：
    
    适应5.0以下
    ```
    mGlass = Glass.Builder.newInstance()
                    .statusBarWithLower(getWindow(), App.mContext, ContextCompat.getColor(App.mContext, R.color.colorPrimary))
                    .text(textView)
                    .background(appBar)
                    .background(fab)
                    .background(colorView)
                    .build();
    ```
    
    不适应5.0以下
    ```
    mGlass = Glass.Builder.newInstance()
                    .statusBar(getWindow(), ContextCompat.getColor(App.mContext, R.color.colorPrimary))
                    .text(textView)
                    .background(appBar)
                    .background(fab)
                    .background(colorView)
                    .build();
    ```
    
使用方法(虽然还有坑，但是先发上来吧)：

    ```
        repositories {
            maven {
                url 'https://dl.bintray.com/veaer/maven/'
            }
        }
        compile 'king.veaer:glass:1.0.0'
    ```
    
[点击下载APK](http://fir.im/vGlass)
#源代码在 GPLv3 协议下发布

[LICENSE](/LICENSE)


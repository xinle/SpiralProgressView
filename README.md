# SpiralProgressView

[![License](https://img.shields.io/badge/license-Apache%202-green.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![Download](https://api.bintray.com/packages/xinle/maven/SpiralProgressView/images/download.svg) ](https://bintray.com/xinle/maven/SpiralProgressView/_latestVersion)

Android 仿IOS ActionSheet UI样式 ,通过纯代码实现 ,不用导入额外的图片 ,可定制化能力强

## Screenshot  (实际效果没有这么差,因为图片压缩过的原因有锯齿毛边)

![](https://github.com/xinle/SpiralProgressView/blob/master/Screenshot/screenshot.gif)

## 使用

```java
compile 'com.lelive:SpiralProgressView:#lastVersion#'
```

## 范例

```java
SpiralProgressView progressView = (SpiralProgressView) findViewById(R.id.spiral_progress);
progressView.setProgress(20);
SpiralProgressView.ProgressAttrs attrs = new SpiralProgressView.ProgressAttrs(this);
attrs.haveAnimation = true;
progressView.setProgressAttrs(attrs);

LinearLayout linearLayout = (LinearLayout) findViewById(R.id.activity_main);
SpiralProgressView progressView1 = new SpiralProgressView(this);
progressView1.setProgress(30);
LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 80);
params.topMargin = 30;
progressView1.setLayoutParams(params);
SpiralProgressView.ProgressAttrs attrs1 = new SpiralProgressView.ProgressAttrs(this);
attrs1.haveAnimation = false;
attrs1.mBackgroudColor = Color.GREEN;
attrs1.mLineColor = Color.GRAY;
attrs1.mDegrees = 30;
attrs1.mWLine = SpiralProgressView.ProgressAttrs.dp2px(this ,20);
attrs1.mDisLine = SpiralProgressView.ProgressAttrs.dp2px(this ,20);
attrs1.mRound = 40;
progressView1.setProgressAttrs(attrs1);

linearLayout.addView(progressView1);
```

- ProgressAttrs属性说明

| Attribute                  | 属性含义                                     | 默认值     |
|:---------------------------|:--------------------------------------------|:----------|
| mMax             | 最大值                                      | 100 |
| mProgress       |  当前进度                         | 0 |
| mDisLine         | 斜线的距离                             | 10dp |
| mWLine  | 斜线的宽度                               | 10dp |
| mDegrees   |  斜线的角度                              | 45 |
| mRound |    圆角                              | 5dp |
| mBackgroudColor   | 背景颜色                      | Color.parseColor("#F86442")|
| mLineColor          | 斜线的颜色                        |Color.parseColor("#D54A2A")   |
| haveAnimation       | 是否有动画                      | false    |
| animationTime   | 动画的时间(作用前提haveAnimation=true)                           | 100      |
| animationSpeed    | 动画的速率(前提haveAnimation=true)                         | mDisLine / 10      |

# FreeImageView
Android ImageView that can be resize/move freely

## Install
implementation 'com.liliang9257:FreeImageview:1.0.1'

### Usage
```xml
<com.ll4869.freeimageview.FreeImageView
    android:layout_width="198.3dp"
    android:layout_height="104.3dp"
    android:scaleType="fitXY"
    android:src="@drawable/dragonborn"
    />
```

设置最小宽高、边缘宽度。边缘宽度用来识别是否要改变图片大小，不影响显示。宽高必须>=3倍边缘宽度
<br>setMinHWS(int width, int height, int sideWidth)</br>

### Preview
 ![img](https://github.com/liliang4869/FreeImageView/blob/main/gif/sample.gif)
 

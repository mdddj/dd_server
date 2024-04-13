这里1.6 换成1.8的才能用
![image.png](https://upload-images.jianshu.io/upload_images/3671684-d29dc4ea99e78c07.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
然后`build.gradle`添加依赖
```
implementation 'androidx.core:core-ktx:1.3.2'
```

咳咳如果还报错,这个也加上
 ```
   kotlinOptions { jvmTarget = 1.8}
```

ok
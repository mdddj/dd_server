flutter自学交流群1102781545，欢迎提问

在`build.gradle(Module:app)`文件下引入插件

```
implementation 'com.github.bumptech.glide:glide:4.8.0'
annotationProcessor 'com.github.bumptech.glide:compiler:4.8.0'
```
使用
```
    fun loadNetImage(src : String){
        Glide.with(this).load(src).into(imageView2);
    }
```
xml布局
```
           <ImageView
              android:id="@+id/imageView2"
              android:layout_width="150dp"
              android:layout_height="150dp"
              android:layout_gravity="center"
              android:layout_marginBottom="30dp"
              android:scaleType="fitStart"
              tools:srcCompat="@tools:sample/avatars"
           />
```
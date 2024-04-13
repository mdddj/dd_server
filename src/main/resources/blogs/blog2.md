```kotlin
import androidx.compose.desktop.Window
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

// 使用图像资源
fun main() = Window(size = IntSize(1000, 800), title = "梁典典", icon = getWindowIcon()) {


    val image = remember { imageFormFile(File("D://logo.png")) }



    ///  horizontalAlignment = Alignment.CenterHorizontally 横向居中子元素
    Column(modifier = Modifier.fillMaxSize(), Arrangement.spacedBy(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {

        /// 使用本地图片
        Text("使用资源图片")
        Image(
            bitmap = imageResource("images/img.png"),
            modifier = Modifier.size(200.dp, 200.dp)
        )


        Text("使用存储设备图片")
        Image(
            bitmap = image
        )
    }

    /// 从存储设备中加载图片,电脑文件系统


}

/// 从资源管理器加载图片
fun imageFormFile(file: File): ImageBitmap {
    return org.jetbrains.skija.Image.makeFromEncoded(file.readBytes()).asImageBitmap()
}

/// 设置应用图标
fun getWindowIcon(): BufferedImage {
    var image: BufferedImage? = null
    try {
        image = ImageIO.read(File("D://logo.png"))
    } catch (e: Exception) {
        print("图片不存在")
    }
    if (image == null) {
        image = BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB)
    }

    return image
}
```

# 效果
![image.png](https://upload-images.jianshu.io/upload_images/3671684-dc07373677b104f6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

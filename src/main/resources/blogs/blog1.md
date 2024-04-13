```kotlin

import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.ExperimentalKeyInput
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.plus
import androidx.compose.ui.input.key.shortcuts

@ExperimentalKeyInput
fun main() = Window {


    MaterialTheme {
        val text = remember { mutableStateOf("") }

        Column {
            Text("请输入你的姓名")


            TextField(
                value = text.value,
                onValueChange = {
                    text.value = it
                },
                modifier = Modifier.shortcuts {

                    on(Key.CtrlLeft + Key.Enter) {
                        print("输入了")
                    }

                }
            )

        }
    }


}
```

# 输入汉字是可能会遇到这个报错
```
Exception in thread "AWT-EventQueue-0" java.lang.NullPointerException: event.text must not be null
```
# 解决办法
修改`build.gradle.kts` 文件, version 换成 `0.3.0-build133`
```
plugins {
    kotlin("jvm") version "1.4.20"
    id("org.jetbrains.compose") version "0.3.0-build133"
}
```
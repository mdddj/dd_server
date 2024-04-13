package shop.itbug.ticket.utils

import cn.hutool.core.io.FileUtil
import java.util.*


object ImageCheck {
    /**
     * 判断是否为图片
     */
    fun isImage(path: String): Boolean {
        val suffix = FileUtil.getSuffix(path).lowercase(Locale.getDefault())
        return "jpg" == suffix || "jpeg" == suffix || "png" == suffix || "bmp" == suffix || "gif" == suffix
    }
}


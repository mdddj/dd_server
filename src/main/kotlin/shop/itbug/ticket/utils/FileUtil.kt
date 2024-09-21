package shop.itbug.ticket.utils

import cn.hutool.core.date.DateUtil
import cn.hutool.core.util.CharsetUtil
import org.springframework.web.multipart.MultipartFile
import java.net.URLEncoder

class FileManagerWrapper(val file: MultipartFile) {

    ///获取文件名
    private fun getFileName() : String {
        return file.originalFilename?.let { file.originalFilename?.substring(0, it.lastIndexOf(".")) } ?: "${DateUtil.date().millisecond()}"
    }

    ///获取文件后缀
    private fun getExt() : String {
        return file.originalFilename?.let { file.originalFilename?.substring(it.lastIndexOf(".")) } ?: ".jpg"
    }

    data class FinalNameModel(var name: String,var thumbnailName: String)
    /**
     * 获取最终的名字
     */
    fun getFinalName() : FinalNameModel {
        val time = System.currentTimeMillis() //时间戳
        var name = getFileName() + "___"+ time + "___"
        name = name.replace(" ","")
        return FinalNameModel(URLEncoder.encode("$name${getExt()}",CharsetUtil.CHARSET_UTF_8),"${name}_thumbnail")
    }
}
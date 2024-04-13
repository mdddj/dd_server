package shop.itbug.ticket.entry.storage

import org.springframework.web.multipart.MultipartFile
import shop.itbug.ticket.entry.FileInfo
import shop.itbug.ticket.entry.User

abstract class StorageAbstract {


    /**
     * 获取访问的链接
     */
    abstract fun getLinkUrl(file: MultipartFile,subFolderName: String = "",host:String = "",user: User?) : FileInfo?
}
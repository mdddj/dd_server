package shop.itbug.ticket.service

import jakarta.servlet.http.HttpServletRequest
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.entry.FileInfo
import shop.itbug.ticket.entry.FileInfoSaveConfig
import shop.itbug.ticket.entry.ResourcesCategory
import shop.itbug.ticket.entry.User

/**
 * @author eee
 */
@Service
interface FileInfoService {
    /**
     * 保存或者修改一个文件类型
     * @param info  文件
     * @return  文件
     */
    fun saveOrUpdate(info: FileInfo): FileInfo

    /**
     * 分页查找文件列表
     * @param pageModel 文件模型
     * @param info  文件查找条件
     * @return  查找结果
     */
    fun findFilesByFolder(pageModel: PageModel, info: FileInfo?): Page<FileInfo>

    /**
     * 保存一个资源文件列表。
     * @param iterable 需要被保存的对象列表
     * @return 保存到数据后的列表
     */
    fun saveAll(iterable: Iterable<FileInfo>): List<FileInfo>

    /**
     * 从阿里云oss中删除一个一张图片
     * @param fileInfo  图片信息
     */
    fun deleteImageFromAliyunOss(fileInfo: FileInfo)

    /**
     * 删除一个对象
     */
    fun deleteById(id: Long)



    /**
     * 尝试删除一个文件对象
     * @param fileId FileInfo 对象的主键ID
     * @return 是否删除成功
     */
    fun tryToDeleteFileinfo(fileId: Long): Boolean


    fun findByUser(user: User, pageModel: PageModel):Page<FileInfo>

    fun getLinkUrl(file: MultipartFile, config: FileInfoSaveConfig): FileInfo?

    fun saveAllFiles(files: List<MultipartFile>, config: FileInfoSaveConfig): MutableSet<FileInfo>

}
package shop.itbug.ticket.service

import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.entry.FileInfo
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
     * 将前台存储的图片上传到阿里云oss,并记录数据到数据库
     * @param pictures 前端上传的文件列表
     * @param imageType 文件夹类型
     * @param resourcesCategory 文件所属帖子分类,可以为null
     * @return  保存到数据库后的列表
     */
    fun savePicsToSqlData(
        pictures: Array<MultipartFile>?,
        imageType: String,
        resourcesCategory: ResourcesCategory?
    ): List<FileInfo>

    /**
     * 存储一个文件, 只要和文件相关的,都保存为这个对象
     * @param file 文件
     * @param type 类型
     * @param resourcesCategory 可以存储为一个分类, 可以为null
     * @return 存储后的文件的对象
     */
    fun saveFile(file: MultipartFile, type: String, resourcesCategory: ResourcesCategory?): FileInfo

    /**
     * 尝试删除一个文件对象
     * @param fileId FileInfo 对象的主键ID
     * @return 是否删除成功
     */
    fun tryToDeleteFileinfo(fileId: Long): Boolean


    fun findByUser(user: User, pageModel: PageModel):Page<FileInfo>

}
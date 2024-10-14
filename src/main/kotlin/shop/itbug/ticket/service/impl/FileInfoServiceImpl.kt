package shop.itbug.ticket.service.impl

import cn.hutool.core.date.DateUtil
import cn.hutool.core.io.FileUtil
import cn.hutool.core.io.file.FileNameUtil
import jakarta.annotation.Resource
import org.springframework.data.domain.Example
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.admin.model.getCurrentPage
import shop.itbug.ticket.admin.model.getPageable
import shop.itbug.ticket.dao.FileInfoRepository
import shop.itbug.ticket.entry.FileInfo
import shop.itbug.ticket.entry.FileInfoSaveConfig
import shop.itbug.ticket.entry.User
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.exception.CommonEnum
import shop.itbug.ticket.exception.ResultDialogType
import shop.itbug.ticket.service.FileInfoService
import shop.itbug.ticket.service.MinioService
import shop.itbug.ticket.utils.FileManagerWrapper
import shop.itbug.ticket.utils.ImageCheck
import java.io.*
import javax.imageio.ImageIO

/**
 * @author eee
 */
@Service
class FileInfoServiceImpl : FileInfoService {
    @Resource
    private lateinit var fileInfoRepository: FileInfoRepository


    @Resource
    private lateinit var minioService: MinioService

    /**
     * 保存或者修改一个文件类型
     *
     * @param info 文件
     * @return 文件
     */
    override fun saveOrUpdate(info: FileInfo): FileInfo {
        return fileInfoRepository.save(info)
    }

    /**
     * 分页查找文件列表
     *
     * @param pageModel 文件模型
     * @param info      文件查找条件
     * @return 查找结果
     */
    override fun findFilesByFolder(pageModel: PageModel, info: FileInfo?): Page<FileInfo> {
        if (info == null) {
            return fileInfoRepository.findAll(PageRequest.of(pageModel.getCurrentPage(), pageModel.pageSize))
        }
        val createDate =
            PageRequest.of(pageModel.getCurrentPage(), pageModel.pageSize, Sort.Direction.DESC, "createDate")
        return fileInfoRepository.findAll(Example.of(info), createDate)
    }

    /**
     * 保存一个资源文件列表。
     *
     * @param iterable 需要被保存的对象列表
     * @return 保存到数据后的列表
     */
    override fun saveAll(iterable: Iterable<FileInfo>): List<FileInfo> {
        return fileInfoRepository.saveAll(iterable).toList()
    }

    /**
     * 从阿里云oss中删除一个一张图片
     *
     * @param fileInfo 图片信息
     */
    override fun deleteImageFromAliyunOss(fileInfo: FileInfo) {
        fileInfo.id?.let { tryToDeleteFileinfo(it) }
    }

    /**
     * 删除一个对象
     *
     * @param id 主键ID
     */
    override fun deleteById(id: Long) {
        val fileInfoEntity = fileInfoRepository.findById(id)
        try {
            fileInfoRepository.deleteById(id)
        } catch (e: Exception) {
            throw BizException("删除失败：$e", ResultDialogType.Dialog)
        }
        if (fileInfoEntity.isPresent) {
            val info = fileInfoEntity.get()
            info.absolutePath?.apply {
                if (this.isNotEmpty()) {
                    try {
                        val file = File(this)
                        if (file.exists()) {
                            file.delete()//删除本地磁盘文件文件
                        }
                    } catch (e: Exception) {
                        throw BizException("$e")
                    }
                }
            }

            //删除minio
            info.minioObjectName?.let {
                minioService.deleteObjectByName(it)
            }
        }
    }


    /**
     * 尝试删除一个文件对象
     * 注意: 阿里云OSS服务中对应的文件也会被删除
     *
     * @param fileId FileInfo 对象的主键ID
     * @return 是否删除成功
     */
    override fun tryToDeleteFileinfo(fileId: Long): Boolean {
        val byId = fileInfoRepository.findById(fileId)
        return if (byId.isPresent) {
            val fileInfo = byId.get()

            //删除本地文件
            if (fileInfo.thumbnailPath.isNullOrEmpty().not()) {
                try {
                    fileInfo.thumbnailPath?.let { File(it).delete() }
                } catch (_: Exception) {
                }
            }
            if (fileInfo.absolutePath.isNullOrEmpty().not()) {
                try {
                    fileInfo.absolutePath?.let { File(it).delete() }
                } catch (_: Exception) {
                }
            }
            fileInfoRepository.deleteById(fileId)
            true
        } else {
            throw BizException(CommonEnum.NOT_FOUND)
        }
    }

    override fun findByUser(user: User, pageModel: PageModel): Page<FileInfo> {
        val req = pageModel.getPageable("createDate")
        return fileInfoRepository.findAllByUser(user, req)
    }

    override fun getLinkUrl(file: MultipartFile, config: FileInfoSaveConfig): FileInfo? {
        val (user, _, folderName, saveEntity) = config
        if (folderName.isNotBlank() && folderName.startsWith("/")) {
            throw BizException("目录前不能带 / 符号")
        }
        val manager = FileManagerWrapper(file)
        val nameModel = manager.getFinalName()
        val finalName = nameModel.name
        val isImageFile = ImageCheck.isImage(finalName)
        val fileInfo = FileInfo().apply {
            this.fileName = finalName
            this.fileSize = file.size
            this.fileType = FileUtil.extName(finalName)
            this.createDate = DateUtil.date()
            this.ext = FileNameUtil.extName(file.originalFilename)
            this.isImage = isImageFile
            originalFilename = file.originalFilename
        }
        val inputStream: InputStream = file.inputStream
        val fileSize: Long
        if (isImageFile) {
            val buf = inputStream.buffered()
            val size = ImageIO.read(buf)
            fileSize = file.size
            fileInfo.width = size.width
            fileInfo.height = size.height
            buf.close()
        } else {
            fileSize = file.size
        }
        val model = minioService.uploadFileWithInputStream(
            file.inputStream,
            finalName,
            folderName,
            fileSize
        )

        if (isImageFile){
            fileInfo.thumbnail = model.fullUrl
        }

        fileInfo.url = model.url
        fileInfo.fullUrl = model.fullUrl
        fileInfo.minioObjectName = model.objectName
        fileInfo.minioBucketName = model.bucketName
        fileInfo.user = user
        if (!saveEntity) {
            return fileInfo
        }
        return fileInfoRepository.save(fileInfo)
    }


    /**
     * 存储全部图片
     */
    @Transactional
    override fun saveAllFiles(
        files: List<MultipartFile>,
        config: FileInfoSaveConfig
    ): MutableSet<FileInfo> {
        val fileInfos = mutableSetOf<FileInfo>()
        files.forEach {
            fileInfos.add(getLinkUrl(it, config) ?: throw BizException("上传图片失败"))
        }
        return fileInfos
    }

}
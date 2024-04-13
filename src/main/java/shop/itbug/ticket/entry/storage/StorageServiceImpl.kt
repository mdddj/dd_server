package shop.itbug.ticket.entry.storage

import cn.hutool.core.date.DateUtil
import cn.hutool.core.io.FileUtil
import cn.hutool.core.io.file.FileNameUtil
import jakarta.annotation.Resource
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import shop.itbug.ticket.entry.FileInfo
import shop.itbug.ticket.entry.User
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.service.FileInfoService
import shop.itbug.ticket.service.MinioService
import shop.itbug.ticket.service.ResourcesCategoryService
import shop.itbug.ticket.service.UserService
import shop.itbug.ticket.utils.FileManagerWrapper
import shop.itbug.ticket.utils.ImageCheck
import java.awt.Dimension
import java.io.IOException
import javax.imageio.ImageIO


@Service
class StorageServiceImpl : StorageAbstract(), StorageService {



    @Resource
    lateinit var storageRepository: StorageRepository

    @Resource
    lateinit var fileInfoService: FileInfoService

    @Resource
    lateinit var userService: UserService

    @Resource
    lateinit var minioService: MinioService

    @Resource
    lateinit var resourcesCategoryService: ResourcesCategoryService


    override fun findAll(): List<Storage> {
        return storageRepository.findAll()
    }

    override fun save(entity: Storage): Storage {
        return storageRepository.save(entity)
    }

    override fun deleteById(id: Long) {
        storageRepository.deleteById(id)
    }

    override fun findById(id: Long): Storage? {
        return storageRepository.findByIdOrNull(id)
    }

    override fun findAllByPage(pageable: Pageable): Page<Storage> {
        return storageRepository.findAll(pageable)
    }


    /**
     * @param subFolderName 不需要带 / 符号  支持的格式 foldere, folder/sub
     *
     */
    @Transactional()
    override fun getLinkUrl(file: MultipartFile, subFolderName: String, host: String, user: User?): FileInfo? {
        if (subFolderName.isNotBlank() && subFolderName.startsWith("/")) {
            throw BizException("目录前不能带 / 符号")
        }
        val manager = FileManagerWrapper(file)
        val nameModel = manager.getFinalName()
        val finalName = nameModel.name
        val fileInfo = FileInfo().apply {
            this.fileName = finalName
            this.fileSize = file.size
            this.fileType = FileUtil.extName(finalName)
            this.createDate = DateUtil.date()
            this.ext = FileNameUtil.extName(file.originalFilename)
            this.isImage = file.originalFilename?.let { ImageCheck.isImage(it) }
            originalFilename = file.originalFilename
        }

        if (ImageCheck.isImage(finalName)) {
            getImageDimensions(file)?.let { size -> {
                fileInfo.width = size.width
                fileInfo.height = size.height
            } }

        }

        val model = minioService.uploadFile(file, finalName,subFolderName)

        fileInfo.url = model.url
        fileInfo.fullUrl = model.fullUrl
        fileInfo.minioObjectName = model.objectName
        fileInfo.minioBucketName = model.bucketName
        fileInfo.user = user
        return fileInfoService.saveOrUpdate(fileInfo)
    }


    /**
     * 存储全部图片
     */
    @Transactional
    fun saveAllFiles(
        files: List<MultipartFile>,
        subFolderName: String = "",
        host: String = "",
        user: User?
    ): MutableSet<FileInfo> {
        val fileInfos = mutableSetOf<FileInfo>()
        files.forEach {
            fileInfos.add(getLinkUrl(it, subFolderName, host, user) ?: throw BizException("上传图片失败"))
        }
        return fileInfos
    }

    fun getImageDimensions(file: MultipartFile) : Dimension? {
        try {
            file.inputStream.use { inputStream ->
                val image = ImageIO.read(inputStream)
                if (image != null) {
                    val width = image.width
                    val height = image.height
                    return Dimension(width, height)
                }
            }
            return null
        } catch (e: IOException) {
            return null
        }
    }


}
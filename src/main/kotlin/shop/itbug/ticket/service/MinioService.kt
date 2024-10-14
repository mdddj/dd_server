package shop.itbug.ticket.service

import com.alibaba.fastjson2.toJSONString
import jakarta.annotation.Resource
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.service.config.MinioConfig
import shop.itbug.ticket.service.redis.RedisKeys
import shop.itbug.ticket.utils.logger
import shop.itbug.ticket.utils.minio.MinioDetailModel
import shop.itbug.ticket.utils.minio.MinioHandler
import shop.itbug.ticket.utils.minio.MinioObjectResult
import java.io.File
import java.io.InputStream


@Service
class MinioService {

    @Resource
    lateinit var minioConfig: MinioConfig

    var handle: MinioHandler? = null

    @Cacheable(RedisKeys.MINIO_CONFIG + "model", key = "'default'")
    fun getConfigModel() = minioConfig.setting


    /**
     * 更新minio配置
     */

    @CacheEvict(RedisKeys.MINIO_CONFIG + "model", key = "'default'")
    fun updateConfig(model: MinioDetailModel): MinioDetailModel {
        logger.info("更新minio配置:${model.toJSONString()}")
        minioConfig.updateSetting(model)
        return model
    }

    /**
     * 上传文件
     *
     * @param subFolderName 支持格式 sub, sub/sub, sub/sub/sub
     */
    fun uploadFile(file: MultipartFile, fileName: String, subFolderName: String): MinioObjectResult {
        return uploadFileWithInputStream(file.inputStream, fileName, subFolderName,file.size)
    }

    fun uploadFileWithInputStream(inputStream: InputStream, fileName: String, subFolderName: String,size: Long): MinioObjectResult {
        return action { handle, model ->
            return@action handle.uploadStream(
                model.bucketName,
                (if (subFolderName.isNotBlank()) subFolderName + File.separator else "") + fileName,
                inputStream,
                size
            )
        }
    }


    /**
     * 删除某个对象
     */
    fun deleteObjectByName( objectName:String) {
        action { handle, model ->
            handle.deleteObject(model.bucketName, objectName)
        }
    }


    private fun <T> action(doHandle: (handle: MinioHandler, model: MinioDetailModel) -> T): T {
        val minioSetting = minioConfig.setting
        try {
            if (minioSetting.bucketName.isBlank()) {
                throw BizException("未配置 bucketName")
            }
            handle = MinioHandler(minioSetting)
            return doHandle(handle!!, minioSetting)
        } catch (e: BizException) {
            throw e
        } catch (e: Exception) {
            e.printStackTrace()
            throw BizException("读取 Minio 配置文件失败")
        }
    }
}
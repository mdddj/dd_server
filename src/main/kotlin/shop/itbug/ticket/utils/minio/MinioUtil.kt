package shop.itbug.ticket.utils.minio

import cn.hutool.core.io.FileUtil
import io.minio.*
import io.minio.errors.MinioException
import io.minio.http.Method
import shop.itbug.ticket.ex.log
import shop.itbug.ticket.exception.BizException
import java.io.InputStream

data class MinioObjectResult(
    val url: String,
    val fullUrl: String,
    val objectName: String,
    val bucketName: String
)

class MinioHandler(val model: MinioDetailModel) {
    private val minioClient: MinioClient = MinioClient.builder()
        .endpoint(model.endpoint)
        .credentials(model.accessKey, model.secretKey)
        .build()

    fun listBuckets(): List<String> {
        return try {
            minioClient.listBuckets().map { it.name() }
        } catch (e: MinioException) {
            emptyList()
        }
    }

    fun bucketExists(bucketName: String): Boolean {
        return try {
            minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())
        } catch (e: MinioException) {
            false
        }
    }

    fun createBucket(bucketName: String) {
        try {
            if (!bucketExists(bucketName)) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build())
            }
        } catch (e: MinioException) {
            println("Error occurred: $e")
        }
    }

    fun uploadFile(bucketName: String, objectName: String, filePath: String) {
        try {
            minioClient.uploadObject(
                UploadObjectArgs.builder()
                    .bucket(bucketName)
                    .`object`(objectName)
                    .filename(filePath)
                    .build()
            )
        } catch (e: MinioException) {
            println("Error occurred: $e")
        }
    }

    fun uploadStream(bucketName: String, objectName: String, stream: InputStream, size: Long): MinioObjectResult {
        try {
            minioClient.putObject(
                PutObjectArgs.builder()
                    .bucket(bucketName)
                    .`object`(objectName)
                    .stream(stream, size, -1)
                    .contentType(FileUtil.getMimeType(objectName))
                    .build()
            )
            return getObjectUrl(bucketName, objectName)
        } catch (e: BizException) {
            throw e
        } catch (e: MinioException) {
            log().warn("Error occurred: ${e.localizedMessage}")
            throw BizException("上传文件失败:$e")
        }
    }

    fun downloadFile(bucketName: String, objectName: String, downloadPath: String) {
        try {
            minioClient.downloadObject(
                DownloadObjectArgs.builder()
                    .bucket(bucketName)
                    .`object`(objectName)
                    .filename(downloadPath)
                    .build()
            )
        } catch (e: MinioException) {
            println("Error occurred: $e")
        }
    }


    fun deleteObject(bucketName: String, objectName: String) {
        minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName)
            .`object`(objectName)
            .build())
    }

    private fun getObjectUrl(bucketName: String, objectName: String): MinioObjectResult {
        val objectUrl = try {
            minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder().bucket(bucketName)
                    .`object`(objectName)
                    .method(Method.GET)
                    .build()
            )
        } catch (e: MinioException) {
            println("Error occurred: $e")
            throw BizException("获取文件访问路径失败:$e")
        }
        return MinioObjectResult(
            url = removeQueryAndFragment(objectUrl),
            fullUrl = objectUrl,
            objectName = objectName,
            bucketName = bucketName
        )
    }
}


private fun removeQueryAndFragment(url: String): String {
    val parts = url.split("[?#]".toRegex()) // 分割 URL，去除查询参数和片段标识符
    return parts[0] // 返回处理后的 URL
}
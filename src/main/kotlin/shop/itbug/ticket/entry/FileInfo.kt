package shop.itbug.ticket.entry

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.media.SchemaProperty
import jakarta.persistence.*
import java.util.*



data class FileInfoSaveConfig(
    val user: User? = null,
    val host: String = "",
    val folderName: String = "",
    ///是否需要把file info 持久化到数据库?
    val saveEntity: Boolean = true
)

/**
 * @author eee
 */
@Entity
@Schema(name = "文件实体类")
class FileInfo {

    @Id
    @GeneratedValue
    var id: Long? = null


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties
    @JsonIgnore
    var user: User? = null

    /**
     * 文件名字
     */
    var fileName: String? = null

    /**
     * 文件类型
     */
    var fileType: String? = null

    /**
     * 文件大小
     */
    var fileSize: Long? = null

    /**
     * 绝对路径
     */
    var absolutePath: String? = null

    /**
     * 上传时间
     */
    var createDate: Date? = null

    /**
     * 是否为收藏文件
     */
    @SchemaProperty(name = "是否已收藏状态")
    var isCollect: Boolean? = null

    /**
     * 最近修改日期
     */
    var updateDate: Date? = null

    /**
     * 设置标记颜色
     */
    var tagColor: String? = null

    /**
     * 下载url
     */
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    var url: String? = null

    /**
     * 备忘录
     */
    var intro: String? = null


    /**
     * 是图片才有这个属性
     * 图片的原始宽度
     */
    var width: Int? = null

    /**
     * 是图片类型才有这个属性
     * 图片的高度
     */
    var height: Int? = null


    @Schema(description = "缩略图链接")
    @Column(length = 10000)
    @Lob
    var thumbnail: String? = null

    @Schema(description = "缩略图保存路径")
    @Column(length = 10000)
    @Lob
    var thumbnailPath: String? = null


    @Schema(description = "原文件名")
    @Column(columnDefinition = "LONGTEXT")
    @Lob
    var originalFilename: String? = null

    @Schema(description = "完整的链接,可能会失效")
    @Column(columnDefinition = "LONGTEXT")
    @Lob
    var fullUrl: String? = null

    @Schema(description = "minio的文件名")
    @Column(columnDefinition = "LONGTEXT")
    @Lob
    var minioObjectName : String? = null

    @Schema(description = "minio存储桶")
    var minioBucketName : String? = null

    @Schema(description = "是否为图片")
    var isImage: Boolean? = null

    @Schema(description = "扩展")
    var ext: String? = null

    override fun toString(): String {
        return "file info: $url"
    }
}



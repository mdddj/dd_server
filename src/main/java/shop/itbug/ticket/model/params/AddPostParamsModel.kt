package shop.itbug.ticket.model.params

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.multipart.MultipartFile
import shop.itbug.ticket.entry.Mianji
import java.util.*

@Schema(name = "发布博客参数")
data class AddPostParamsModel(

    @Schema(description = "修改模式需要传入这个属性")
    var id : Long? = null,

    @Schema(description = "正文内容")
    var content: String? = null,

    @Schema(description = "分类ID")
    var categoryId: Long? = null,

    @Schema(description = "面基对象")
    var mianji: Mianji? = null,

    @Schema(description = "标题")
    var title: String? = null,

    @Schema(description = "类型")
    var type: String? = null,

    @Schema(description = "标签")
    var label: String? = null,

    @Schema(description = "权限")
    var authority: Int? = null,

    @Schema(description = "点击数")
    var clickCount: Long? = null,

    @Schema(description = "发布时间")
    var createDate: Date? = null,

    @Schema(description = "缩略图")
    var thumbnailImage: String? = null,

    @Schema(description = "简介")
    var description: String? = null,

    @Schema(description = "链接")
    var links: String? = null,

    @Schema(description = "跳转链接")
    var browserUrl: String? = null,

    var updateDate: Date? = null,



    @RequestPart(name = "pictures")
    @RequestParam(name = "pictures")
    var pictures: Array<MultipartFile>? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AddPostParamsModel

        if (id != other.id) return false
        if (content != other.content) return false
        if (categoryId != other.categoryId) return false
        if (mianji != other.mianji) return false
        if (title != other.title) return false
        if (type != other.type) return false
        if (label != other.label) return false
        if (authority != other.authority) return false
        if (clickCount != other.clickCount) return false
        if (createDate != other.createDate) return false
        if (thumbnailImage != other.thumbnailImage) return false
        if (description != other.description) return false
        if (links != other.links) return false
        if (browserUrl != other.browserUrl) return false
        if (updateDate != other.updateDate) return false
        if (pictures != null) {
            if (other.pictures == null) return false
            if (!pictures.contentEquals(other.pictures)) return false
        } else if (other.pictures != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (content?.hashCode() ?: 0)
        result = 31 * result + (categoryId?.hashCode() ?: 0)
        result = 31 * result + (mianji?.hashCode() ?: 0)
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (type?.hashCode() ?: 0)
        result = 31 * result + (label?.hashCode() ?: 0)
        result = 31 * result + (authority ?: 0)
        result = 31 * result + (clickCount?.hashCode() ?: 0)
        result = 31 * result + (createDate?.hashCode() ?: 0)
        result = 31 * result + (thumbnailImage?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (links?.hashCode() ?: 0)
        result = 31 * result + (browserUrl?.hashCode() ?: 0)
        result = 31 * result + (updateDate?.hashCode() ?: 0)
        result = 31 * result + (pictures?.contentHashCode() ?: 0)
        return result
    }
}
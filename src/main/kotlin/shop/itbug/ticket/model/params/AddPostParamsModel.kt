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

)
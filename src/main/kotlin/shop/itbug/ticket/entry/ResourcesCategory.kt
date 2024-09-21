package shop.itbug.ticket.entry

import com.alibaba.fastjson2.annotation.JSONField
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.media.SchemaProperty
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank


///类型枚举
enum class ResourceType (val type: String) {
    IdeaRoom("idea-chat-room"),
    IdeaHelpForum("idea-forum"),
    Folder("folder"),
    Doc("doc"),
    Image("images"),
    DocDirectory("doc-directory"),
    Version("version"),
    PrivacyPolicy("Privacy")

}

///带有count
@Schema(name = "资源分类(带数量)")
interface ResourcesCategoryCounter {
    val description: String
    val id: Long
    val level: Long
    val logo: String
    val name: String
    val resourceCount: Int
    val type: String
}


/**
 * 文件夹 / 群组
 * @author eee
 */
@Entity
@Schema(name = "资源分类")
open class ResourcesCategory {



    @Id
    @GeneratedValue
    @Schema(description = "主键ID")
    open var id: Long? = null

    /**
     * 资源的标题
     */
    @SchemaProperty(name = "频道(分类)名字")
    open var name: @NotBlank(message = "请输入频道名字") String? = null

    /**
     * 资源的logo
     */
    @SchemaProperty(name = "logo显示")
    open var logo: String? = null

    /**
     * 频道描述
     */
    @SchemaProperty(name = "描述")
    open var description: String? = null

    /**
     * 频道公告,置顶
     */
    @ManyToOne
    @JSONField(serialize = false)
    open var announcement: MyResources? = null

    /**
     * 频道的类型
     */
    @SchemaProperty(name = "频道(分类)类型")
    open var type: String? = null


    /**
     * 父节点
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JSONField(serialize = false)
    @JsonIgnoreProperties
    @JsonIgnore
    open var parentNode: ResourcesCategory? = null

    /**
     * 分类等级，如果是1，为顶级菜单，否则不是
     */
    @Schema(description = "分类等级，如果是1，为顶级菜单，否则不是")
    open var level: Int? = null


    @Transient
    @Schema(description = "动态数量")
    open var resourceCount: Int? = null


    @Schema(description="图片")
    @OneToOne
    open var fileInfo: FileInfo? = null
}
package shop.itbug.ticket.entry

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import shop.itbug.ticket.entry.project.Project
import java.io.Serializable
import java.time.ZonedDateTime


///平台
@Schema(name = "平台类型")
enum class VersionPlatform {
    Android, Ios, Macos, Windows, Linux
}

/**
 * app 版本更新
 */
@Entity
@Schema(name = "版本")
open class Version : Serializable {
    @Id
    @GeneratedValue
    @Schema(description = "主键")
    open var id: Long? = null

    /**
     * 版本标题
     */
    @Schema(description = "标题")
    open var title: String? = null

    /**
     * 版本更新记录
     */
    @Lob
    @Schema(description = "更新记录")
    @NotBlank(message = "请填写版本更新内容")
    open var description: String? = null

    /**
     * api下载地址
     */
    @Lob
    @Schema(description = "下载地址(直链)")
    open var downloadUrl: String? = null

    /**
     * 发布平台
     */
    @Schema(description = "平台")
    @NotNull(message = "请填写平台类型")
    open var platform: VersionPlatform? = null


    /**
     * 发布时间
     */
    @Schema(description = "创建时间")
    open var createDate: ZonedDateTime? = null

    /**
     * 版本下载页面地址,
     */
    @Lob
    @Schema(description = "第三方下载地址")
    open var htmlViewPage: String? = null

    /**
     * 安装包大小
     */
    @Schema(description = "包大小")
    open var packageSize: Long? = null

    /**
     * 是否有效
     */
    @Schema(description = "是否有效")
    open var enable: Boolean? = null

    /**
     * 版本号
     */
    @Schema(description = "版本号")
    @NotBlank(message = "请填写安装包版本号")
    open var versionNumber: String? = null


    @Schema(description = "项目名称")
    open var projectName: String? = null

    @OneToOne
    @Schema(description = "关联项目")
    open var project: Project? = null
}
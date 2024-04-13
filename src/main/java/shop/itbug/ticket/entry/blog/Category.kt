package shop.itbug.ticket.entry.blog

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import java.io.Serializable
import java.util.*

/**
 * @author ldd
 */
@Entity
@Schema(name = "博客分类")
open class Category : Serializable {
    @Id
    @GeneratedValue
    @Schema(description = "主键ID")
    open var id: Long? = null

    @Schema(description = "分类名")
    @NotBlank(message = "请输入分类名称")
    open var name: String? = null

    @Schema(description = "分类图标")
    open var logo: String? = null

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    @Schema(description = "介绍,简介")
    open var intro: String? = null

    @Schema(description = "创建时间")
    open var createTime: Date? = null


}
package shop.itbug.ticket.entry.system

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

/**
 * 大淘客的相关信息
 */

@Entity
@Schema(name = "折淘客账号")
open class DTKDetail {
    @Id
    @GeneratedValue
    @Schema(description = "主键ID")
    open var id: Long? = null

    @Schema(description = "大淘客appKey",required = true)
    open var appKey: String? = null

    @Schema(description = "大淘客secret", required = true)
    open var appSecret: String? = null

    @Schema(description = "名称标题")
    open var title: String? = null

    @Schema(description = "备注")
    open var intro: String? = null

    /**
     * 启用默认
     */
    @Schema(description = "是否为默认启用账号")
    open var selectDefault: Boolean? = null

    @Schema(description = "logo")
    open var logo: String? = null

    @Schema(description = "渠道关系ID", example = "2518946481")
    open var relationId: String? = null
}
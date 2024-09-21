package shop.itbug.ticket.entry.blog

import com.fasterxml.jackson.annotation.JsonIgnore
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Lob
import jakarta.validation.constraints.NotBlank
import java.util.*

/**
 * @author eee
 */
@Entity
@Schema(name = "字典")
open class Text {
    @Id
    @GeneratedValue
    @Schema(description = "主键ID")
    open var id: Long? = null

    /**
     * 获取长文本内容的关键字,需要是唯一的
     */
    @NotBlank(message = "请输入关键字！")
    @Schema(description = "查询关键字")
    open var name: String? = null

    /**
     * 介绍
     */
    @Lob
    @Schema(description = "介绍")
    open var intro: String? = null

    /**
     * 长文本内容
     */
    @Lob
    @Schema(description = "正文内容")
    open var context: String? = null

    /**
     * 是否为需要数据密码才能查看,
     * 如果没有设置则为false, 默认所有人都能查看
     */
    @Schema(description = "是否加密")
    open  var isEncryptionText: Boolean? = null

    /**
     * 查看字段的密码
     */
    @Schema(description = "查看密码")
    open var viewPassword: String? = null

    /**
     * 原始查看密码,
     * 这个字段就不用返回给前台展示了
     */
    @JsonIgnore
    @Schema(description = "原始查看密码")
    open var originPassword: String? = null


    @Schema(description = "创建时间")
    open var createDate: Date? = null

    @Schema(description = "最后修改时间")
    open var updateDate: Date? = null
}
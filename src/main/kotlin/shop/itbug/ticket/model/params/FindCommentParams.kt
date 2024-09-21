package shop.itbug.ticket.model.params

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

/**
 * @author ldd
 */
open class FindCommentParams {
    /**
     * 查询的关键字
     */
    open var findKey: @NotBlank(message = "请输入查询的关键ID获取特殊查询关键字") String? = null

    /**
     * 评论的类型,比如博客的为"blog"
     */
    open var type: @NotBlank(message = "请输入查询的评论类型") String? = null

    /**
     * 第几页
     */
    open var page: @NotNull(message = "请输入页码") Int? = null
}
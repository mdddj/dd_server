package shop.itbug.ticket.model.blog

import jakarta.validation.constraints.NotNull

open class TagBlogsParam {
    /**
     * 标签id
     */
    open var tagId: @NotNull(message = "请输入标签id") Long? = null

    /**
     * 第几页, 从1开始算
     */
    open var page: @NotNull(message = "请输入页码") Int? = null

    /**
     * 每页几条数据
     */
    open var pageSize: @NotNull(message = "请输入每页数量") Int? = null
}
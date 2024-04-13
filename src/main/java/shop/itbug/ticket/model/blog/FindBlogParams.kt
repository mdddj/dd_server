package shop.itbug.ticket.model.blog

import jakarta.validation.constraints.NotNull

open class FindBlogParams {
    open var page: @NotNull(message = "请输入页码") Int? = null
    open var pageSize: @NotNull(message = "请输入每页数量") Int? = null
}
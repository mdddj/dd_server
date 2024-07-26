package shop.itbug.ticket.model.blog

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.URL

class UpdateThumbnailParams {
    val id: @NotNull(message = "请输入博客id") Long? = null
    val url: @NotBlank(message = "请输入博客缩略图url") @URL(message = "不是一个正确的url") String? = null
}
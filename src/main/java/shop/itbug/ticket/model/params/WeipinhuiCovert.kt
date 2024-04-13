package shop.itbug.ticket.model.params

import jakarta.validation.constraints.NotBlank

/**
 * 唯品会转链
 */
class WeipinhuiCovert {
    private val url: @NotBlank(message = "输入唯品会id或者url") String? = null
    private val chanTag: String? = null
}
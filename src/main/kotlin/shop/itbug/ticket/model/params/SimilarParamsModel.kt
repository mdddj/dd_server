package shop.itbug.ticket.model.params

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

@Schema(name = "猜你喜欢参数")
open class SimilarParamsModel {

    /**
     * 大淘客的商品id
     */
    @Schema(description = "大淘客的商品id")
    @NotBlank(message = "请输入产品ID")
    open var id: String = ""

    /**
     * 每页条数，默认10 ， 最大值100
     */
    @Schema(description = "数量", defaultValue = "100")
    @NotBlank(message = "请输入获取数据条数")
    open  var size: String = ""
}
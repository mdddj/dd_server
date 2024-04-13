package shop.itbug.ticket.model.params

import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.media.SchemaProperty
import jakarta.validation.constraints.NotNull

@Schema(name = "主键ID参数")
class IdKey {

    @field: NotNull(message = "请输入ID")
    @SchemaProperty(name= "主键ID")
    var idValue: Long? = null
}

@Schema(name = "主键ID")
data class IdBody(@Schema(description = "主键ID") val id : Long)
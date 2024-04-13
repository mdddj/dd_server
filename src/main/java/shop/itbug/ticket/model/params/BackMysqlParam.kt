package shop.itbug.ticket.model.params

import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.media.SchemaProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

@Schema(name = "数据库备份参数")
data class BackMysqlParam (
    @SchemaProperty(name = "文件名")
    val fileName: @NotBlank(message = "请输入mysql备份文件名") String? = null,
    @SchemaProperty(name = "mysql版本是否大于8")
    val dbIsVersion8: @NotNull(message = "请输入mysql版本是否大于8") Boolean? = null
)
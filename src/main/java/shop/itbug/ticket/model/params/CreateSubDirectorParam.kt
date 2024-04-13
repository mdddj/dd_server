package shop.itbug.ticket.model.params

import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.media.SchemaProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

@Schema(name = "创建Doc类型的参数模型")
class CreateSubDirectorParam {

    @field: NotNull(message = "请填写父亲节点ID")
    var parentNodeId: Long? = null

    @field: NotBlank(message = "请填写目录名字")
    var name: String? = null

    /**
     * 如果是修改操作,需要设置修改的ID
     */
    @SchemaProperty(name = "如果是修改操作,需要设置修改的ID")
    var updateId: Long? = null

    var description: String? = null
}
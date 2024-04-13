package shop.itbug.ticket.model.params

import io.swagger.v3.oas.annotations.media.Schema

///修改用户信息的参数
@Schema(name = "修改用户资料的参数")
class UpdateUserParams {
    @Schema(name = "修改昵称")
    var nickName: String = ""


    @Schema(name = "修改头像")
    var picture: String? = null

    @Schema(name = "修改手机号")
    var phone: String? = null
}
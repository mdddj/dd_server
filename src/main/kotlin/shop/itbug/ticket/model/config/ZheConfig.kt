package shop.itbug.ticket.model.config

import io.swagger.v3.oas.annotations.media.Schema
import java.io.Serializable


///折淘客配置
@Schema(name = "折淘客配置模型")
data class ZheConfigModel(
    ///
    @Schema(description = "app key")
    var appKey: String = "",
    @Schema(description = "sid")
    var sid: String = "",
    ///淘宝授权回调URL
    @Schema(description = "淘宝授权回调URL")
    var tbAuthBackUrl:String = "",
    ///唯品会sid
    @Schema(description = "唯品会sid")
    var wphSid: String = ""
) : Serializable
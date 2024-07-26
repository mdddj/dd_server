package shop.itbug.ticket.service.config

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.stereotype.Service
import java.io.Serializable

@Schema(name = "小程序博客配置")
data class MiniAppConfigModel(
    @Schema(description = "姓名")
    var nickName: String = "梁典典",
    @Schema(description = "性别")
    var gender: String = "男",
    @Schema(description = "地址")
    var address: String = "中国,广东,广州",
    @Schema(description = "职业")
    var job : String = "Flutter开发者",
    @Schema(description = "技术栈")
    var codeStack: String = "Flutter,Kotlin,Java,Rust,Android",
    @Schema(description = "个人描述")
    var descriptionText: String = "长长的描述",
    @Schema(description = "底部按钮")
    var moreText: String = "查看更多",
    @Schema(description = "查看更多的字典key")
    var moreTextKey: String = "miniMore",
    @Schema(description = "按钮1标题")
    var btnOneTitle: String = "加我好友",
    @Schema(description = "按钮1字典key")
    var btnOneKey: String = "aboutMe",
    @Schema(description = "按钮2标题")
    var btnTwoTitle: String = "合作",
    @Schema(description = "按钮2字典key")
    var btnTwoKey: String = "getMoney",
    @Schema(description = "头像")
    var avatar: String = ""

): Serializable

@Service
class MiniAppConfig: ConfigBase<MiniAppConfigModel>(MiniAppConfigModel::class) {
    override val configName: String
        get() = "mini-app-config"
    override val defaultModel: MiniAppConfigModel
        get() = MiniAppConfigModel()
}
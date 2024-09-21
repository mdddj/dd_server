package shop.itbug.ticket.service.config

import org.springframework.stereotype.Service
import shop.itbug.ticket.model.config.ZheConfigModel

@Service
class ZheConfigService : ConfigBase<ZheConfigModel>(ZheConfigModel::class) {
    override val configName: String
        get() = "zhe_tao_ke_config"
    override val defaultModel: ZheConfigModel
        get() = ZheConfigModel()

    fun getRequestParam() : HashMap<String,Any> {
        return hashMapOf("apiKey" to setting.appKey)
    }


}
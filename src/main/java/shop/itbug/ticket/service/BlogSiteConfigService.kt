package shop.itbug.ticket.service

import jakarta.annotation.Resource
import org.springframework.stereotype.Service
import shop.itbug.ticket.model.BlogSiteConfig
import shop.itbug.ticket.model.BlogSiteConfigImpl

@Service
class BlogSiteConfigService {

    @Resource
    private lateinit var blogSiteConfigImpl: BlogSiteConfigImpl


    //    获取配置
    fun getConfig(): BlogSiteConfig {
        return blogSiteConfigImpl.setting
    }


//    修改配置
    fun updateConfig(newModel: BlogSiteConfig){
        blogSiteConfigImpl.updateSetting(newModel)
    }

}
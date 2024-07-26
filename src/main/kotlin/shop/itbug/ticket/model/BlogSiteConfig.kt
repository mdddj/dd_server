package shop.itbug.ticket.model

import org.springframework.stereotype.Service
import shop.itbug.ticket.service.config.ConfigBase
import java.io.Serializable

data class BlogSiteConfig(

//    icp备案号
    var icp: String = "icp备案",
//    github链接
    var github: String = "",

    ) : Serializable


// 博客配置
@Service
class BlogSiteConfigImpl : ConfigBase<BlogSiteConfig>(BlogSiteConfig::class){
    override val configName: String
        get() = "blog_site_config"
    override val defaultModel: BlogSiteConfig
        get() = BlogSiteConfig()
}
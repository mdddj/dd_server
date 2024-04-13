package shop.itbug.ticket

import jakarta.annotation.Resource
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import shop.itbug.ticket.entry.ResourceType
import shop.itbug.ticket.service.ResourcesCategoryService

@Component
class ResourceInit: ApplicationRunner {
    @Resource private lateinit var resourcesCategoryService: ResourcesCategoryService

    override fun run(args: ApplicationArguments?) {
        resourcesCategoryService.createCategoryWithSimple("典の面基",ResourceType.Image,"","")
        resourcesCategoryService.createCategoryWithSimple("随手拍",ResourceType.Image,"","")
        resourcesCategoryService.createCategoryWithSimple("无聊的生活",ResourceType.Image,"","")
        resourcesCategoryService.createCategoryWithSimple("平凡的日子",ResourceType.Image,"","")
        resourcesCategoryService.createCategoryWithSimple("代码的相关",ResourceType.Image,"","")
        resourcesCategoryService.createCategoryWithSimple("小动物额",ResourceType.Image,"","")
        resourcesCategoryService.createCategoryWithSimple("喝茶吗",ResourceType.Image,"","")
        resourcesCategoryService.createCategoryWithSimple("四季",ResourceType.Image,"","")
        resourcesCategoryService.createCategoryWithSimple("护肤吗",ResourceType.Image,"","")
        resourcesCategoryService.createCategoryWithSimple("其他归档",ResourceType.Image,"","")
        resourcesCategoryService.createCategoryWithSimple("网站记录",ResourceType.Image,"","")
        resourcesCategoryService.createCategoryWithSimple("Rust学习笔记",ResourceType.Doc,"","")
        resourcesCategoryService.createCategoryWithSimple("Flutter学习笔记",ResourceType.Doc,"","")
        resourcesCategoryService.createCategoryWithSimple("Swift学习笔记",ResourceType.Doc,"","")
        resourcesCategoryService.createCategoryWithSimple("rust生命周期",ResourceType.DocDirectory,"","")
        resourcesCategoryService.createCategoryWithSimple("rust异常处理",ResourceType.DocDirectory,"","")
        resourcesCategoryService.createCategoryWithSimple("rust基础",ResourceType.DocDirectory,"","")
        resourcesCategoryService.createCategoryWithSimple("典典小卖部版本更新记录",ResourceType.Version,"","")
        resourcesCategoryService.createCategoryWithSimple("典典小卖部隐私政策",ResourceType.PrivacyPolicy,"","")
    }
}
package shop.itbug.ticket

import jakarta.annotation.Resource
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import shop.itbug.ticket.entry.ResourceType
import shop.itbug.ticket.service.ResourcesCategoryService

@Component
class IdeaInitConfig : ApplicationRunner {

    @Resource
    private lateinit var resourcesCategoryService: ResourcesCategoryService

    override fun run(args: ApplicationArguments?) {
        start()
    }

    private fun start() {
        resourcesCategoryService.createCategoryWithSimple(
            "闲聊吹水",
            ResourceType.IdeaRoom,
            null,
            "idea flutter社区闲聊频道"
        )
        resourcesCategoryService.createCategoryWithSimple(
            "问题求助",
            ResourceType.IdeaRoom,
            null,
            "idea flutter问题发布频道"
        )
    }
}
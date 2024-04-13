package shop.itbug.ticket.controller

import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import shop.itbug.ticket.service.PddService

@RestController
@RequestMapping("/pdd/")
@Tag(name = "拼多多接口")
class PddController {
    @Resource
    private lateinit var pddService: PddService
    @GetMapping("/gen-auth-url")
    @Throws(Exception::class)
    fun pddGenerAuthUrl(): String? {
        return pddService.generBeianUrl()
    }


    /**
     * 获取平多多推荐商品
     *
     * @param page        第几页 从0开始
     * @param channelType 进宝频道推广商品: 1-今日销量榜,3-相似商品推荐,4-猜你喜欢(和进宝网站精选一致),5-实时热销榜,6-实时收益榜。默认值5
     * @return json数据
     */
    @GetMapping("/recommend")
    fun recommendGoods(page: Int, channelType: Int): String? {
        try {
            return pddService.recommend(page, channelType)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }

    @GetMapping("/covert")
    fun pddCovert(id: String?): String? {
        try {
            return pddService.covert(id)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }
}
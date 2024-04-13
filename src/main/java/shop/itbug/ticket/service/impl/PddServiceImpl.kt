package shop.itbug.ticket.service.impl

import org.springframework.stereotype.Service
import shop.itbug.ticket.service.PddService

@Service
class PddServiceImpl : PddService {
    @Throws(Exception::class)
    override fun generBeianUrl(): String {
        return "todo: 未实现"
    }

    @Throws(Exception::class)
    override fun detail(goodsId: String?): String? {
        return "todo: 未实现"
    }

    /**
     * 获取拼多多推荐商品
     *
     * @param page 翻页
     * @return json数据
     */
    @Throws(Exception::class)
    override fun recommend(page: Int, channelType: Int): String? {
        return "todo: 未实现"
    }

    /**
     * 拼多多商品转链
     *
     * @param goodsSign 商品id
     * @return json
     */
    @Throws(Exception::class)
    override fun covert(goodsSign: String?): String? {
       return "todo: 未实现"
    }
}
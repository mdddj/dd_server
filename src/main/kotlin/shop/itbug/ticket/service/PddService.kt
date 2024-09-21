package shop.itbug.ticket.service

import org.springframework.stereotype.Service

@Service
interface PddService {
    @Throws(Exception::class)
    fun generBeianUrl(): String?

    @Throws(Exception::class)
    fun detail(goodsId: String?): String?

    /**
     * 获取拼多多推荐商品
     * @param page  翻页
     * @return  json数据
     */
    @Throws(Exception::class)
    fun recommend(page: Int, channelType: Int): String?

    /**
     * 拼多多商品转链
     * @param goodsSign 商品id
     * @return  json
     */
    @Throws(Exception::class)
    fun covert(goodsSign: String?): String?
}
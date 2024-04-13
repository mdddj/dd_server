package shop.itbug.ticket.service

import org.springframework.stereotype.Service
import shop.itbug.ticket.entry.Carousel

@Service
interface CarouselService {
    /**
     * 创建一个轮播图
     * @param imageUrl 图床地址src
     * @param title 标题
     * @param location  摆放位置
     * @param clickType 点击类型
     * @param params    事件参数
     * @return obj
     */
    fun create(imageUrl: String?, title: String?, location: String?, clickType: String?, params: String?): Carousel

    /**
     * 获取轮播图列表
     * @param location  摆放位置
     * @return List<obj>
    </obj> */
    fun selectList(location: String?): List<Carousel?>?

    /**
     * 删除一个轮播图
     * @param id   轮播图id
     */
    fun deleteOne(id: Long)

    /**
     * 查找全部轮播图
     * @return 列表
     */
    fun selectAll(): List<Carousel?>
}
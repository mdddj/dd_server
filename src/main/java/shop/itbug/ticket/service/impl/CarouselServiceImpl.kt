package shop.itbug.ticket.service.impl

import jakarta.annotation.Resource
import org.apache.commons.lang3.StringUtils
import org.springframework.stereotype.Service
import shop.itbug.ticket.dao.CarouselDao
import shop.itbug.ticket.entry.Carousel
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.exception.CommonEnum
import shop.itbug.ticket.service.CarouselService

@Service
class CarouselServiceImpl : CarouselService {
    @Resource
    private val carouselDao: CarouselDao? = null

    /**
     * 创建一个轮播图
     *
     * @param imageUrl  图床地址src
     * @param title     标题
     * @param location  摆放位置
     * @param clickType 点击类型
     * @param params    事件参数
     * @return obj
     */
    override fun create(
        imageUrl: String?,
        title: String?,
        location: String?,
        clickType: String?,
        params: String?
    ): Carousel {
        if (StringUtils.isBlank(imageUrl) || StringUtils.isBlank(location) || StringUtils.isBlank(clickType)) throw BizException(
            CommonEnum.PARAMS_ERROR
        )
        val carousel = Carousel()
        carousel.imgUrl = imageUrl
        carousel.clickType = clickType
        carousel.params = params
        carousel.location = location
        carousel.title = title
        return carouselDao!!.save(carousel)
    }

    /**
     * 获取轮播图列表
     *
     * @param location 摆放位置
     * @return List<obj>
    </obj> */
    override fun selectList(location: String?): List<Carousel?>? {
        if (StringUtils.isBlank(location)) throw BizException(CommonEnum.PARAMS_ERROR)
        return carouselDao!!.findAllByLocation(location)
    }

    /**
     * 删除一个轮播图
     *
     * @param id 轮播图id
     */
    override fun deleteOne(id: Long) {
        carouselDao!!.deleteById(id)
    }

    /**
     * 查找全部轮播图
     *
     * @return 列表
     */
    override fun selectAll(): List<Carousel?> {
        return carouselDao!!.findAll()
    }
}
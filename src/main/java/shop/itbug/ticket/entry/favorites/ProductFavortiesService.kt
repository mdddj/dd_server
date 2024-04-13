package shop.itbug.ticket.entry.favorites

import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.entry.User

@Service
interface ProductFavortiesService {

    /**
     * 存储或者修改某个收藏产品
     * @param 要收藏的产品
     */
    fun save(product: ProductFavorites): ProductFavorites

    /**
     * 移除收藏
     */
    fun remove(id: Long)

    /**
     * 根据用户和主键查询
     *
     */
    fun findListWithUserAndId(user: User,id: Long): List<ProductFavorites>


    /**
     * 批量移除收藏
     */
    fun batchDeleteWithIds(user: User,ids: List<Long>)

    /**
     * 查询用户收藏的产品
     */
    fun findList(user: User, pageModel: PageModel): Page<ProductFavorites>

}
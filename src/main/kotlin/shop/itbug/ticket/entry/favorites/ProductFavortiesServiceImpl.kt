package shop.itbug.ticket.entry.favorites

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.admin.model.getCurrentPage
import shop.itbug.ticket.entry.User
import shop.itbug.ticket.exception.BizException

@Service
class ProductFavortiesServiceImpl: ProductFavortiesService {

    @Autowired lateinit var productFavoritesRepository: ProductFavoritesRepository
    /**
     * 存储或者修改某个收藏产品
     * @param 要收藏的产品
     */
    override fun save(product: ProductFavorites): ProductFavorites {
        return productFavoritesRepository.save(product)
    }

    /**
     * 移除收藏
     */
    override fun remove(id: Long) {
        try {
            productFavoritesRepository.deleteById(id)
        }catch (e: Exception){
            throw BizException("删除失败,请稍后重试")
        }
    }

    /**
     * 根据用户和主键查询
     *
     */
    override fun findListWithUserAndId(user: User, id: Long): List<ProductFavorites> {
       return productFavoritesRepository.findAllByUserAndId(user, id)
    }

    /**
     * 批量移除收藏
     */
    override fun batchDeleteWithIds(user: User,ids: List<Long>) {
        val exp = ProductFavorites()
        exp.user = user
    }

    /**
     * 查询用户收藏的产品
     */
    override fun findList(user: User, pageModel: PageModel): Page<ProductFavorites> {
        val pageReq = PageRequest.of(pageModel.getCurrentPage(),pageModel.pageSize,Sort.Direction.DESC,"createTime")
        return productFavoritesRepository.findAllByUser(user,pageReq)
    }
}
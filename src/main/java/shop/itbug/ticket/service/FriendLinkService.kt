package shop.itbug.ticket.service

import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.entry.FriendLink

@Service
interface FriendLinkService {
    /**
     * 根据条件获取全部友链
     * @param friendLink 条件
     * @return 列表
     */
    fun findAllBy(friendLink: FriendLink): List<FriendLink>


    /**
     * 查找全部
     */
    fun findAll() : List<FriendLink>

    /**
     * 创建或者修改一个友链
     * @param friendLink 对象
     * @return 对象
     */
    fun save(friendLink: FriendLink): FriendLink

    /**
     * 删除一个友情链接
     * @param id 主键ID
     */
    fun delete(id: Long)


    fun findAll(pageModel: PageModel,friendLink: FriendLink?): Page<FriendLink>
}
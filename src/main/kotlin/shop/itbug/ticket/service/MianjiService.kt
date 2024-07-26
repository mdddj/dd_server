package shop.itbug.ticket.service

import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.entry.Mianji
import shop.itbug.ticket.entry.User

@Service
interface MianjiService {
    
    /**
     * 保存一个或者修改一个面基对象
     * @param mianji 保存的对象
     * @return  保存成功后的对象
     */
    fun save(mianji: Mianji): Mianji

    /**
     * 查询面基列表
     * @param pageModel 分页参数
     * @param selectOptions 查询条件,可以为空
     * @return 查询结果
     */
    fun list(pageModel: PageModel, selectOptions: Mianji?): Page<Mianji>


    /**
     * 查询用户发布的面基申请
     */
    fun findMianListWithUser(user: User, pageModel: PageModel) : Page<Mianji>

    fun findById(id: Long) : Mianji?


    fun deleteByUserAndId(id: Long,user: User)

}
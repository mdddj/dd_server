package shop.itbug.ticket.service

import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.entry.system.DTKDetail

@Service
interface DTKDetailService {
    /**
     * 保存或者修改
     *
     * @param dtkDetail 操作对象
     * @return 类对象
     */
    fun saveOne(dtkDetail: DTKDetail): DTKDetail


    /**
     * 启用 或者 停用
     *
     * @param action true 启用 , false 停用
     * @param id     主键
     * @return 是否设置成功
     */
    fun openAndCloseOne(action: Boolean, id: Long)

    /**
     * 根据id查找
     *
     * @param id 主键
     * @return 类对象
     */
    fun findById(id: Long): DTKDetail?

    /**
     * 查找默认设定的
     *
     * @return 类对象, null表示没有设置
     */
    fun findDefault(): DTKDetail?

    fun findBy(model: DTKDetail) : DTKDetail?


    fun findAllBy(model: DTKDetail?,pageModel: PageModel?) : Page<DTKDetail>


    /**
     * 验证大淘客账号是否可用
     */
    fun verifyDtkAccount(appKey: String,appSecret: String) : Boolean


    /**
     * 删除账号
     */
    fun deleteById(id: Long)
}
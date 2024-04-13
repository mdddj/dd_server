package shop.itbug.ticket.service.impl

import jakarta.annotation.Resource
import org.springframework.data.domain.Example
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.admin.model.getCurrentPage
import shop.itbug.ticket.dao.MianjiRepository
import shop.itbug.ticket.entry.Mianji
import shop.itbug.ticket.entry.User
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.service.MianjiService

@Service
class MianjiServiceImpl : MianjiService {
    @Resource
    private lateinit var mianjiRepository: MianjiRepository

    /**
     * 保存一个或者修改一个面基对象
     *
     * @param mianji 保存的对象
     * @return 保存成功后的对象
     */
    override fun save(mianji: Mianji): Mianji {
        return mianjiRepository.save(mianji)
    }

    /**
     * 查询面基列表
     * @param pageModel 分页参数
     * @param selectOptions 查询条件,可以为空
     * @return 查询结果
     */
    override fun list(pageModel: PageModel, selectOptions: Mianji?): Page<Mianji> {
        val of = PageRequest.of(pageModel.getCurrentPage(), pageModel.pageSize,Sort.Direction.DESC,"createDate")
        return if (selectOptions != null) {
            mianjiRepository.findAll(
                Example.of(selectOptions),
                of
            )
        } else mianjiRepository.findAll(of)
    }


    override fun findMianListWithUser(user: User, pageModel: PageModel): Page<Mianji> {
        val pageRequest = PageRequest.of(pageModel.getCurrentPage(),pageModel.pageSize,Sort.Direction.DESC,"createDate")
        return mianjiRepository.findAllByUser(user,pageRequest)
    }

    override fun findById(id: Long): Mianji? {
        return mianjiRepository.findByIdOrNull(id)
    }

    override fun deleteByUserAndId(id: Long, user: User) {
         val mianji = mianjiRepository.findByIdAndUser(id, user) ?: throw BizException("面基未找到或者已被删除")
        mianjiRepository.delete(mianji)
    }
}
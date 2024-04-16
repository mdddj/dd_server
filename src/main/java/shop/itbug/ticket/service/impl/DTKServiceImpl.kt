package shop.itbug.ticket.service.impl

import cn.hutool.http.HttpUtil
import com.alibaba.fastjson2.JSONObject
import jakarta.annotation.Resource
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.annotation.Caching
import org.springframework.data.domain.Example
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.admin.model.getPageable
import shop.itbug.ticket.dao.DTKDetailDao
import shop.itbug.ticket.entry.system.DTKDetail
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.exception.ResultDialogType
import shop.itbug.ticket.service.DTKDetailService
import shop.itbug.ticket.service.redis.RedisKeys
import shop.itbug.ticket.utils.MyDateUtil
import shop.itbug.ticket.utils.dtk.DtkSignUtil
import kotlin.jvm.optionals.getOrNull


/**
 * @author eee
 */
@Service
class DTKServiceImpl : DTKDetailService {

    @Resource
    private lateinit var dtkDetailDao: DTKDetailDao

    /**
     * 保存或者修改
     *
     * @param dtkDetail 操作对象
     * @return 类对象
     */
    override fun saveOne(dtkDetail: DTKDetail): DTKDetail {
        if (dtkDetail.id != null) {
            return dtkDetailDao.save(dtkDetail)
        }

        //新增
        val find = dtkDetailDao.findByAppKey(dtkDetail.appKey)
        if (find != null) {
            throw BizException("appKey已存在")
        }
        return dtkDetailDao.save(dtkDetail)
    }


    override fun deleteById(id: Long) {
        dtkDetailDao.deleteById(id)
    }

    /**
     * 启用 或者 停用
     *
     * @param action true 启用 , false 停用
     * @param id     主键
     * @return 是否设置成功
     */
    override fun openAndCloseOne(action: Boolean, id: Long) {
        //如果存在默认的启用账号,则需要取消,因为只能存在一个默认的账号
        if (action) {
            dtkDetailDao.findBySelectDefault(true)?.let {
                it.selectDefault = false
                dtkDetailDao.save(it)
            }
        }
        val dtkDetailResult = dtkDetailDao.findById(id).getOrNull() ?: throw BizException("未找到信息")
        dtkDetailResult.selectDefault = action
        dtkDetailDao.save(dtkDetailResult)

    }

    /**
     * 根据id查找
     *
     * @param id 主键
     * @return 类对象
     */
    override fun findById(id: Long): DTKDetail? {
        val byId = dtkDetailDao.findById(id)
        return byId.map { dtkDetail: DTKDetail? ->
            dtkDetail
        }.orElse(
            null
        )
    }

    /**
     * 查找默认设定的
     *
     * @return 类对象, null表示没有设置
     */
    override fun findDefault(): DTKDetail? {
        val allBySelect = dtkDetailDao.findAllBySelectDefault(true)
        return if (allBySelect.isEmpty()) {
            null
        } else allBySelect[0]
    }


    override fun findBy(model: DTKDetail): DTKDetail? {
        return dtkDetailDao.findOne(Example.of(model)).orElse(null)
    }

    override fun findAllBy(model: DTKDetail?, pageModel: PageModel?): Page<DTKDetail> {
        if (model != null && pageModel != null) {
            val pageable = pageModel.getPageable()
            return dtkDetailDao.findAll(Example.of(model), pageable)
        }
        return dtkDetailDao.findAll(Pageable.unpaged())
    }


    override fun verifyDtkAccount(appKey: String, appSecret: String): Boolean {
        val host = "https://openapi.dataoke.com/api/tb-service/get-order-details"
        val initParams = DtkSignUtil.getInitParamsMap(appKey, appSecret)
        val time = MyDateUtil.calcOrderQueryTime(-10)
        initParams["version"] = "v1.0.0"
        initParams["queryType"] = '1'
        initParams["startTime"] = time.startTime
        initParams["endTime"] = time.endTime
        return try {
            val response = HttpUtil.get(host, initParams)
            val json = JSONObject.parse(response)
            val r = json.getIntValue("code", -1) == 0
            if (r.not()) {
                throw BizException("验证大淘客账号失败,请检查输入是否正确", ResultDialogType.Dialog)
            }
            true
        } catch (e: Exception) {
            throw BizException("验证大淘客账号失败,请检查输入是否正确", ResultDialogType.Dialog)
        }
    }


}

///
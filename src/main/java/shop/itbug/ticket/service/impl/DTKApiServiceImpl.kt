package shop.itbug.ticket.service.impl

import com.alibaba.fastjson2.JSONObject
import jakarta.annotation.Resource
import org.apache.commons.lang3.StringUtils
import org.springframework.stereotype.Service
import shop.itbug.ticket.entry.DTKResultJson
import shop.itbug.ticket.entry.getMsg
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.service.DTKApiService
import shop.itbug.ticket.service.DTKDetailService
import shop.itbug.ticket.utils.Result
import shop.itbug.ticket.utils.dtk.DtkSignUtil
import shop.itbug.ticket.utils.dtk.HttpUtils
import shop.itbug.ticket.utils.successResult

@Service
class DTKApiServiceImpl : DTKApiService {
    @Resource
    private lateinit var dtkDetailService: DTKDetailService

    override fun request(apiUrl: String, params: MutableMap<String, Any>, apiVersion: String): String {
        val dtkAccount = dtkDetailService.findDefault() ?: throw BizException("请先配置默认启用的大淘客账号")
        val appKey = dtkAccount.appKey ?: throw BizException("appKey不存在")
        val appSecret = dtkAccount.appSecret ?: throw BizException("appSecret不存在")
        val initParams = DtkSignUtil.getInitParamsMap(appKey,appSecret)
        initParams["version"] = apiVersion
        initParams.putAll(params)
        try {
            return HttpUtils.sendGet(apiUrl, initParams)
        } catch (e: Exception) {
            throw BizException("系统繁忙,请稍后重试")
        }
    }



    override fun getDTKResultJsonObject(response: String?): DTKResultJson? {
        return if (StringUtils.isNotBlank(response)) {
            JSONObject.parseObject(response, DTKResultJson::class.java)
        } else null
    }

    /**
     * 判断大淘客返回的数据是否正确
     *
     * @param dtkResultJson 类对象
     * @return true 表示成功
     */
    override fun resultDataIsSuccess(dtkResultJson: DTKResultJson?): Boolean {
        if (dtkResultJson != null) {
            return dtkResultJson.code == 0
        }
        return false
    }

    /**
     * 获取大淘客返回数据的消息
     *
     * @param dtkResultJson 类对象
     * @return 消息
     */
    override fun getResultDataMessage(dtkResultJson: DTKResultJson): String {
        return dtkResultJson.msg ?: ""
    }

    override fun exception(dtkResultJson: DTKResultJson?): BizException {
        return BizException(dtkResultJson!!.code!!, dtkResultJson.getMsg())
    }

    override fun handleResponse(response: String): Result<String> {
        val dtkResultJsonObject = getDTKResultJsonObject(response)
        if (resultDataIsSuccess(dtkResultJsonObject)) {
            if (dtkResultJsonObject != null) {
                return dtkResultJsonObject.data?.successResult() ?: throw BizException("获取数据失败")
            }
        }
        println(response)
        throw exception(dtkResultJsonObject)
    }

    /**
     * 判断请求是否成功
     *
     * @param jsonResponse 大淘客返回的字符串
     * @param key          成功标识key
     * @param value        成功标识value
     * @return 是否请求成功
     */
    override fun isRequestSuccess(jsonResponse: String?, key: String?, value: Int): Boolean {
        try {
            val `object` = JSONObject.parseObject(jsonResponse)
            val keyVlaue = `object`.getInteger(key)
            if (keyVlaue != null && keyVlaue == value) {
                return true
            }
        } catch (e: Exception) {
            return false
        }
        return false
    }

    /**
     * 获取数据字符串
     *
     * @param jsonResponse 同上
     * @param key          同上
     * @param value        同上
     * @return json 字符串 data
     */
    override fun getDataJsonString(jsonResponse: String?, key: String?, value: Int): String? {
        if (isRequestSuccess(jsonResponse, key, value)) {
            val `object` = JSONObject.parseObject(jsonResponse)
            return `object`.getString("data")
        }
        throw exception(getDTKResultJsonObject(jsonResponse))
    }
}
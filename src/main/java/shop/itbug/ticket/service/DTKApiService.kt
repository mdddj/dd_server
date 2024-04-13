package shop.itbug.ticket.service

import org.springframework.stereotype.Service
import shop.itbug.ticket.entry.DTKResultJson
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.utils.Result

@Service
interface DTKApiService {

    fun request(apiUrl: String, params: MutableMap<String, Any>, apiVersion: String = "v1.0.0"): String

    /**
     * 把大淘客返回的数据转成类对象
     *
     * @param response json 字符串
     * @return 类对象
     */
    fun getDTKResultJsonObject(response: String?): DTKResultJson?

    /**
     * 判断大淘客返回的数据是否正确
     *
     * @param dtkResultJson 类对象
     * @return true 表示成功
     */
    fun resultDataIsSuccess(dtkResultJson: DTKResultJson?): Boolean

    /**
     * 获取大淘客返回数据的消息
     *
     * @param dtkResultJson 类对象
     * @return 消息
     */
    fun getResultDataMessage(dtkResultJson: DTKResultJson): String
    fun exception(dtkResultJson: DTKResultJson?): BizException
    fun handleResponse(response: String): Result<String>
    fun isRequestSuccess(jsonResponse: String?, key: String?, value: Int): Boolean
    fun getDataJsonString(jsonResponse: String?, key: String?, value: Int): String?
}
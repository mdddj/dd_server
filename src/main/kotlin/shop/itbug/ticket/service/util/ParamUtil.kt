package shop.itbug.ticket.service.util

import org.apache.commons.lang3.StringUtils
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.exception.CommonEnum

/// 参数验证
object ParamUtil {
    fun verifyParam(vararg paramValue: String?) {
        for (param in paramValue) {
            if (StringUtils.isBlank(param)) {
                throw BizException(CommonEnum.PARAMS_ERROR)
            }
        }
    }

    fun hasParam(key: String, value: String?, map: MutableMap<String, Any?>): MutableMap<String, Any?> {
        if (StringUtils.isNotBlank(value)) {
            map[key] = value!!
            return map
        }
        return map
    }
}
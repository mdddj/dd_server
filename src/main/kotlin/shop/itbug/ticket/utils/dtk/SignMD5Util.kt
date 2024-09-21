package shop.itbug.ticket.utils.dtk

import cn.hutool.crypto.digest.DigestUtil
import java.util.*
import kotlin.random.Random

/**
 * @Title: SignMD5Util
 * @Description: 签名相关工具类
 * @Version V1.0
 */
object DtkSignUtil {



    data class SignModel(val timer: String,val nonce: String,val sign: String)


    /**
     * 大淘客验证
     */
    private fun getSign(appKey: String,appSecret:String) : SignModel {
        val timer = "${System.currentTimeMillis()}"
        val nonce = "${Random(System.currentTimeMillis()).nextInt(100000,999999)}"
        val str = "appKey=$appKey&timer=$timer&nonce=$nonce&key=$appSecret"
        val sign = DigestUtil.md5Hex(str).uppercase(Locale.getDefault())
        return SignModel(timer, nonce, sign)
    }

    /**
     * 获取大淘客请求参数
     */
    fun getInitParamsMap(appKey: String,appSecret:String): MutableMap<String,Any> {
        val signInfo = getSign(appKey, appSecret)
        return mutableMapOf(
            "signRan" to signInfo.sign,
            "timer" to signInfo.timer,
            "nonce" to signInfo.nonce,
            "appKey" to appKey
        )
    }
}
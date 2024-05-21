package shop.itbug.ticket.utils

import com.alibaba.fastjson2.JSONObject
import com.alibaba.fastjson2.JSONWriter
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.http.HttpStatus
import org.springframework.validation.BindingResult
import org.springframework.validation.ObjectError
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.exception.CommonEnum
import shop.itbug.ticket.exception.ResultDialogType
import java.io.Serializable

/**
 * 通用返回类
 *
 * @author ldd
 * @param <T> 返回的参数类型
</T> */

typealias ResultJSON<T> = Result<T>
typealias R<T> = Result<T>

/**
 * 没有权限的异常转换成Result Json对象
 */
fun Exception?.noAuthenticationResult(): Result<String> {
    if (this == null) {
        return Result(CommonEnum.NO_AUTHORIZATION)
    }
    return Result(CommonEnum.NO_AUTHORIZATION)
}

fun <T> T.successResult(message: String = "操作成功"): ResultJSON<T> {
    val r = ResultJSON.ok(this, message)
    if(message == "操作成功"){
        r.type = ResultDialogType.None
    }
     return r
}

/**
 * 转成json
 */
fun <T> T.toJson(): String {
    return JSONObject.toJSONString(this, JSONWriter.Feature.PrettyFormat)
}

fun BindingResult.verify() {
    if (this.hasErrors()) {
        throw BizException(ResultJSON.paramValidError(this))
    }
}

fun <T> Result<T>.changeDialogType(type: ResultDialogType): Result<T> {
    this.type = type
    return this
}

/**
 * 设置重定向的URL
 */
fun <T> Result<T>.setRedirectUrl(url: String) : Result<T> {
    this.redirectUrl = url
    return this
}



//@Schema(name = "通用返回结构")
class Result<T> : Serializable {

    @Schema(description = "状态码")
    var state: Int

    @Schema(description = "消息提示")
    var message: String? = ""

    @Schema(description = "携带数据")
    var data: T? = null

    @Schema(description = "通知类型:Dialog:弹窗,Toast:吐司,Notice:浮层,None:不展示消息,FinalToast:成功或者失败都展示吐司,FinalDialog: 成功或者失败都展示消息弹窗")
    var type: ResultDialogType = ResultDialogType.None


    @get:Schema(description = "true: 达到预期操作,false: 操作失败,错误信息查看message字段")
    val success get() = state == HttpStatus.OK.value()


    @Schema(description = "重定向URL,不为null需要跳转到这个链接", example = "/error?message=token失效&reDirectTo=/orderList")
    var redirectUrl: String? = null

    constructor() {
        state = HttpStatus.OK.value()
    }

    /**
     * 为了方便，重载n个构造器
     * @param state 状态码
     * @param message 消息提示
     * @param data 数据
     */
    @JvmOverloads
    constructor(
        state: Int,
        message: String? = "",
        data: T? = null,
        type: ResultDialogType = ResultDialogType.Toast
    ) : super() {
        this.state = state
        this.message = message
        this.data = data
        this.type = type
    }

    constructor(commonEnum: CommonEnum, type: ResultDialogType = ResultDialogType.Toast) : super() {
        message = commonEnum.errMessage()
        state = commonEnum.errCode()
        data = null
        this.type = type
    }

    constructor(commonEnum: CommonEnum, data: T, type: ResultDialogType = ResultDialogType.Toast) {
        message = commonEnum.errMessage()
        state = commonEnum.errCode()
        this.data = data
        this.type = type
    }

    constructor(state: Int, data: T) : this(state, "成功", data)
    constructor(error: String?) : this(HttpStatus.INTERNAL_SERVER_ERROR.value(), error, null)
    constructor(data: T) : this(HttpStatus.OK.value(), "", data)
    constructor(data: T, message: String?) : this(HttpStatus.OK.value(), message, data)
    constructor(e: Throwable) : this(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.message, null)


    override fun toString(): String {
        return "JsonResult [state=" + state + ", message=" + message + "data=" + data + "]"
    }

    companion object {
        const val OTHER = 201
        fun <T> ok(data: T?, msg: String = "操作成功"): Result<T> {
            val r = Result(HttpStatus.OK.value(), msg, data)
            if(msg == "操作成功"){
                r.type = ResultDialogType.None
            }
            return r
        }

        fun <T> successMessage(message: String): Result<T> {
            return Result(HttpStatus.OK.value(), message)
        }

        fun err(message: String): Result<String?> {
            return Result(OTHER, message, null)
        }

        fun <T> err(message: String, data: T): Result<T> {
            return Result(OTHER, message, data)
        }

        fun noAuth(): Result<String> {
            return Result(CommonEnum.NO_AUTHORIZATION)
        }

        /**
         * 参数验证失败
         * @param bindingResult 控制器方法验证结果
         * @return 返回前端的验证信息
         */
        fun paramValidError(bindingResult: BindingResult): Result<List<ObjectError>> {
            return Result(CommonEnum.PARAMS_ERROR, bindingResult.allErrors)
        }

    }
}
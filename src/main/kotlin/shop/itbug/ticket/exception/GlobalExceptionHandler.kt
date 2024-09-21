package shop.itbug.ticket.exception

import cn.hutool.json.JSONException
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import jakarta.validation.ConstraintViolation
import jakarta.validation.ConstraintViolationException
import kotlinx.serialization.SerializationException
import org.springframework.security.authentication.InsufficientAuthenticationException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.NoHandlerFoundException
import shop.itbug.ticket.utils.R
import shop.itbug.ticket.utils.Result
import java.net.BindException
import java.util.function.Consumer


/**
 * 全局异常统一处理
 * @author ldd
 */
@ControllerAdvice
class GlobalExceptionHandler {

    /**
     * 处理自定义的业务异常
     *
     * @param e 自定义异常
     * @return json
     */
    @ExceptionHandler(value = [BizException::class])
    @ResponseBody
    fun bizExceptionHandler(e: BizException): Result<Any> {
        return Result(e.errCode, e.errMessage, data = e.data, type = e.type)
    }

    @ExceptionHandler(value = [NoHandlerFoundException::class])
    @ResponseBody
    fun noHandleFoundException(e:NoHandlerFoundException): R<Any> {
        return R(CommonEnum.NOT_FOUND)
    }

    @ExceptionHandler(value = [BindException::class])
    @ResponseBody
    fun handleBindException(
        ex: BindException?, request: HttpServletRequest?, response: HttpServletResponse, handler: Any?
    ): Result<Any> {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST)
        return Result<Any>(CommonEnum.BODY_NOT_MATCH).apply {
            data = ex
        }
    }

    /**
     * 处理自定义的业务异常
     *
     * @param e 自定义异常
     * @return json
     */
    @ExceptionHandler(value = [IllegalArgumentException::class])
    @ResponseBody
    fun illegalArgumentExceptionHandle(e: IllegalArgumentException): Result<Any> {
        e.printStackTrace()
        return Result(500, e.message, e.localizedMessage)
    }

    @ExceptionHandler(value = [ServletException::class])
    @ResponseBody
    fun servletExceptionHandle(exception: ServletException): Result<Any?> {
        exception.printStackTrace()
        return Result(500, exception.message, exception.localizedMessage)
    }

    @ExceptionHandler(value = [NullPointerException::class])
    @ResponseBody
    fun nullPointerExceptionHandle(exception: NullPointerException): Result<Any> {
        exception.printStackTrace()
        return Result(500, exception.message, exception.localizedMessage)
    }


    /**
     * 参数验证失败异常统一处理
     * @param exception 参数验证失败
     * @return 异常信息
     */
    @ExceptionHandler(value = [ConstraintViolationException::class])
    @ResponseBody
    fun constraintViolationException(exception: ConstraintViolationException): Result<Any> {
        exception.printStackTrace()
        val errors = ArrayList<Any>()
        exception.constraintViolations.forEach(Consumer { constraintViolation: ConstraintViolation<*> ->
            errors.add(
                constraintViolation.messageTemplate
            )
        })
        return Result(CommonEnum.PARAMS_VIOLATION_EXCEPTION, errors)
    }

    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    @ResponseBody
    fun methodArgumentNotValidExceptionHandle(excetion: MethodArgumentNotValidException): Result<*> {
        return Result(30001, "参数验证失败", excetion.allErrors)
    }

    @ExceptionHandler(value = [JSONException::class])
    @ResponseBody
    fun jsonException(exception: JSONException): Result<JSONException> {
        return Result(30002, "数据解析错误", exception)
    }

    @ExceptionHandler(value = [SerializationException::class])
    @ResponseBody
    fun jsonDecodingException(exception: SerializationException): Result<SerializationException> {
        return Result(30002, "数据解析错误", exception)
    }

    @ExceptionHandler(value = [InsufficientAuthenticationException::class])
    @ResponseBody
    fun insufficientAuthenticationExceptionHandle(e: InsufficientAuthenticationException): Result<InsufficientAuthenticationException> {
        return Result(CommonEnum.NO_AUTHORIZATION_INVALID, e)
    }
}
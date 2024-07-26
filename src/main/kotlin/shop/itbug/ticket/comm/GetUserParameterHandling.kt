package shop.itbug.ticket.comm

import org.springframework.core.MethodParameter
import org.springframework.lang.Nullable
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import shop.itbug.ticket.annotation.GetLoginUser
import shop.itbug.ticket.entry.User

/**
 * 快捷获取登录用户对象
 */
@Service
class GetUserParameterHandling : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        val hasAnnotation = parameter.hasParameterAnnotation(GetLoginUser::class.java)
        val isUserObject = parameter.parameterType.isAssignableFrom(User::class.java)
        return hasAnnotation && isUserObject
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        @Nullable mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        @Nullable binderFactory: WebDataBinderFactory?
    ): Any {
        return SecurityContextHolder.getContext().authentication.principal as User
    }
}
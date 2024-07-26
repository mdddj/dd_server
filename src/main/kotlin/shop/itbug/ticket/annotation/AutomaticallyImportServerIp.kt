package shop.itbug.ticket.annotation

import jakarta.servlet.http.HttpServletRequest
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Service
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import shop.itbug.ticket.controller.getCurrentHost

@Service
class AutomaticallyImportServerIp : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        val hasAnno = parameter.hasParameterAnnotation(ServiceHost::class.java)
        val isString = parameter.parameterType.isAssignableFrom(String::class.java)
        return hasAnno && isString
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {
        val nativeRequest = webRequest.getNativeRequest(HttpServletRequest::class.java)
        if (nativeRequest != null) {
            return nativeRequest.getCurrentHost()
        }
        return ""
    }
}
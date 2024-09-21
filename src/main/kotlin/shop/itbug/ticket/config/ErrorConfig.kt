package shop.itbug.ticket.config

import org.springframework.boot.web.server.ErrorPage
import org.springframework.boot.web.server.ErrorPageRegistrar
import org.springframework.boot.web.server.ErrorPageRegistry
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus

/**
 * 注册默认错误页面
 */
@Configuration
class ErrorConfig : ErrorPageRegistrar {
    override fun registerErrorPages(registry: ErrorPageRegistry) {
        val errorPages = arrayOfNulls<ErrorPage>(1)
        errorPages[0] = ErrorPage(HttpStatus.NOT_FOUND, "/")
        registry.addErrorPages(*errorPages)
    }
}
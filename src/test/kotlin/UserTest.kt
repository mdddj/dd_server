
import jakarta.annotation.Resource
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import shop.itbug.ticket.App
import shop.itbug.ticket.model.params.LoginParam
import shop.itbug.ticket.service.UserService
import shop.itbug.ticket.utils.printToConsole

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = [App::class])
@AutoConfigureMockMvc
@ExtendWith(SpringExtension::class)
open class UserTest {
    @Resource private lateinit var userService: UserService

    /// 
    @Test
    open fun testLogin(){
        val loginSuccessResultModel = userService.login(LoginParam.new("admin", "123456"))
        loginSuccessResultModel.token.printToConsole()
    }
}
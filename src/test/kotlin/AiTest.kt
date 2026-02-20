import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import shop.itbug.ticket.App
import shop.itbug.ticket.service.AiService

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = [App::class])
@ExtendWith(SpringExtension::class)
class AiTest {
    @Autowired
    private lateinit var aiService: AiService


    @Test
    fun testGenerate(){
        runBlocking {
            val result = aiService.generate("你好,你能帮我赶时髦? ")
            println(result)
        }
    }
}
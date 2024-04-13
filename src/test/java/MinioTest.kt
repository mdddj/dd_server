
import jakarta.annotation.Resource
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import shop.itbug.ticket.App
import shop.itbug.ticket.service.MinioService

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = [App::class])
@AutoConfigureMockMvc
class MinioTest {

    @Resource
    private lateinit var minioService: MinioService


    @Test
    fun getDetail(){
        val configModel = minioService.getConfigModel()
    }
}

import jakarta.annotation.Resource
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import shop.itbug.ticket.App
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.service.blog.TextService

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = [App::class])
@AutoConfigureMockMvc
class TextServiceTest {


    @Resource
    private lateinit var textService: TextService

    @Test
    fun testSelectAll(){
        val selectAll = textService.selectAll(PageModel(page = 1, pageSize = 20), null)
        assert(selectAll.isEmpty)
    }

}
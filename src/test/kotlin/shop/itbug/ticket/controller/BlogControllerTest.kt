package shop.itbug.ticket.controller

import jakarta.annotation.Resource
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import shop.itbug.ticket.App
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.service.MyResourceService

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = [App::class])
@AutoConfigureMockMvc
@ExtendWith(SpringExtension::class)
class BlogControllerTest {
    @Resource private lateinit var myResourceService: MyResourceService
    @Test
    fun findAllResource() {
        val result = myResourceService.findAll(PageModel(1,20),null)
        assert(!result.isEmpty)
    }

}
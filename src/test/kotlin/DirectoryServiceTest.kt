import org.junit.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import shop.itbug.ticket.App
import shop.itbug.ticket.entry.directory.DirectoryServiceImpl

/**
 * @Author: 梁典典
 * @Date: 2024/09/28/上午10:24
 * @Description:
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = [App::class])
@AutoConfigureMockMvc
@ExtendWith(SpringExtension::class)
class DirectoryServiceTest {


    @Autowired
    lateinit var directoryServiceImpl: DirectoryServiceImpl

    @Test
    fun testDelete(){
        directoryServiceImpl.deleteByName(".ddblog")
    }
}
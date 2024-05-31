import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import shop.itbug.ticket.App
import shop.itbug.ticket.entry.storage.StorageServiceImpl

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = [App::class])
@AutoConfigureMockMvc
@ExtendWith(SpringExtension::class)
class UploadFileTest {
    @Autowired private lateinit var storageServiceImpl: StorageServiceImpl

    /**
     * 测试上传文件
     */
    @Test
    fun testUploadFile(){
    }
}
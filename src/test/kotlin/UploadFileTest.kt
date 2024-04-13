import com.alibaba.fastjson2.toJSONString
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.mock.web.MockMultipartFile
import org.springframework.test.context.junit.jupiter.SpringExtension
import shop.itbug.ticket.App
import shop.itbug.ticket.entry.storage.StorageServiceImpl
import shop.itbug.ticket.ex.log
import java.io.File
import java.io.FileInputStream

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
        val file = File("/Users/ldd/Pictures/02.png")
        val mockFile = MockMultipartFile("test file",file.name,"image/png", FileInputStream(file))
        val linkUrl = storageServiceImpl.getLinkUrl(mockFile, "admin", "http://localhost:8082", null)
        log().info(linkUrl?.toJSONString())
    }
}
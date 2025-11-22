package shop.itbug.ticket.api.publicapi


/*
* 图片代理,防止图片防盗链
* */
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/api/public/img-proxy")
@Tag(name = "图片代理")
class ImageProxyController {

    @GetMapping
    @Operation(summary = "图片代理")
    fun proxy(@RequestParam("url") imgurl: String, response: HttpServletResponse) {
        try {
            val url = URI.create(imgurl).toURL()
            val connection = url.openConnection()
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
            connection.connect()
            response.contentType = connection.contentType
            connection.getInputStream().use { input ->
                response.outputStream.use { output ->
                    input.copyTo(output)
                }
            }
        } catch (e: Exception) {
            response.status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR
            response.writer.write("Error proxying image: ${e.message}")
        }
    }
}
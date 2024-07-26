package shop.itbug.ticket.entry.report

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.annotation.GetLoginUser
import shop.itbug.ticket.annotation.ServiceHost
import shop.itbug.ticket.entry.FileInfoSaveConfig
import shop.itbug.ticket.entry.User
import shop.itbug.ticket.entry.storage.StorageServiceImpl
import shop.itbug.ticket.service.FileInfoService
import shop.itbug.ticket.utils.ResultJSON
import shop.itbug.ticket.utils.successResult

@RestController
@RequestMapping("/api/report")
@Tag(name = "举报控制器")
class ReportController {
    @Resource
    private lateinit var reportService: ReportService

    @Resource
    private lateinit var fileInfoService: FileInfoService

    data class AddReportParam(val content: String, val type: String, val images: List<MultipartFile>)

    @PostMapping("/save")
    @Operation(summary = "添加一条举报记录")
    fun addReport(@GetLoginUser user: User, params: AddReportParam, @ServiceHost host: String): ResultJSON<Report> {
        val config = FileInfoSaveConfig(user,host,"举报图片")
        val imageFiles = fileInfoService.saveAllFiles(params.images, config)
        val obj = Report().apply {
            this.images = imageFiles
            this.content = params.content
            this.type = params.type
        }
        val saved = reportService.save(obj)
        return saved.successResult()
    }



    @Operation(summary = "查找用户举报的内容")
    @GetMapping("/find-by-page")
    fun findByUser(pageModel: PageModel, @GetLoginUser user: User) : ResultJSON<Page<Report>> {
        return reportService.findAllByUser(pageModel,user).successResult()
    }

}
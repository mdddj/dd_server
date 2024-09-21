package shop.itbug.ticket.admin.controller.minio

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.web.bind.annotation.*
import shop.itbug.ticket.exception.ResultDialogType
import shop.itbug.ticket.service.MinioService
import shop.itbug.ticket.utils.R
import shop.itbug.ticket.utils.changeDialogType
import shop.itbug.ticket.utils.minio.MinioDetailModel
import shop.itbug.ticket.utils.successResult

@RestController
@RequestMapping("/admin/minio")
@Tag(name = "minio配置")
class MinioController {


    @Resource
    private lateinit var minioService: MinioService


    @GetMapping("/get")
    @Operation(summary = "获取 minio 配置")
    fun getMinioConfig(): R<MinioDetailModel> {
        return minioService.getConfigModel().successResult("获取成功").changeDialogType(ResultDialogType.None)
    }


    @PostMapping("/update")
    @Operation(summary = "修改minio配置")
    fun updateMinioConfig(@RequestBody model: MinioDetailModel) : R<MinioDetailModel>{
        minioService.updateConfig(model)
        return minioService.getConfigModel().successResult("修改成功")
    }
}
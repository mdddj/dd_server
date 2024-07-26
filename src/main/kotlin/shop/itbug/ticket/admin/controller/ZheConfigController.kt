package shop.itbug.ticket.admin.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.web.bind.annotation.*
import shop.itbug.ticket.exception.ResultDialogType
import shop.itbug.ticket.model.config.ZheConfigModel
import shop.itbug.ticket.service.config.ZheConfigService
import shop.itbug.ticket.utils.R
import shop.itbug.ticket.utils.changeDialogType
import shop.itbug.ticket.utils.successResult

@Tag(name = "折淘客管理")
@RestController
@RequestMapping("/api/admin/zhe")
class ZheConfigController {

    @Resource
    lateinit var zheConfigService: ZheConfigService

    @GetMapping("/get")
    @Operation(summary = "获取折淘客配置")
    fun getConfig() : R<ZheConfigModel> {
        return zheConfigService.setting.successResult().changeDialogType(ResultDialogType.None)
    }

    @PostMapping("/update")
    @Operation(summary = "修改配置")
    fun update(@RequestBody params: ZheConfigModel) : R<ZheConfigModel> {
        return zheConfigService.updateSetting(params).successResult()
    }
}
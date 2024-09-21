package shop.itbug.ticket.admin.controller.miniapp

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import shop.itbug.ticket.service.config.MiniAppConfig
import shop.itbug.ticket.service.config.MiniAppConfigModel
import shop.itbug.ticket.utils.R
import shop.itbug.ticket.utils.successResult

@RestController
@RequestMapping("/admin/miniapp/config")
@Tag(name = "博客小程序配置控制器")
class MiniAppConfigController {

    @Resource
    private lateinit var miniAppConfig: MiniAppConfig

    @GetMapping("/get")
    @Operation(summary = "获取配置")
    fun miniAppConfig(): R<MiniAppConfigModel> = miniAppConfig.setting.successResult()

    @PostMapping("/update")
    @Operation(summary = "修改配置")
    fun updateConfig(@RequestBody model: MiniAppConfigModel): R<MiniAppConfigModel> {
        return miniAppConfig.updateSetting(model).successResult("修改成功")
    }

}
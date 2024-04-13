package shop.itbug.ticket.admin.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.exception.ResultDialogType
import shop.itbug.ticket.service.config.MailAccountConfig
import shop.itbug.ticket.service.config.MyMailAccount
import shop.itbug.ticket.service.config.createMail
import shop.itbug.ticket.utils.R
import shop.itbug.ticket.utils.changeDialogType
import shop.itbug.ticket.utils.successResult

@RestController
@RequestMapping("/api/admin/mail")
@Tag(name = "邮件通知配置")
@PreAuthorize("hasRole('admin') or hasRole('email')")
class MailConfigController {

    @Resource
    lateinit var mailAccountConfig: MailAccountConfig


    @PostMapping("/update")
    @Operation(summary = "修改配置")
    fun save(@RequestBody myMailAccount: MyMailAccount): R<MyMailAccount> {
        return mailAccountConfig.updateSetting(myMailAccount).successResult("保存成功")
    }

    @GetMapping("/get")
    @Operation(summary = "获取配置")
    fun get(): R<MyMailAccount> = mailAccountConfig.setting.successResult("获取成功")

    @Schema(name = "邮件测试参数")
    data class TestEmail(
        @Schema(description = "收件人邮箱") val to: String,
        @Schema(description = "标题") val title: String,
        @Schema(description = "正文内容") val content: String,
        @Schema(description = "是否为HTML") val isHtml: Boolean
    )

    @PostMapping("/test")
    @Operation(summary = "发送邮件测试")
    fun sendTestEmail(@RequestBody testEmail: TestEmail): R<TestEmail> {
        try {
            val msgId = mailAccountConfig.setting.createMail().setTos(testEmail.to).setTitle(testEmail.title)
                .setContent(testEmail.content).setHtml(testEmail.isHtml).send()
            return testEmail.successResult("发送成功,id=$msgId").changeDialogType(ResultDialogType.FinalToast)
        } catch (e: Exception) {
            throw BizException(e.localizedMessage, ResultDialogType.Dialog)
        }
    }
}
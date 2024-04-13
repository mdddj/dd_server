package shop.itbug.ticket.admin.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import shop.itbug.ticket.entry.Version
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.exception.ResultDialogType
import shop.itbug.ticket.model.params.IdBody
import shop.itbug.ticket.service.VersionService
import shop.itbug.ticket.utils.*
import java.time.ZoneId
import java.time.ZonedDateTime


@Tag(name = "版本管理(管理员)")
@RestController
@RequestMapping("/api/admin/version")
class VersionAdminController {

    @Resource private lateinit var versionService: VersionService


    @GetMapping("/get")
    @Operation(summary = "根据ID查找对象")
    fun findById(id: Long): R<Version> = versionService.findById(id).successResult("获取成功")



    @PostMapping("/save")
    @Operation(summary = "新建一个版本")
    fun save(
        @RequestBody @Parameter(name = "版本信息") version: Version,
        @Parameter(hidden = true) bindingResult: BindingResult
    ): Result<Version> {
        try {
            bindingResult.verify()
            version.createDate = ZonedDateTime.now(ZoneId.systemDefault())
            return versionService.save(version).successResult("保存成功").changeDialogType(ResultDialogType.FinalDialog)
        } catch (e: Exception) {
            e.printStackTrace()
            throw BizException("添加失败:$e", ResultDialogType.Dialog)
        }
    }


    @DeleteMapping("/delete")
    @Operation(summary = "删除一个版本")
    fun delete(@Parameter(name = "主键ID") idKey: IdBody): Result<Boolean> {
        try {
            versionService.delete(idKey.id)
        } catch (e: Exception) {
            throw BizException("删除失败")
        }
        return true.successResult("删除成功").changeDialogType(ResultDialogType.FinalToast)
    }

}
package shop.itbug.ticket.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.entry.system.DTKDetail
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.service.DTKDetailService
import shop.itbug.ticket.utils.R
import shop.itbug.ticket.utils.Result
import shop.itbug.ticket.utils.successResult

@RestController
@RequestMapping("/api/v1/dtk/detail")
@Tag(name = "大淘客配置接口")
class DTKDetailController {
    @Resource
    private lateinit var dtkDetailService: DTKDetailService


    @PostMapping("/add")
    @Operation(summary = "添加大淘客账号")
    fun add(@RequestBody dtkDetail: DTKDetail): Result<DTKDetail> {
        try{
            return dtkDetailService.saveOne(dtkDetail).successResult("保存成功")
        }catch (e:Exception){
            throw BizException(e.localizedMessage)
        }
    }

    @GetMapping("/set-default")
    @Operation(summary = "获取默认配置账号")
    fun findDefault(): Result<DTKDetail> {
        return dtkDetailService.findDefault()?.successResult("获取默认配置账号成功") ?: throw BizException("没有配置默认的账号")
    }

    @Schema(name="设置默认账号的参数")
    data class ChangeDefaultAccountParam(@Schema(description = "true: 启用, false: 取消启用") val action: Boolean, @Schema(description = "ID")  val id: Long)


    ///
    @PostMapping("/update-default")
    @Operation(summary = "设置默认的账号")
    fun changeDefaultAccount(@RequestBody params: ChangeDefaultAccountParam) : R<Boolean>{
        dtkDetailService.openAndCloseOne(params.action,params.id)
        return R.ok(true)
    }

    @GetMapping("/all")
    @Operation(summary = "获取全部账号")
    fun getAll(filter: DTKDetail?,pageModel: PageModel?): R<Page<DTKDetail>>{
        return dtkDetailService.findAllBy(filter,pageModel).successResult("获取全部成功")
    }

    @Schema(name = "验证大淘客账号是否可用参数")
    data class VerifyDtkAccountParam( @Schema(description = "大淘客appKey") val appKey: String, @Schema(description = "大淘客appSecret")  val appSecret: String)

    @PostMapping("/verify")
    @Operation(summary = "验证大淘客账号是否可用")
    fun verifyDtkAccount(@RequestBody params : VerifyDtkAccountParam) : R<Boolean> {
        return dtkDetailService.verifyDtkAccount(params.appKey,params.appSecret).successResult("有效的大淘客账号")
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除大淘客账号")
    fun deleteById(id: Long) : R<Boolean> {
        try {
            dtkDetailService.deleteById(id)
            return R.ok(true)
        }catch (e:Exception){
            throw BizException(e.localizedMessage)
        }
    }
}
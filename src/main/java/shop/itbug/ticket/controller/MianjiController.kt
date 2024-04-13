package shop.itbug.ticket.controller

import com.alibaba.fastjson2.toJSONString
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.constraints.NotNull
import org.springframework.data.domain.Page
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.annotation.GetLoginUser
import shop.itbug.ticket.entry.Mianji
import shop.itbug.ticket.entry.User
import shop.itbug.ticket.entry.storage.StorageServiceImpl
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.service.MianjiService
import shop.itbug.ticket.utils.Result
import shop.itbug.ticket.utils.ResultJSON
import shop.itbug.ticket.utils.verify

/**
 * 面基相关接口
 */
@RestController
@RequestMapping("/api/mianji")
@Tag(name = "面基接口")
class MianjiController {

    @Resource
    private lateinit var  mainjiService: MianjiService

    @Resource private lateinit var storageServiceImpl: StorageServiceImpl
    /**
     * 获取面基列表
     * @param pageModel 分页
     */
    @GetMapping("/list")
    @Operation(summary = "面基列表")
    fun list( @NotNull @Validated pageModel: PageModel, @Parameter(hidden = true)  bindingResult: BindingResult): Result<Page<Mianji>> {
        bindingResult.verify()
        val list = mainjiService.list(pageModel, null)
        return Result.ok(list)
    }

    /**
     * 添加一条面基申请记录
     */
    @PostMapping("/add")
    @Operation(summary = "新增一条面基申请")
    fun save(@Validated model: Mianji, @RequestPart(name = "files") files : List<MultipartFile>, @Parameter(hidden = true)  bindingResult: BindingResult,@GetLoginUser @Parameter(hidden = true) user: User,
             httpServletRequest: HttpServletRequest): ResultJSON<Mianji> {
        println("文件列表:${files.size}")
        bindingResult.verify()
        model.user = user

        if(files.isNotEmpty()){
            model.images = storageServiceImpl.saveAllFiles(files,"",
                httpServletRequest.getCurrentHost(),user)
        }

        println(model.images.toJSONString())

        val save = mainjiService.save(model)
        return Result.ok(save,"提交成功.")
    }


    @GetMapping("/find")
    @Operation(summary = "根据 id 查找面基数据")
    fun findById(id: Long): ResultJSON<Mianji>{
        val result =  mainjiService.findById(id) ?: throw BizException("面基未找到或者已删除")
        return ResultJSON.ok(result)
    }


    @GetMapping("/release")
    @Operation(summary = "查找用户发布的面基动态")
    fun findByUserSend(pageModel: PageModel, @GetLoginUser user: User) : ResultJSON<Page<Mianji>>{
        val data = mainjiService.findMianListWithUser(user, pageModel)
        return ResultJSON.ok(data)
    }

    @DeleteMapping("/remove")
    @Operation(summary = "删除某个面基")
    fun deleteById(id: Long,@GetLoginUser user: User) : ResultJSON<String> {
        mainjiService.deleteByUserAndId(id, user)
        return ResultJSON.successMessage("删除成功")
    }
}

/**
 * 获取当前服务器的 host
 */
fun HttpServletRequest.getCurrentHost() : String  {
    return "$scheme://$serverName:$serverPort"
}
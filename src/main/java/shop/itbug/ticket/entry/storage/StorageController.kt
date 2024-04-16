package shop.itbug.ticket.entry.storage

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import shop.itbug.ticket.annotation.GetLoginUser
import shop.itbug.ticket.controller.getCurrentHost
import shop.itbug.ticket.entry.FileInfo
import shop.itbug.ticket.entry.User
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.service.FileInfoService
import shop.itbug.ticket.utils.ResultJSON

/// 
@RestController
@RequestMapping("/api/storage")
@Tag(name = "存储模块")
class StorageController {
    @Resource
    private lateinit var storageServiceImpl: StorageServiceImpl
    @Resource
    private lateinit var fileInfoService: FileInfoService

    @PostMapping("/save")
    @Operation(summary = "新增存储策略")
    fun save(@RequestBody storage: Storage): Result<Storage> {
        val save = storageServiceImpl.save(storage)
        return Result.success(save)
    }


    // 
    @PostMapping("/upload")
    @Operation(summary = "上传一个文件")
    fun uploadTest(@Parameter(description = "文件") file: MultipartFile,httpServletRequest: HttpServletRequest,@GetLoginUser user: User): Result<FileInfo?> {
        try {
            val linkUrl = storageServiceImpl.getLinkUrl(file,"admin",httpServletRequest.getCurrentHost(),user)
            return Result.success(linkUrl)
        }catch (e : Exception){
            throw BizException("上传文件失败:${e.localizedMessage}")
        }
    }


    @DeleteMapping("/delete")
    @Operation(summary = "删除一个文件")
    fun remove(id: Long) : ResultJSON<*> {
        fileInfoService.deleteById(id)
        return shop.itbug.ticket.utils.Result.ok("删除成功")
    }

}
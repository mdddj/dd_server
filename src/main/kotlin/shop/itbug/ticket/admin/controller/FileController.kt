package shop.itbug.ticket.admin.controller

import com.alibaba.fastjson2.JSONObject
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import jakarta.servlet.http.HttpServletRequest
import org.springframework.data.domain.Page
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.annotation.GetLoginUser
import shop.itbug.ticket.controller.getCurrentHost
import shop.itbug.ticket.entry.FileInfo
import shop.itbug.ticket.entry.FileInfoSaveConfig
import shop.itbug.ticket.entry.ResourcesCategory
import shop.itbug.ticket.entry.User
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.exception.CommonEnum
import shop.itbug.ticket.service.FileInfoService
import shop.itbug.ticket.service.ResourcesCategoryService
import shop.itbug.ticket.utils.*
import shop.itbug.ticket.utils.BlogUtil.CoverToPage

/**
 * @author eee
 */
@RestController
@RequestMapping("/api/file")
@Tag(name = "文件接口(管理员)")
@PreAuthorize("hasRole('admin')")
class FileController {
    @Resource
    private lateinit var resourcesCategoryService: ResourcesCategoryService

    @Resource private lateinit var fileInfoService: FileInfoService

    /**
     * 上传一个文件
     * @param file 文件
     * @return 上传后的文件信息
     */
    @PostMapping("/upload")
    @Operation(summary = "上传文件(oss)", deprecated = true)
    fun uploadWithSimple(@RequestParam("file") file: MultipartFile,request: HttpServletRequest,@GetLoginUser user: User): Result<FileInfo> {
        val config = FileInfoSaveConfig(user,request.getCurrentHost(),"public")
        val aPublic = fileInfoService.getLinkUrl(file,config)
        return Result.ok(aPublic)
    }

    /**
     * 尝试删除一个文件对象
     * @return 是否删除
     */
    @DeleteMapping("/delete")
    @Operation(summary = "删除文件")
    fun deleteFileInfo(@RequestBody jsonObject: JSONObject): Result<Boolean> {
        val id = jsonObject.getLong("id") ?: throw BizException("id is null")
        val b = fileInfoService.tryToDeleteFileinfo(id)
        return Result.ok(b)
    }

    /**
     * 获取文件夹
     *
     * @param folderId 父文件夹id
     * @return 跟文件夹或者子文件夹
     */
    @GetMapping("/get-folders")
    @Operation(summary = "文件夹")
    fun findFolders(@RequestParam(name = "id", defaultValue = "0") folderId: Long): Result<*> {
        val resourcesCategory = ResourcesCategory()
        return if (folderId == 0L) {
            // 获取根文件夹
            resourcesCategory.parentNode = null
            resourcesCategory.type = "folder"
            val allByObj = resourcesCategoryService.findAllByObj(resourcesCategory)
            Result.ok(allByObj)
        } else {
            val byId = resourcesCategoryService.findById(folderId)
            resourcesCategory.type = "folder"
            resourcesCategory.parentNode = byId
            val allByObj = resourcesCategoryService.findAllByObj(resourcesCategory)
            Result.ok(allByObj)
        }
    }

    /**
     * 根据文件夹获取文件列表
     * @param folderId  文件夹id
     * @param pageModel 分页数据
     * @param bindingResult 验证信息
     * @return  处理结果
     */
    @GetMapping("/get-files")
    @Operation(summary = "获取文件列表")
    fun getFiles(
        @RequestParam("id") folderId: Long?,
        @Validated pageModel: PageModel?,
        bindingResult: BindingResult
    ): Result<CoverToPage<FileInfo>> {
        if (bindingResult.hasErrors()) {
            bindingResult.verify()
        }
        if (folderId == null) {
            throw BizException(CommonEnum.PARAMS_ERROR)
        }
        val fileInfo = FileInfo()
        val filesByFolder = pageModel?.let { fileInfoService.findFilesByFolder(it, fileInfo) } ?: throw BizException("查找失败")
        return Result.ok(BlogUtil.coverToPage(filesByFolder))
    }


    @GetMapping("/list")
    @Operation(summary = "获取文件列表2")
    fun list(pageModel: PageModel,fileInfo : FileInfo?) : ResultJSON<CoverToPage<FileInfo>>  {
        val data = fileInfoService.findFilesByFolder(pageModel, fileInfo)
        return BlogUtil.coverToPage(data).successResult()
    }


    @GetMapping("/user")
    @Operation(summary = "获取用户发布的资源")
    fun getFileListByUser(@GetLoginUser user: User,pageModel: PageModel) : ResultJSON<Page<FileInfo>> {
        return fileInfoService.findByUser(user, pageModel).successResult()
    }
}
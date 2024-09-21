package shop.itbug.ticket.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.entry.Version
import shop.itbug.ticket.entry.VersionPlatform
import shop.itbug.ticket.entry.project.ProjectService
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.exception.CommonEnum
import shop.itbug.ticket.service.VersionService
import shop.itbug.ticket.utils.BlogUtil
import shop.itbug.ticket.utils.Result
import shop.itbug.ticket.utils.successResult
import shop.itbug.ticket.utils.verify

@RestController
@RequestMapping("/api/version")
@Tag(name = "版本接口")
class VersionController {
    @Resource
    private lateinit var versionService: VersionService

    @Resource lateinit var projectService: ProjectService

    /**
     * 查询全部已上传版本,列表查询
     * @param pageModel 分页参数
     * @param params 查询条件,可以为null
     * @return 查询记过
     */
    @GetMapping("/list")
    @Operation(summary = "查询列表")
    fun list(pageModel: PageModel, params: Version?): Result<BlogUtil.CoverToPage<Version>> {
        val all = versionService.findAll(pageModel, params)
        return Result.ok(BlogUtil.coverToPage(all))
    }

    /**
     * 获取最新的的版本
     *
     * @param platform 平台名字
     * @return 版本数据
     */
    @GetMapping("/new")
    @ResponseBody
    @Operation(summary = "获取最新的的版本")
    fun getNewVersion(
        @Parameter(
            name = "平台",
            description = "Android, Ios, Macos, Windows, Linux",
            example = "Android"
        ) @RequestParam("platform") platform: @NotBlank(message = "请输入平台") String
    ): Result<Version> {
        val newVersion = versionService.findNewVersion(platform)
            ?: throw BizException(CommonEnum.NOT_FOUND)
        return Result.ok(newVersion)
    }



    @GetMapping("/findBy")
    @Operation(summary = "条件查找一个版本信息")
    fun find(version: Version): Result<Version> {
        return versionService.findByExample(version).successResult("查找成功")
    }

    @GetMapping("/findByAll")
    @Operation(summary = "条件查询全部版本")
    fun findAllBy(version: Version): Result<List<Version>> {
        return versionService.findAllByExample(version).successResult("查找成功")
    }

    @Schema(name = "检测新版本参数")
    data class CheckVersionModel(
        @Schema(description = "项目名字") @NotBlank(message = "请输入项目名字") val appName: String,
        @Schema(description = "当前版本号") @NotBlank(message = "请输入当前版本号") val appVersion: String,
        @Schema(description = "平台,Android, Ios, Macos, Windows, Linux", example = "Macos") @NotBlank(message = "请输入平台") val platform: VersionPlatform
    )

    @GetMapping("/checkVersion")
    @Operation(summary = "检测新版本")
    fun checkNewVersion(@Valid @Parameter(description = "参数") model: CheckVersionModel, bingResult: BindingResult): Result<Version> {
        bingResult.verify()
        return versionService.checkNewVersion(model)?.successResult("获取成功") ?: throw BizException("暂无新版本")
    }
}
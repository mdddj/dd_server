package shop.itbug.ticket.controller.resource

import cn.hutool.core.date.DateUtil
import com.alibaba.fastjson2.JSONObject
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.data.domain.Page
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.annotation.GetLoginUser
import shop.itbug.ticket.annotation.ServiceHost
import shop.itbug.ticket.entry.MyResources
import shop.itbug.ticket.entry.ResourcesCategory
import shop.itbug.ticket.entry.User
import shop.itbug.ticket.entry.share.Share
import shop.itbug.ticket.entry.storage.StorageServiceImpl
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.exception.CommonEnum
import shop.itbug.ticket.model.params.IdBody
import shop.itbug.ticket.service.MianjiService
import shop.itbug.ticket.service.MyResourceService
import shop.itbug.ticket.service.ResourcesCategoryService
import shop.itbug.ticket.utils.Result
import shop.itbug.ticket.utils.ResultJSON
import shop.itbug.ticket.utils.SuccessResult
import shop.itbug.ticket.utils.successResult

@RestController
@RequestMapping("/api/app/resource/")
@Tag(name = "动态接口(APP)")
class MyResourceControllerWithApp {

    @Resource
    private lateinit var myResourceService: MyResourceService


    @Resource
    private lateinit var resourcesCategoryService: ResourcesCategoryService


    @Resource
    private lateinit var storageServiceImpl: StorageServiceImpl

    @Resource
    private lateinit var mianjiService: MianjiService

    @GetMapping("/list")
    @Operation(summary = "获取动态列表")
    fun findResourceList(
        pageModel: PageModel,
        name: String,
        @RequestParam(name = "type", defaultValue = "app") type: String
    ): ResultJSON<*> {
        val findByName = resourcesCategoryService.findByName(name, type)
        val findListByResourceCategory = myResourceService.findListByResourceCategory(findByName, pageModel)
        return findListByResourceCategory.SuccessResult("获取成功")
    }


    @Schema(name = "创建动态的参数")
    data class CreateOneResourceParams(
        @Schema(description = "分类名字,r如果不存在会自动创建")
        val cateName: String,
        @Schema(description = "正文内容")
        val content: String,
         @Schema( title = "如果没有找到这个分组，新建的类型，默认是 app") val type: String = "app",
        val title: String?,
        @Schema(description = "文件列表")
        val files: List<MultipartFile>?,
        @Schema(description = "面基对象")
        val mianjiId: Long?,
        @Schema(description = "产品分享,需要传json字符串")
        var share: String?
    )

    @PostMapping("/new")
    @Operation(summary = "创建动态")
    @Transactional()
    fun createOneResource(
        params: CreateOneResourceParams,
        @GetLoginUser user: User,
        @ServiceHost host: String
    ): ResultJSON<MyResources> {
        try {
            val cate = resourcesCategoryService.findByName(params.cateName, params.type)

            val resource = MyResources().apply {
                this.content = params.content
                this.category = cate
                this.title = params.title
                this.user = user
                this.type = cate.type
                this.createDate = DateUtil.date()
            }

            if(!params.share.isNullOrBlank()){
                try {
                    resource.share = JSONObject.parseObject(params.share!!,Share::class.java)
                }catch (e:Exception){
                    throw BizException(e)
                }
            }

            if (!params.files.isNullOrEmpty()) {
                val files =
                    storageServiceImpl.saveAllFiles(params.files, params.cateName, host, user)
                resource.images = files.toMutableSet()
            }
            if (params.mianjiId != null) {
                resource.mianji = mianjiService.findById(params.mianjiId)
            }
            val saveOrUpdate1 = myResourceService.saveOrUpdate(resource)


            return saveOrUpdate1.successResult()
        }catch (e:Exception){
            e.printStackTrace()
            throw BizException("发布失败")
        }
    }

    @GetMapping("/find-resource-category")
    @Operation(summary = "根据名字查找分类")
    fun findResourceCategory(name: String) : ResultJSON<ResourcesCategory>{
        if(name.isBlank()){
            throw BizException(CommonEnum.NOT_FOUND)
        }
        return resourcesCategoryService.findByName(name).successResult()
    }

    @GetMapping("/release")
    @Operation(summary = "获取用户发布的动态")
    fun findByUser(@GetLoginUser user: User, pageModel: PageModel): ResultJSON<Page<MyResources>> {
        try {
            val data = myResourceService.findListByUser(user, pageModel)
            return data.SuccessResult()
        }catch (e:Exception){
            e.printStackTrace()
            throw BizException("获取数据失败")
        }
    }


    @DeleteMapping("/delete")
    @Operation(summary = "删除动态")
    fun deleteResource(@GetLoginUser user: User, @RequestBody id: IdBody) : ResultJSON<String> {
        try {
            myResourceService.deleteByUserAndId(user, id.id)
            return Result.ok("删除成功")
        }catch (e:Exception){
            e.printStackTrace()
            throw BizException("删除失败")
        }
    }


    @GetMapping("/find-by-id")
    @Operation(summary = "根据ID查找动态")
    fun findById(id: Long) : ResultJSON<MyResources> = myResourceService.findById(id)?.successResult() ?: throw BizException(CommonEnum.NOT_FOUND)
}
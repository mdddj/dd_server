package shop.itbug.ticket.controller.jobs

import cn.hutool.core.date.DateUtil
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import jakarta.validation.Valid
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.annotation.GetLoginUser
import shop.itbug.ticket.entry.MyResources
import shop.itbug.ticket.entry.ResourcesCategory
import shop.itbug.ticket.entry.User
import shop.itbug.ticket.service.MyResourceService
import shop.itbug.ticket.service.ResourcesCategoryService
import shop.itbug.ticket.utils.*


@RestController
@RequestMapping("/api/public/jobs")
@Tag(name = "找工作接口")
class JobsController {

    @Resource
    lateinit var resourcesCategoryService: ResourcesCategoryService

    @Resource
    lateinit var myResourcesService: MyResourceService

    @GetMapping("/city")
    @Operation(summary = "城市列表")
    fun getJobsCity(): ResultJSON<*> {
        return resourcesCategoryService.findAllByObj(ResourcesCategory().apply { type = "jobs-city" }).SuccessResult()
    }


    data class AddJobParams(val cateId: Long, val title: String, val content: String)

    @PostMapping("/add-job")
    fun addJob(@RequestBody params: AddJobParams, @GetLoginUser user: User): ResultJSON<*> {
        val cate = resourcesCategoryService.findById(params.cateId)
        return myResourcesService.saveOrUpdate(MyResources().apply {
            this.user = user
            this.category = cate
            this.title = params.title
            this.content = params.content
            this.createDate = DateUtil.date()
            this.type = cate?.type
            this.authority = 4
            this.clickCount = 0
        }).successResult()
    }

    @GetMapping("/find-job")
    fun findJobs(@Valid pageModel: PageModel,@Valid cateId: Long,bindingResult: BindingResult) : ResultJSON<*> {
        bindingResult.verify()
        val cate = resourcesCategoryService.findById(cateId)
        val findList = myResourcesService.findAll(pageModel,MyResources().apply {
            category = cate
            type = cate?.type
        })
        return findList.SuccessResult()
    }

}
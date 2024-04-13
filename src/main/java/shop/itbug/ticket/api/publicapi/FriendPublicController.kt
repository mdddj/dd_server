package shop.itbug.ticket.api.publicapi

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.web.bind.annotation.*
import shop.itbug.ticket.entry.FriendLink
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.service.FriendLinkService
import shop.itbug.ticket.utils.R
import shop.itbug.ticket.utils.successResult


@RestController
@Tag(name = "友链接口(开放)")
@RequestMapping("/api/public/friend")
class FriendPublicController{
    @Resource private lateinit var friendLinkService: FriendLinkService


    @PostMapping("/apply-for")
    @Operation(summary = "自助申请友链")
    fun applyFor(@RequestBody params: FriendLink) : R<FriendLink> {
        val findBy = FriendLink().apply {
            url = params.url
        }
        if(friendLinkService.findAllBy(findBy).isNotEmpty()){
            throw BizException("申请失败,url博客已存在")
        }
        findBy.apply {
            url = null
            name = params.name
        }
        if(friendLinkService.findAllBy(findBy).isNotEmpty()){
            throw BizException("存在同名的命名,请修改后重试")
        }
        try{
            return friendLinkService.save(params).successResult("申请已提交,审核通过后将显示")
        }catch (e:Exception){
            throw BizException(e.localizedMessage)
        }
    }

    @GetMapping("/all")
    @Operation(summary = "获取所有友链(申请通过的)")
    fun findAll(): R<List<FriendLink>>{
        return friendLinkService.findAllBy(FriendLink().apply {
            state = 1
        }).successResult("查找成功")
    }

}
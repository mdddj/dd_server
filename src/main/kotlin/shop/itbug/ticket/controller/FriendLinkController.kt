package shop.itbug.ticket.controller

import cn.hutool.core.date.DateUtil
import com.alibaba.fastjson2.toJSONString
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import shop.itbug.ticket.entry.FriendLink
import shop.itbug.ticket.ex.log
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.service.FriendLinkService
import shop.itbug.ticket.utils.Result
import shop.itbug.ticket.utils.successResult
import shop.itbug.ticket.utils.verify

/**
 * 友链相关操作
 * @author ldd
 */
@RestController
@RequestMapping("/api/friend")
@Tag(name = "友链接口(开放)")
class FriendLinkController {
    @Resource
    private lateinit var friendLinkService: FriendLinkService

    /**
     * 获取全部友链
     * @param friendLink 查询条件
     * @return  友链集合列表
     */
    @GetMapping("/list")
    @Operation(summary = "获取全部友链", description = "支持过滤查询")
    fun findAll(friendLink: FriendLink?): Result<List<FriendLink>> {
        if(friendLink==null) return friendLinkService.findAll().successResult()
        return Result.ok(friendLinkService.findAllBy(friendLink))
    }

    /**
     * 保存或者修改一个友链
     * @param friendLink 查询对象
     * @return 保存后的对象
     */
    @PostMapping("/save")
    @Operation(summary = "添加一个友链对象")
    fun save(@RequestBody @Validated friendLink: FriendLink, bindingResult: BindingResult): Result<FriendLink> {
        log().info(friendLink.toJSONString())
        bindingResult.verify()
        val find = FriendLink().apply { url = friendLink.url }
        val all = friendLinkService.findAllBy(find)
        if (all.isNotEmpty()) {
            // 如果不为空，则需要先删除
            throw BizException("不支持重复提交")
        }
        friendLink.state = 0
        friendLink.createDate = DateUtil.date()
        return Result.ok(friendLinkService.save(friendLink))
    }
}
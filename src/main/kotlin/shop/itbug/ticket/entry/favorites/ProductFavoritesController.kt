package shop.itbug.ticket.entry.favorites

import com.alibaba.fastjson2.JSONObject
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.annotation.GetLoginUser
import shop.itbug.ticket.entry.User
import shop.itbug.ticket.entry.ifNullThrowBizException
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.exception.CommonEnum
import shop.itbug.ticket.exception.tureToNull
import shop.itbug.ticket.model.params.IdKey
import shop.itbug.ticket.utils.Result
import shop.itbug.ticket.utils.ResultJSON
import shop.itbug.ticket.utils.verify


@RestController
@Tag(name = "用户产品收藏")
@RequestMapping("/api/user/favorites")
class ProductFavoritesController {

    val log: Logger = LoggerFactory.getLogger(ProductFavoritesController::class.java)
    @Autowired lateinit var productFavortiesService: ProductFavortiesService


    /**
     * 添加收藏
     */
    @PostMapping("/save")
    @Operation(summary = "添加收藏")
    fun save(
        @GetLoginUser @Parameter(hidden = true) user: User,
        @RequestBody @Validated product: ProductFavorites,
        @Parameter(hidden = true) bindingResult: BindingResult
    ): ResultJSON<*> {
        log.info("添加收藏:${JSONObject.toJSONString(product)} ")
        bindingResult.verify()
        user.ifNullThrowBizException()
        product.user = user
        val saved = productFavortiesService.save(product)
        return ResultJSON.ok(saved,"收藏成功")
    }

    /**
     * 删除某个收藏
     */
    @PostMapping("/remove")
    @Operation(summary = "删除收藏")
    fun delete(
        @GetLoginUser @Parameter(hidden = true) user: User,
        @Validated @RequestBody idKey: IdKey,
        @Parameter(hidden = true) bindingResult: BindingResult
    ): ResultJSON<String> {
        bindingResult.verify()
        user.ifNullThrowBizException()
        //验证这格产品是不是这个用户的
        productFavortiesService.findListWithUserAndId(user, idKey.idValue!!).isEmpty().tureToNull()
            ?: throw BizException("无法删除此产品,请联系管理员")
        idKey.idValue.let { productFavortiesService.remove(it!!) }
        return Result.ok("删除成功")
    }

    @GetMapping("/list")
    @Operation(summary = "查询收藏列表")
    fun list(@GetLoginUser @Parameter(hidden = true) user: User, @Valid pageModel: PageModel?, @Parameter(hidden = true) bindingResult: BindingResult): ResultJSON<Page<ProductFavorites>> {
        if (pageModel==null) {
            throw  BizException(CommonEnum.PARAMS_VIOLATION_EXCEPTION)
        }
        bindingResult.verify()
        user.ifNullThrowBizException()
        return ResultJSON.ok(productFavortiesService.findList(user, pageModel))
    }
}
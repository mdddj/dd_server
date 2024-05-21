package shop.itbug.ticket.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.media.SchemaProperty
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import org.apache.commons.lang3.StringUtils
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.annotation.GetLoginUser
import shop.itbug.ticket.entry.FileInfo
import shop.itbug.ticket.entry.FileInfoSaveConfig
import shop.itbug.ticket.entry.User
import shop.itbug.ticket.entry.storage.StorageServiceImpl
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.exception.ResultDialogType
import shop.itbug.ticket.service.*
import shop.itbug.ticket.shiro.JwtUtil
import shop.itbug.ticket.utils.*

/**
 *
 * @author eee
 */
@RestController
@RequestMapping("/api")
@Tag(name = "用户接口")
class UserController {


    @Resource
    private lateinit var userService: UserService

    @Resource
    lateinit var myResourceService: MyResourceService

    @Resource
    lateinit var mianjiService: MianjiService

    @Resource
    lateinit var zheTkService: ZheTkService


    @Resource
    lateinit var fileInfoService: FileInfoService

    /**
     * 根据token获取用户信息失败
     * @param token   jwt token
     * @param request 请求头
     * @return 用户信息
     */
    @GetMapping("/get-user-by-token")
    @Operation(summary = "根据token查询用户信息")
    fun getCurrentLoginUser(
        @RequestParam(
            name = "token",
            defaultValue = ""
        ) @Parameter(name = "用户token", required = true) token: String,
        @Parameter(hidden = true) request: HttpServletRequest
    ): Result<User> {
        var userToken = token
        if (StringUtils.isBlank(userToken)) {
            userToken = request.getHeader("authorization")
        }
        val id = JwtUtil.getUsername(userToken)
        if (id != null) {
            val user = userService.findByUserId(id) ?: throw BizException("获取用户信息失败")
            val verify = JwtUtil.verify(userToken, user.id!!, user.password)
            return if (!verify) {
                throw BizException("登录信息已过时,请重新登录")
            } else Result.ok(user)
        }
        throw BizException("查询用户信息失败")
    }

    /**
     * 用户退出登录
     *
     * @return 操作结果
     */
    @PostMapping("/user/logout")
    @Operation(summary = "用户退出登录")
    fun logout(): Result<*> {
        //todo 退出登录
        return Result.err("你已成功退出登录.")
    }

    /**
     * 获取用户信息
     *
     * @param id          用户ID
     * @param loginNumber 用户登录名称
     * @return 用户资料
     */
    @GetMapping("/user-public/detail")
    @Operation(summary = "获取用户信息 (开放)")
    fun getUserInfo(
        @RequestParam(value = "id", required = false) @Parameter(name = "用户ID") id: Long?,
        @Parameter(name = "登录用户名") @RequestParam(value = "loginNumber", required = false) loginNumber: String?
    ): Result<*> {
        var user: User? = null
        if (id != null) {
            user = userService.findByUserId(id)
        }
        if (user == null && StringUtils.isNotBlank(loginNumber)) {
            user = userService.findByLoginNumber(loginNumber)
        }
        return if (user == null) Result.err("获取用户信息失败") else Result.ok(user)
    }

    /**
     * 查询用户发布的帖子
     */
    @GetMapping("/user/posts")
    @Operation(summary = "查询用户发布的帖子")
    fun findUserResources(
        @GetLoginUser user: User,
        @Validated pageModel: PageModel,
        bindingResult: BindingResult
    ): Result<*> {
        bindingResult.verify()
        return myResourceService.findListByUser(user, pageModel).successResult()
    }

    /**
     * 查询用户发布的面积申请
     */
    @GetMapping("/user/mianji")
    fun findUserWithMianji(
        @GetLoginUser user: User,
        @Validated pageModel: PageModel,
        bindingResult: BindingResult
    ): Result<*> {
        bindingResult.verify()
        return mianjiService.findMianListWithUser(user, pageModel).successResult()
    }


    /**
     * 使用邮箱注册用户
     */
    @Schema(name = "邮箱相关参数")
    data class EmailRegisterParams(
        ///如果是获取验证码接口,则将验证码发送到这个邮箱.如果是验证邮箱注册用户,则将用此邮箱注册一个新账号
        @SchemaProperty(name = "邮箱") @NotBlank(message = "请输入邮箱") @Email(message = "请输入有效的邮箱") val email: String,
        @SchemaProperty(name = "验证码") val code: String? = null,
        @SchemaProperty(name = "账号密码") val password: String? = null
    )

    /**
     * 介绍邮箱验证码
     */
    @PostMapping("/user-public/email-register")
    @Operation(summary = "获取邮箱验证码")
    fun emailRegister(
        @Validated @RequestBody params: EmailRegisterParams,
        bindingResult: BindingResult
    ): ResultJSON<*> {
        bindingResult.verify()
        return userService.regAccountWithEmail(params).successResult("发送验证码成功")
    }

    /**
     * 邮箱验证功能接口
     */
    @PostMapping("/user-public/email-valid")
    @Operation(summary = "验证邮箱并注册账号")
    fun emailValid(
        @Validated @RequestBody params: EmailRegisterParams,
        bindingResult: BindingResult
    ): ResultJSON<*> {
        bindingResult.verify()
        return userService.validEmailCode(params).successResult("账号注册成功")
    }


    @PostMapping("/open-vip")
    @Operation(summary = "开通普通 vip,默认所有人都可以开通 vip")
    fun openVip(@GetLoginUser user: User): Result<String> {
        if ( (user.vip ?: 0 )< 1) {
            user.vip = 1
            userService.save(user)
            return Result<String>().apply {
                message = "恭喜，您已经成功开通了 VIP!"
                type = ResultDialogType.Dialog
            }
        }
        return Result<String>().apply {
            message = "您已经是 VIP 用户了"
        }
    }


    @PostMapping("/update-avatar")
    @Operation(summary = "修改用户头像")
    fun updateUserAvatar(
        file: MultipartFile,
        @GetLoginUser user: User,
        httpServletRequest: HttpServletRequest
    ): ResultJSON<FileInfo> {
        try {
            val config = FileInfoSaveConfig(user,httpServletRequest.getCurrentHost(),"用户头像")
            val fileInfo = fileInfoService.getLinkUrl(file, config)
                ?: throw BizException("更换头像失败，请稍后重试")

            fileInfo.url?.let {
                if (it.isNotBlank()) {
                    user.picture = it
                }
            }
            userService.save(user)
            return fileInfo.successResult()
        } catch (e: Exception) {
            e.printStackTrace()
            throw BizException("修改失败")
        }
    }

    data class UpdateNameParams(val name: String)

    @PostMapping("/user/update-username")
    @Operation(summary = "修改用户昵称")
    fun updateUserName(@GetLoginUser user: User, @RequestBody params: UpdateNameParams): ResultJSON<Boolean> {
        user.nickName = params.name
        userService.save(user)
        return ResultJSON.ok(true)
    }

    data class UpdateCityParams(val city: String)

    @PostMapping("/user/update-city")
    @Operation(summary = "修改城市")
    fun updateUserCity(@GetLoginUser user: User, @RequestBody params: UpdateCityParams): ResultJSON<Boolean> {
        user.city = params.city
        userService.save(user)
        return ResultJSON.ok(true)
    }

    data class UpdateJobParams(val job: String)

    @PostMapping("/user/update-job")
    @Operation(summary = "修改工作")
    fun updateUserJob(@GetLoginUser user: User, @RequestBody params: UpdateJobParams): ResultJSON<Boolean> {
        user.job = params.job
        userService.save(user)
        return ResultJSON.ok(true)
    }

    data class UpdateDescParams(val intro: String)

    @PostMapping("/user/update-desc")
    @Operation(summary = "修改个人介绍")
    fun updateUserDesc(@GetLoginUser user: User, @RequestBody params: UpdateDescParams): ResultJSON<Boolean> {
        user.intro = params.intro
        userService.save(user)
        return ResultJSON.ok(true)
    }


    @GetMapping("/user/all-user")
    @Operation(summary = "获取全部用户")
    @PreAuthorize("hasRole('admin')")
    fun getAllUsers() : R<List<User>> {
        return userService.findAll().successResult("查询成功")
    }

    /**
     * 尝试绑定渠道ID
     */
    @PostMapping("/relation/bind")
    @Operation(summary = "绑定渠道ID")
    fun bindRelationID(@GetLoginUser user: User) : R<User> {
        val findScItem = zheTkService.findScItem(user.id!!) ?: throw BizException("未检测到渠道ID")
        user.relationId = findScItem.relationID.toString()
        return userService.save(user).successResult("绑定渠道ID成功")
    }
}



package shop.itbug.ticket.service.impl

import cn.hutool.core.lang.UUID
import cn.hutool.core.util.RandomUtil
import cn.hutool.extra.mail.MailUtil
import jakarta.annotation.Resource
import org.springframework.data.domain.Example
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.stereotype.Service
import shop.itbug.ticket.admin.controller.AdminAuthController
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.admin.model.getCurrentPage
import shop.itbug.ticket.controller.UserController
import shop.itbug.ticket.dao.UserDao
import shop.itbug.ticket.entry.User
import shop.itbug.ticket.entry.UserAccountType
import shop.itbug.ticket.entry.encodePassword
import shop.itbug.ticket.entry.validPassword
import shop.itbug.ticket.ex.log
import shop.itbug.ticket.ex.passwordEncode
import shop.itbug.ticket.ex.sendValidCode
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.model.api.LoginSuccessResultModel
import shop.itbug.ticket.model.api.generateApiToken
import shop.itbug.ticket.model.params.LoginParam
import shop.itbug.ticket.service.UserService
import shop.itbug.ticket.service.config.MailAccountConfig
import shop.itbug.ticket.utils.CheckStrUtils
import java.util.*

@Service
class UserServiceImpl : UserService {


    @Resource
    private lateinit var userDao: UserDao

    @Resource
    private lateinit var mailAccountConfig: MailAccountConfig

    override fun findByLoginNumber(loginNumber: String?): User? {
        return userDao.findByLoginNumber(loginNumber)
    }

    override fun findByPhone(phone: String?): User? {
        return userDao.findByPhone(phone ?: "")
    }

    override fun findUsersByType(type: Int): List<User> {
        return userDao.findAllByType(type)
    }

    override fun createAdminAccount(loginNumber: String, password: String, email: String): User {
        if (findUsersByType(UserAccountType.Admin.type).isNotEmpty()) {
            throw BizException("超级管理员账号已经注册")
        }
        val salt = UUID.randomUUID(true).toString(true)
        val user = User()
        user.loginNumber = loginNumber
        user.userPassword = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(password)
        user.nickName = "梁典典"
        user.salt = salt
        user.email = email
        user.picture = "https://static.saintic.com/picbed/huang/2021/08/17/1629169985484.jpg"
        user.type = UserAccountType.Admin.type
        return userDao.save(user)
    }

    override fun save(user: User): User {
        return userDao.save(user)
    }

    override fun findByUserId(userId: Long): User? {
        val byId = userDao.findById(userId)
        return byId.orElse(null)
    }

    override fun login(param: LoginParam): LoginSuccessResultModel {

        val user: User?

        when (param.loginType) {
            "email" -> {
                user = emailLogin(param)
            }

            else -> {
                try {
                    user = findByLoginNumber(param.loginNumber) ?: throw BizException("账号错误")
                    val match = user.validPassword(param.password)
                    if (match.not()) throw BizException("登录失败,密码错误")
                } catch (e: Exception) {
                    throw e
                }
            }
        }
        return LoginSuccessResultModel(
            user.generateApiToken(),
            user
        )

    }

    /**
     * 创建一个普通用户账号
     *
     * @param loginName 登录账号
     * @param password  登录密码
     */
    override fun registor(loginName: String, password: String?, pic: String?) {
        // 判断登录账户是否被人注册
        val findedUser = findByLoginNumber(loginName)
        if (findedUser == null) {
            val salt = UUID.randomUUID(true).toString(true)
            val user = User()
            user.loginNumber = loginName
            user.userPassword = encodePassword(password ?: "123456")
            user.nickName = "用户@$loginName"
            user.salt = salt
            pic?.let {
                user.picture = it
            }

            user.type = 0
            userDao.save(user)
        } else {
            throw BizException(10001, "该用户名已经被人抢先占用了,请另外取一个吧")
        }
    }

    override fun findList(user: User?, pageModel: PageModel): Page<User> {
        val of = PageRequest.of(pageModel.getCurrentPage(), pageModel.pageSize)
        return if (user == null) {
            userDao.findAll(of)
        } else userDao.findAll(
            Example.of(
                user
            ), of
        )
    }

    /**
     * 发送邮件的服务
     * @param email 接受人
     * @param title 标题
     * @param content 正文内容
     * @param s 是否为html
     */
    override fun sendSimpleEmail(email: String?, title: String?, content: String?, s: Boolean?): String? {
        try {
            return MailUtil.send(email, title, content, s!!)
        } catch (e: Exception) {
            throw BizException("发送邮件失败:${e}")
        }
    }

    override fun findUserTodayUploadImageCount(user: User): Int {
        return 0
    }

    /**
     * 使用邮箱注册用户
     */
    override fun regAccountWithEmail(params: UserController.EmailRegisterParams): User {
        //判断是否已经被注册
        val isReg = userDao.findByEmailAndStatus(params.email, 0)
        if (isReg != null) throw BizException("邮箱已经被注册.")

        //查找是否已经存在用户
        val user = userDao.findByEmailAndStatus(params.email, 4)
        if (user != null) {
            //已存在,生成一个新的验证码,并发送到邮箱
            user.activeCode = verifyCode
            user.sendValidCode(mailAccountConfig.setting)
            return save(user)
        } else {
            //创建一个新用户
            val code = verifyCode
            val newUser = User().apply {
                email = params.email
                activeCode = code
                status = 4
            }
            //发送验证码
            newUser.sendValidCode(mailAccountConfig.setting)
            return userDao.save(newUser)
        }
    }

    /**
     * 验证邮箱的验证码,如果通过,转成正式用户
     */
    override fun validEmailCode(params: UserController.EmailRegisterParams): User {
        if (params.code == null) {
            throw BizException("请输入验证码")
        }
        if (params.password == null) {
            throw BizException("请输入密码")
        }
        if (!CheckStrUtils.verifyPassword(params.password)) {
            throw BizException("密码似乎太简单了,请重新设置一个")
        }
        var user = userDao.findByEmailAndStatus(params.email, 0)
        if (user != null) {
            throw BizException("邮箱已经验证通过")
        }
        user = userDao.findByEmailAndStatus(params.email, 4)
        if (user == null) {
            throw BizException("邮箱未注册")
        }
        user = userDao.findByEmailAndActiveCode(params.email, params.code)
        if (user == null) {
            throw BizException("验证失败,请重试")
        }
        //验证通过,更新信息,生成一个默认密码
        user = user.apply {
            status = 0
            userPassword = passwordEncode(params.password)
        }
        //把默认密码通过邮件发送给用户
        sendSimpleEmail(
            user.email,
            "典典账号注册成功",
            "请牢记你的密码:${params.password}",
            true
        )
        return save(user)
    }

    /**
     * 使用邮箱的方式进行登录
     */
    override fun emailLogin(param: LoginParam): User {
        userDao.findByEmail(param.loginNumber) ?: throw BizException("无此账号")
        val user = userDao.findByEmailAndStatus(param.loginNumber, 0) ?: throw BizException("该邮箱未注册")
        if (PasswordEncoderFactories.createDelegatingPasswordEncoder().matches(param.password, user.password)) {
            return user
        }
        throw BizException("密码错误,请重试")
    }

    override fun removeUser(model: AdminAuthController.DeleteUserParam) {
        try {
            userDao.deleteById(model.id)
        } catch (e: Exception) {
            throw BizException("删除失败")
        }
    }


    override fun findAll(): List<User> {
        return userDao.findAll()
    }

    override fun findByRelationId(relationId: String): User? {
        return userDao.findByRelationId(relationId)
    }

    override fun updatePassword(user: User, oldPassword: String, newPassword: String) {
        log().info("update password :${oldPassword} $newPassword")
        val valid = user.validPassword(oldPassword)
        if (valid) {
            user.userPassword = encodePassword(newPassword)
            userDao.save(user)
        } else {
            throw BizException("密码验证失败")
        }

    }
}

var verifyCode: String = java.lang.String.valueOf(Random().nextInt(899999) + 100000)
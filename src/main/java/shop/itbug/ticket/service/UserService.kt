package shop.itbug.ticket.service

import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import shop.itbug.ticket.admin.controller.AdminAuthController
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.controller.UserController
import shop.itbug.ticket.entry.User
import shop.itbug.ticket.model.api.LoginSuccessResultModel
import shop.itbug.ticket.model.params.LoginParam

/**
 * @author 梁典典
 */
@Service
interface UserService  {
    /**
     * 根据登录名字获取用户对象
     * @param loginNumber 用户账号名字
     * @return  用户对象
     */
    fun findByLoginNumber(loginNumber: String?): User?

    /**
     * 根据用户手机号码获取用户对象
     * @param phone 用户手机号
     * @return  用户对象
     */
    fun findByPhone(phone: String?): User?

    /**
     * 用户用户类型获取用户列表
     * @param type  用户类型
     * @return  用户对象列表
     */
    fun findUsersByType(type: Int): List<User>

    /**
     * 创建一个管理员的账号
     * @param loginNumber   登录名字
     * @param password  登录密码
     */
    fun createAdminAccount(loginNumber: String, password: String,email:String = "") : User

    /**
     * 保存一个用户对象到数据库
     * @param user  要保存的数据对象
     * @return  保存成功后的数据对象
     */
    fun save(user: User): User

    /**
     * 用户用户id获取用户对象
     * @param userId    用户id
     * @return  查找到的用户对象
     */
    fun findByUserId(userId: Long): User?

    /**
     * 用户登录
     * @param param 用户参数
     * @return  登录成功后的token
     */
    fun login(param: LoginParam): LoginSuccessResultModel

    /**
     * 创建一个普通用户账号
     * @param loginName 登录账号
     * @param password  登录密码
     * @param pic 头像
     */
    fun registor(loginName: String, password: String?, pic: String?)

    /**
     * 查询已注册用户列表
     * @param user  查询条件
     * @param pageModel 分页数据
     * @return  json
     */
    fun findList(user: User?, pageModel: PageModel): Page<User>

    /**
     * 给用户发送电子邮件
     * @param email 邮箱
     * @param title 标题
     * @param content 正文
     * @param s 是否简单类型的邮件
     * @return 结果
     */
    fun sendSimpleEmail(email: String?, title: String?, content: String?, s: Boolean?): String?

    /**
     * 查询用户今天一共上传了多少张照片
     * 用户上传照片需要做一个限制,每天限制上传10张照片
     */
    fun findUserTodayUploadImageCount(user: User): Int


    /**
     * 使用邮箱注册用户
     */
    fun regAccountWithEmail(params: UserController.EmailRegisterParams): User

    /**
     * 验证邮箱的验证码,如果通过,转成正式用户
     */
    fun validEmailCode(params: UserController.EmailRegisterParams) : User

    /**
     * 使用邮箱的方式进行登录
     */
    fun emailLogin(param: LoginParam) : User

    fun removeUser(model: AdminAuthController.DeleteUserParam)


    fun findAll() : List<User>


    fun findByRelationId(relationId: String): User?

    fun updatePassword(user: User,oldPassword: String, newPassword: String)

}
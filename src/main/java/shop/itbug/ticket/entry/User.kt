package shop.itbug.ticket.entry

import cn.hutool.core.lang.Console
import com.fasterxml.jackson.annotation.JsonIgnore
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.media.SchemaProperty
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.exception.CommonEnum
import java.math.BigDecimal
import java.math.BigInteger
import java.util.*

/**
 * 当用户为空的时候,会抛出 [BizException]异常,后续逻辑无法继续执行
 */
fun User?.ifNullThrowBizException() {
    if (this == null) {
        Console.log("无法或者用户信息...CommonEnum.SIGNATURE_NOT_MATCH")
        throw BizException(CommonEnum.SIGNATURE_NOT_MATCH)
    }
}




enum class UserAccountType(val type: Int) {
    Admin(100)
}




/**
 * @author eee
 */
@Entity
@Schema(name = "用户类")
open class User :UserDetails {
    /**
     * 用户id
     */
    @GeneratedValue
    @Id
    open var id: Long? = null

    /**
     * 用户登录名
     */
    @Schema(description = "用户名")
    open var loginNumber: String = ""

    /**
     * 用户的昵称
     */
    @Schema(description = "用户昵称")
    open var nickName: String = ""

    /**
     * 用户的邮箱
     */
    @Schema(description = "邮箱")
    open var email: String = ""

    @Schema(description="用户介绍")
    open var intro : String? = null

    /**
     * 用户头像
     */
    @Schema(description = "头像")
    open var picture: String = ""

    /**
     * 用户电话
     */
    open var phone: String? = ""

    /**
     * 用户密码
     */
    @JsonIgnore
    @Column(name = "password")
    @Schema(description = "用户密码")
    open var userPassword: String = ""

    @Schema(description = "所在城市")
    open var city : String = ""

    @Schema(description = "工作")
    open var job: String = ""

    /**
     * 最近登录时间
     */
    @Schema(description = "最近登录时间")
    open var loginTime: Date = Date()

    /**
     * 账号类型
     */
    @Schema(description = "账号类型")
    open var type: Int? = null


    /**
     * 是否为VIP会员
     * 0. 不是,默认
     * 1. 是,普通vip
     * 2. 是,超级VIP
     * 3. 是特殊VIP
     */
    @Schema(description = "是否为VIP会员,0. 不是,默认 1. 是,普通vip 2. 是,超级VIP 3. 是特殊VIP" )
    open var vip: Int? = null


    /**
     * 用户钱包的金额,
     * 默认是0
     */
    @Schema(description = "用户钱包的金额")
    open var wallet: BigDecimal? = null


    /**
     * open ai token
     * 提问所需要消耗的
     */
    @Schema(description = "open ai token 提问所需要消耗的")
    open var openAiTokens: BigInteger? = null


    /**
     * 特殊用户,提问不消耗token
     * 充钱的用户: 设置为true
     */
    @Schema(description = "特殊用户,提问不消耗token,充钱的用户: 设置为true")
    open var openAiFlag: Boolean? = null


    /**
     * 激活邮箱的代码
     */
    @JsonIgnore
    @Schema(description = "激活邮箱的代码")
    open var activeCode: String?  = null

    /**
     * 用户的权限列表
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = [JoinColumn(referencedColumnName = "id", name = "user_id")],
        inverseJoinColumns = [JoinColumn(referencedColumnName = "id", name = "role_id")]
    )
    open var roles: MutableSet<Role>? = null


    /**
     * 用户是否被禁用
     * [0] - 默认状态下,可以正常使用
     * [1] - 账户密码失效
     * [2] - 账户被锁定
     * [3] - 账户密码被锁定
     * [4] - 需要验证邮箱
     */
    @Schema(description = "用户是否被禁用,[0] - 默认状态下,可以正常使用,[1] - 账户密码失效,[2] - 账户被锁定,[3] - 账户密码被锁定,[4] - 需要验证邮箱")
    open var status: Int? = null

    /**
     * 盐
     * 用来加密和混淆密码
     */
    @JsonIgnore
    open var salt: String? = null


    @Schema(description = "渠道ID")
    open var relationId: String? = null


    @ManyToOne
    @SchemaProperty(name = "绑定公司")
    @Schema(description = "所属公司")
    open var enterprise: Enterprise? = null



    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return roles?.toMutableList() ?: mutableListOf()
    }

    @JsonIgnore
    override fun getPassword(): String {
        return userPassword
    }

    override fun getUsername(): String {
        return loginNumber
    }

    /**
     * 账户密码是否失效
     */
    override fun isAccountNonExpired(): Boolean {
        return status == 1
    }

    /**
     * 账户是否被锁定
     */
    override fun isAccountNonLocked(): Boolean {
        return status == 2
    }

    /**
     * 账户密码是否被锁定
     */
    override fun isCredentialsNonExpired(): Boolean {
        return status == 3
    }

    /**
     * 账户是否可用
     */
    override fun isEnabled(): Boolean {
        return status == 0
    }

    data class UpdateUserModel(
        val id: Long,
        val nickName: String? = null,
        val openAiVip: Boolean? = null,
        val tokens: String? = null,
        val status: Int? = null,
        val vip: Int? = null,
        val enterpriseId: Long? = null
    )

    fun hasAdminRole() : Boolean {
        return roles?.find { it.name == "admin" } != null
    }

    fun getShowName() : String {
        return "$email $nickName"
    }
}
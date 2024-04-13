package shop.itbug.ticket.entry

import cn.hutool.core.date.DateUtil
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.media.SchemaProperty
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.util.*


/**
 * @author ldd
 */
@Entity
@Schema(name = "面基模型")
open class Mianji  {
    @Id
    @GeneratedValue
    @SchemaProperty(name = "主键ID")
    open var id: Long? = null

    /**
     * 姓名
     */
    @SchemaProperty(name = "姓名")
    @NotBlank(message = "请输入您的称呼")
    open var name: String = ""

    /**
     * 年龄
     */
    @SchemaProperty(name = "年龄")
    @NotNull(message = "请输入您的年龄段")
    open var age: Int = 0

    /**
     *
     * soul 用户名
     */
    @SchemaProperty(name = "soul用户名")
    open var soulName: String = ""

    /**
     * soul 用户名
     */
    @SchemaProperty(name = "soul条形码")
    open var soulCodeImage: String = ""

    /**
     * 面基事项
     */
    @SchemaProperty(name = "面基干嘛的")
    open var mianjiInfo: String = ""

    /**
     * 你的住址
     */
    @SchemaProperty(name = "你的住址")
    open var location: String = ""

    /**
     * 你想和我去的面基地点
     */
    @SchemaProperty(name = "想去面基的地点")
    open var toLocation: String = ""

    /**
     * 你和典典的相关
     */
    @SchemaProperty(name = "你和典典的备注")
    open var aboutDiandian: String = ""

    /**
     * 面基状态。1，表示已面基。0，表示还没面基
     */
    @SchemaProperty(name = "面基的状态,。1，表示已面基。0，表示还没面基")
    open var state: Int = 0


    /**
     * 谁发布的
     */
    @ManyToOne
    open var user: User? = null

    /**
     * 创建时间
     */
    open var createDate: Date? = DateUtil.date()

    /**
     * 图片列表
     */
    @OneToMany
    open var images: MutableSet<FileInfo> = mutableSetOf()



}
package shop.itbug.ticket.entry

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.*
import shop.itbug.ticket.entry.share.Share
import shop.itbug.ticket.entry.useagent.MyUseAgent
import java.util.*

/**
 * @author eee
 */
@Entity
@Schema(name = "资源对象")
open class MyResources  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description="主键")
    open var id: Long? = null

    /**
     * 谁发布的
     */
    @ManyToOne
    @Schema(description="发布人")
    open var user: User? = null

    @ManyToOne
    @Schema(description="分类")
    open var category: ResourcesCategory? = null

    /**
     * 资源标题
     */
    @Lob
    @Schema(description="标题")
    open var title: String? = null

    /**
     * 资源内容
     */
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    @Schema(description="正文内容")
    open var content: String? = null

    /**
     * 发布时间
     */
    @Schema(description="发布时间")
    open var createDate: Date? = null

    /**
     * 最后修改的一次时间
     */
    @Schema(description="修改时间")
    open var updateDate: Date? = null

    /**
     * 标签
     */
    @Schema(description="标签")
    open var label: String? = null

    /**
     * 资源缩略图
     */
    @Schema(description="缩略图")
    open var thumbnailImage: String? = null

    /**
     * 描述
     */
    @Lob
    @Schema(description="描述")
    open var description: String? = null

    /**
     * 相关链接
     */
    @Schema(description="链接")
    open var links: String? = null

    /**
     * 类型
     */
    @Schema(description="类型")
    open var type: String? = null

    /**
     * 权限  0 : 公开  1: 仅管理员可见 2 : 草稿 4: 需要审核
     */
    @Schema(description="权限")
    open var authority: Int? = null

    /**
     * 点击量
     */
    @Schema(description = "点击量")
    open var clickCount: Long? = null

    /**
     * 文件类型专用
     */
    @ManyToOne
    @Schema(description = "文件专用")
    open var fileInfo: FileInfo? = null

    /**
     * 图片列表
     */
    @Schema(description = "图片列表")
    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    open var images: MutableSet<FileInfo>? = null

    @ManyToOne
    @Schema(description = "面基专用")
    open var mianji: Mianji? = null


    @Schema(description = "url")
    open var browserUrl: String? = null


    @Schema(description="产品分享")
    @OneToOne(cascade = [CascadeType.ALL])
    open var share: Share? = null

    @Schema(description = "系统平台")
    @OneToOne(cascade = [CascadeType.ALL])
    open var useAgent: MyUseAgent? = null


    @Schema(description = "发布设备平台")
    open var platformString: String? = null


}


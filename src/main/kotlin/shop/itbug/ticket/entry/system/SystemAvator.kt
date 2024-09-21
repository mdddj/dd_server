package shop.itbug.ticket.entry.system

import jakarta.persistence.*

/**
 * 系统预设图片
 * @author 梁典典
 */
@Table(name = "system_avator")
@Entity
open class SystemAvator {

    constructor()
    constructor(type: Int, url: String){
        this.type = type
        this.url = url
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    open var id: Long? = null

    /**
     * 图片简介
     */
    open var intro: String? = null

    /**
     * 图片类型 1: 头像
     * 2: 背景
     */
    open var type: Int? = null
    /**
     * 图片的url链接
     */
    open var url: String? = null
}
package shop.itbug.ticket.model

import shop.itbug.ticket.entry.MyResources

open class ResourceCategoryTreeModel {
    /**
     * 标题
     */
    open var title: String? = null

    /**
     * ID 主键
     */
    open var id: Long? = null

    /**
     * 是否存在子节点
     */
    open var hasChildren = false

    /**
     * 全部子节点的ID
     */
    open var childrenIds: List<Long>? = null

    /**
     * 图片
     */
    open var logo: String? = null

    /**
     * 子节点
     */
    open var children: ArrayList<ResourceCategoryTreeModel>? = null

    /**
     * 全部子节点
     */
    open var resources: List<MyResources>? = null
}
package shop.itbug.ticket.model.params


open class CommentParamsModel {
    /**
     * 大淘客商品id（id和goodsid其中一个必填）
     */
    open var id: String? = null

    /**
     * 淘宝商品id（id和goodsid其中一个必填）
     */
    open var goodsId: String? = null

    /**
     * 默认：0-全部 评论类型：0-全部；1-含图；2-含视频；
     */
    open var type: String? = null

    /**
     * 排序方式 0-按热度排序 1-按最新添加排序 默认为0
     */
    open var sort: String? = null

    /**
     * 评论类型 0-全部 1-去掉默认好评 默认为0(2020/12/30新增字段)
     */
    open var haopingType: String? = null
}
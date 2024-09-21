package shop.itbug.ticket.model.params

import jakarta.validation.constraints.NotBlank

class TaolijinProductsParam {
    private val pageSize: @NotBlank(message = "每页返回条数，每页条数支持输入10,20，50,100,200") String? = null
    private val pageId: @NotBlank(message = "分页id：常规分页方式，请直接传入对应页码（比如：1,2,3……）") String? = null
    private val cids: String? = null
    private val sort: String? = null
    private val keyWord: String? = null
    private val goodsType: String? = null
}
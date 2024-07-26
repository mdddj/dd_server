package shop.itbug.ticket.model.blog

import shop.itbug.ticket.entry.blog.Category
import shop.itbug.ticket.entry.blog.Tag

class StatisticsResultModel {
    var blogCount: Long? = null
    var cateCount: Long? = null
    var tagCount: Long? = null
    var categoryList: List<Category>? = null
    var tags: List<Tag>? = null
    var monthsCounts: List<MonthResultModel>? = null
    var archiveModels: List<ArchiveModel>? = null
}
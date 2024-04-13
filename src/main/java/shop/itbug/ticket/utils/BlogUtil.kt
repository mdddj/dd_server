package shop.itbug.ticket.utils

import org.apache.commons.lang3.StringUtils
import org.springframework.data.domain.Page
import shop.itbug.ticket.model.api.CachePage
import shop.itbug.ticket.model.blog.SimplePage
import java.util.*
typealias MyPageResult<T> = BlogUtil.CoverToPage<T>
fun <T> Page<T>.toMyPageResult(): BlogUtil.CoverToPage<T> {
    return BlogUtil.coverToPage(this)
}
/**
 * @author ldd
 */
object BlogUtil {
    /**
     * 验证创建博客有效信息
     *
     * @param title      标题
     * @param content    正文
     * @param categoryId 分类id
     * @return 是否有效
     */
    fun validBlog(title: String?, content: String?, categoryId: Long?): Boolean {
        return !StringUtils.isBlank(title) && !StringUtils.isBlank(content) && categoryId != null
    }

    data class CoverToPage<T>(val list: List<T>,val page: SimplePage)
    fun <T> coverToPage(page: Page<T>): CoverToPage<T> {
        val content = page.content
        val simplePage = SimplePage()
        simplePage.pageSize = page.size
        simplePage.maxPage = page.totalPages
        simplePage.total = page.totalElements
        val pageNumber = page.pageable.pageNumber
        simplePage.currentPage = pageNumber
        val b = page.isFirst
        val paged = page.isLast
        simplePage.hasPrevious = b
        simplePage.isPaged = paged
        return  CoverToPage<T>(content,simplePage)
    }

    fun <T> coverToPageByCache(page: CachePage<T>): CoverToPage<T> {
        val content = page.content
        val simplePage = SimplePage()
        simplePage.pageSize = page.size
        simplePage.maxPage = page.totalPages
        simplePage.total = page.totalElements
        val pageNumber = page.pageable.pageNumber
        simplePage.currentPage = pageNumber
        val b = page.first
        val paged = page.last
        simplePage.hasPrevious = b
        simplePage.isPaged = paged
        return  CoverToPage(content,simplePage)
    }

    @JvmStatic
    fun getFirstAndLastDay(year: Int, month: Int, start: Boolean): Date? {
        if (year < 0 || month < 1) {
            return null
        }
        val cal = Calendar.getInstance()
        cal[Calendar.YEAR] = year
        cal[Calendar.MONTH] = month - 1
        val firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH)
        cal[Calendar.DAY_OF_MONTH] = firstDay
        if (start) {
            return cal.time
        }
        val lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH)
        cal[Calendar.DAY_OF_MONTH] = lastDay
        return cal.time
    }
}
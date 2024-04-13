package shop.itbug.ticket.model.api

import org.springframework.data.domain.Page


val <T> Page<T>.cache : CachePage<T> get() {
    val cacheSort = CacheSort(empty = this.sort.isEmpty,sorted = this.sort.isSorted, unsorted = this.sort.isUnsorted)
    return CachePage(
        content = this.content,
        empty = this.isEmpty,
        first = this.isFirst,
        last = this.isLast,
        number = this.number,
        numberOfElements = this.numberOfElements,
        pageable = CachePageable(
            offset = this.pageable.offset,
            pageSize = this.pageable.pageSize,
            pageNumber = this.pageable.pageNumber,
            paged = this.pageable.isPaged,
            sort = cacheSort,
            unpaged = this.pageable.isUnpaged
        ),
        size = this.size,
        sort = cacheSort,
        totalElements = this.totalElements,
        totalPages = this.totalPages

    )
}

data class CachePage<T> (
    val content: List<T>,
    val empty: Boolean,
    val first: Boolean,
    val last: Boolean,
    val number: Int,
    val numberOfElements: Int,
    val pageable: CachePageable,
    val size: Int,
    val sort: CacheSort,
    val totalElements: Long,
    val totalPages: Int
)

data class CachePageable (
    val offset: Long,
    val pageNumber: Int,
    val pageSize: Int,
    val paged: Boolean,
    val sort: CacheSort,
    val unpaged: Boolean
)

data class CacheSort (
    val empty: Boolean,
    val sorted: Boolean,
    val unsorted: Boolean
)
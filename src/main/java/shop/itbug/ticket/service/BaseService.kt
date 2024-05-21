package shop.itbug.ticket.service

import org.springframework.data.domain.Example
import org.springframework.data.domain.Page
import org.springframework.data.jpa.repository.JpaRepository
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.admin.model.getPageable
import shop.itbug.ticket.utils.toJson

abstract class BaseService<T> {

    abstract fun getDao(): JpaRepository<T, Long>

    fun saveEntity(entity: T & Any): T {
        return getDao().save(entity)
    }
    /**
     * 查找某个对象
     */
    fun findEntity(entity: T & Any): T? {
        val findBy = getDao().findBy(Example.of(entity)) { f -> f.first() }
        return if (findBy.isPresent) findBy.get() else null
    }

    /**
     * 判断对象是否存在
     */
    fun isExits(entity: T & Any) : Boolean {
        return getDao().exists(Example.of(entity))
    }

    fun findListByPage(pageMode: PageModel) : Page<T> {
        println(pageMode.toJson())
        return getDao().findAll(pageMode.getPageable())
    }

    /**
     * 查找全部
     */
    fun findAll() : List<T> {
        return getDao().findAll()
    }


    fun findById(id: Long): T? {
        return getDao().findById(id).orElse(null)
    }

}
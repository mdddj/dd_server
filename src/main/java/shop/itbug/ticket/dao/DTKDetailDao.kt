package shop.itbug.ticket.dao

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import shop.itbug.ticket.entry.system.DTKDetail

@Repository
interface DTKDetailDao : JpaRepository<DTKDetail, Long> {
    /**
     * 查找默认设置的或者未启用的
     *
     * @param select 是否默认
     * @return 类对象列表
     */
    fun findAllBySelectDefault(select: Boolean): List<DTKDetail>


    fun findBySelectDefault(select: Boolean) : DTKDetail?

    /**
     *
     * 根据appkey查找
     *
     * @param appkey 查找条件 传入appKey
     * @return 类对象
     */
    fun findByAppKey(appkey: String?): DTKDetail?
}
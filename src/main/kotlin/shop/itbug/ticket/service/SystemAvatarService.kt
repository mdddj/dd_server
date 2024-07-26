package shop.itbug.ticket.service

import org.springframework.stereotype.Service
import shop.itbug.ticket.entry.system.SystemAvator

/**
 * 操作图片资源的服务
 * @author 梁典典
 */
@Service
interface SystemAvatarService {
    /**
     * 根据图片类型统计总数
     * @param type  类型
     * @return 总数
     */
    fun count(type: Int): Long

    /**
     * 保存或者修改一个对象
     * @param obj   要保存或者修改的对象
     * @return  保存成功后的对象
     */
    fun save(obj: SystemAvator): SystemAvator

    /**
     * 根据类型查找全部图片
     * @param type  类型
     * @return  图片列表
     */
    fun findAllByType(type: Int): List<SystemAvator>
}
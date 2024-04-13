package shop.itbug.ticket.service.impl

import jakarta.annotation.Resource
import org.springframework.stereotype.Service
import shop.itbug.ticket.dao.SystemAvatarRepository
import shop.itbug.ticket.entry.system.SystemAvator
import shop.itbug.ticket.service.SystemAvatarService

/**
 * 系统图片资源实现
 *
 * @author eee
 */
@Service
class SystemAvatarServiceImpl : SystemAvatarService {
    @Resource
    private lateinit var systemAvatarRepository: SystemAvatarRepository

    /**
     * 根据图片类型统计总数
     *
     * @param type 类型
     */
    override fun count(type: Int): Long {
        return systemAvatarRepository.countByType(type)
    }

    /**
     * 保存或者修改一个对象
     *
     * @param obj 要保存或者修改的对象
     * @return 保存成功后的对象
     */
    override fun save(obj: SystemAvator): SystemAvator {
        return systemAvatarRepository.save(obj)
    }

    /**
     * 根据类型查找全部图片
     *
     * @param type 类型
     * @return 图片列表
     */
    override fun findAllByType(type: Int): List<SystemAvator> {
        return systemAvatarRepository.findAllByType(type)
    }
}
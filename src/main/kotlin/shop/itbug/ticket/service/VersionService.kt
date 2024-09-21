package shop.itbug.ticket.service

import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.controller.VersionController
import shop.itbug.ticket.entry.Version
import shop.itbug.ticket.entry.VersionPlatform
import shop.itbug.ticket.entry.project.Project

@Service
interface VersionService {
    /**
     * 保存一条数据
     *
     * @param version 版本数据
     * @return 保存后的
     */
    fun save(version: Version): Version

    /**
     * 查找某个平台下面的最新的一个版本
     *
     * @param platform 平台
     * @return 查找到的版本
     */
    fun findNewVersion(platform: String): Version?

    /**
     * 查询全部已上传发布版本
     * @param pageModel 分页
     * @param version 模糊查询条件
     * @return 返回结果
     */
    fun findAll(pageModel: PageModel, version: Version?): Page<Version>

    fun findAllByPlatform(platform: String): List<Version>

    fun delete(id: Long)

    fun findById(id: Long): Version

    fun findByExample(version: Version): Version

    fun findAllByExample(versions: Version): List<Version>

    fun findAllByProject(project: Project, platform: VersionPlatform): List<Version>

    fun saveAll(versionList: List<Version>): List<Version>

    fun removeAll()

    fun checkNewVersion(model : VersionController.CheckVersionModel) : Version?
}
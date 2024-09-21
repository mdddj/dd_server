package shop.itbug.ticket.service.impl

import cn.hutool.core.comparator.VersionComparator
import jakarta.annotation.Resource
import org.springframework.data.domain.Example
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.admin.model.getCurrentPage
import shop.itbug.ticket.controller.VersionController
import shop.itbug.ticket.dao.VersionDao
import shop.itbug.ticket.entry.Version
import shop.itbug.ticket.entry.VersionPlatform
import shop.itbug.ticket.entry.project.Project
import shop.itbug.ticket.entry.project.ProjectService
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.service.VersionService
import kotlin.jvm.optionals.getOrElse

@Service
class VersionServiceImpl : VersionService {

    @Resource
    private lateinit var versionDao: VersionDao

    @Resource lateinit var projectService: ProjectService

    /**
     * 保存一条数据
     *
     * @param version 版本数据
     * @return 保存后的
     */
    override fun save(version: Version): Version {
        return versionDao.save(version)
    }

    override fun findByExample(version: Version): Version {
        return versionDao.findOne(Example.of(version)).getOrElse { throw BizException("没有找到数据") }
    }

    override fun findAllByExample(versions: Version): List<Version> {
        return versionDao.findAll(Example.of(versions))
    }

    override fun findAllByProject(project: Project, platform: VersionPlatform): List<Version> {
        return versionDao.findAllByProjectAndPlatform(
            project, platform, Sort.by("createDate").descending()
        )
    }

    override fun saveAll(versionList: List<Version>): List<Version> {
        return versionDao.saveAll(versionList)
    }

    override fun removeAll() {
        versionDao.deleteAll()
    }

    override fun checkNewVersion(model: VersionController.CheckVersionModel): Version? {
        val project = projectService.findByName(model.appName) ?: throw BizException("未找到该项目:${model.appName}")
        val versions = versionDao.findAllByProjectAndPlatform(project,model.platform,Sort.by("createDate").descending())
        if(versions.isNotEmpty()){
            val lastNew = versions.first()
            val compare = VersionComparator.INSTANCE.compare(model.appVersion, lastNew.versionNumber)
            if(compare<0){
                ///有新版本
                return lastNew
            }
        }
        return null
    }

    override fun findById(id: Long): Version {
        return versionDao.findById(id).getOrElse { throw BizException("未找到数据.") }
    }

    /**
     * 查找某个平台下面的最新的一个版本
     *
     * @param platform 平台
     * @return 查找到的版本
     */
    override fun findNewVersion(platform: String): Version? {
        return versionDao.findFirstByPlatformOrderByCreateDateDesc(enumValueOf<VersionPlatform>(platform))
    }

    /**
     * 查询全部已上传发布版本
     *
     * @param pageModel 分页
     * @param version 模糊查询条件,可以为null
     * @return 返回结果
     */
    override fun findAll(pageModel: PageModel, version: Version?): Page<Version> {
        val pageRequest = PageRequest.of(
            pageModel.getCurrentPage(), pageModel.pageSize,
            Sort.Direction.DESC, "createDate"
        )
        if (version == null) {
            return versionDao.findAll(pageRequest)
        }
        return versionDao.findAll(Example.of(version), pageRequest)
    }

    override fun findAllByPlatform(platform: String): List<Version> {
        return versionDao.findAllByPlatform(enumValueOf(platform))
    }

    override fun delete(id: Long) {
        versionDao.deleteById(id)
    }
}
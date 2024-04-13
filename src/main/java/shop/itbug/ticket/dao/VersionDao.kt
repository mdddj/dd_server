package shop.itbug.ticket.dao

import jakarta.validation.constraints.NotBlank
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import shop.itbug.ticket.entry.Version
import shop.itbug.ticket.entry.VersionPlatform
import shop.itbug.ticket.entry.project.Project

@Repository
interface VersionDao : JpaRepository<Version, Long> {
    fun findFirstByPlatformOrderByCreateDateDesc(platform: @NotBlank(message = "请填写安装包平台") VersionPlatform): Version?
    fun findAllByPlatform(platform: VersionPlatform): List<Version>
    fun findAllByProjectAndPlatform(project: Project,platform: VersionPlatform,sort: Sort): List<Version>
}
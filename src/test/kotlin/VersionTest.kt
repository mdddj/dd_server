
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import shop.itbug.ticket.App
import shop.itbug.ticket.controller.VersionController
import shop.itbug.ticket.entry.Version
import shop.itbug.ticket.entry.VersionPlatform
import shop.itbug.ticket.entry.project.Project
import shop.itbug.ticket.entry.project.ProjectService
import shop.itbug.ticket.ex.log
import shop.itbug.ticket.service.VersionService
import java.time.ZoneId
import java.time.ZonedDateTime

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = [App::class])
@AutoConfigureMockMvc
@ExtendWith(SpringExtension::class)
open class VersionServiceTest {

    @Autowired private lateinit var versionService: VersionService
    @Autowired private lateinit var projectService: ProjectService

    @Test
    open fun testSaveNewVersion() {

        val testProject = projectService.findByName("test") ?: projectService.save(Project().apply { name = "test" })

        val version = Version().apply {
            title = "test"
            description = "bug fix"
            downloadUrl = "http://localhost:8080"
            platform = VersionPlatform.Android
            versionNumber = "1.0.0"
            project = testProject
            packageSize = 12234
            enable = true
            createDate = ZonedDateTime.now(ZoneId.systemDefault()).minusDays(1)
        }
        val version2 = Version().apply {
            title = "test"
            description = "bug fix"
            downloadUrl = "http://localhost:8080"
            platform = VersionPlatform.Android
            versionNumber = "1.0.1"
            project = testProject
            packageSize = 12234
            enable = true
            createDate = ZonedDateTime.now(ZoneId.systemDefault()).minusMinutes(1)
        }
        val version3 = Version().apply {
            title = "test"
            description = "bug fix"
            downloadUrl = "http://localhost:8080"
            platform = VersionPlatform.Android
            versionNumber = "1.1.0"
            project = testProject
            packageSize = 12234
            enable = true
            createDate =ZonedDateTime.now(ZoneId.systemDefault())
        }

        versionService.saveAll(listOf(version,version2,version3))

    }


    @Test
    fun hasNewVersion(){
        val newVersion = versionService.checkNewVersion(VersionController.CheckVersionModel(
            appName = "test","1.0.0",VersionPlatform.Android
        ))
        assert(newVersion != null)
        val newVersion2 = versionService.checkNewVersion(VersionController.CheckVersionModel(
            appName = "test","2.0.0",VersionPlatform.Android
        ))
        assert(newVersion2==null)
    }

    @Test
    fun clean(){
        versionService.removeAll()
        log().info("Removed all versions")
    }
}
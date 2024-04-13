package shop.itbug.ticket.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import shop.itbug.ticket.ex.log
import java.io.File




@ConfigurationProperties(prefix = "dd-server-config")
@Configuration
open class DdServerConfig {
    ///配置根目录
    lateinit var rootDir: String
    private final val userHomeDirectory = File(System.getProperty("user.home"))

    /**
     * 请使用  [DdServerConfig.whenRootDir] 函数
     */
     fun getRootDirFileFun(): File {
        val rootDirFile = File(userHomeDirectory.absolutePath + File.separator + rootDir)
        try {
            if (rootDirFile.exists().not()) {
                val createNewFile = rootDirFile.mkdirs()
                if (createNewFile) {
                    log().info("初始化配置文件目录成功 -> ${rootDirFile.path}")
                }
            }
            return rootDirFile
        } catch (e: Exception) {
            log().warn("创建配置目录失败:$e")
        }
        return rootDirFile
    }



    private fun whenRootDir(doHandle: (dir: File) -> Unit) {
        doHandle(getRootDirFileFun())
    }


}
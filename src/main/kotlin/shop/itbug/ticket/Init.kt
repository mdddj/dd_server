package shop.itbug.ticket

import cn.hutool.core.io.IoUtil
import jakarta.annotation.Resource
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.core.io.ResourceLoader
import org.springframework.stereotype.Component
import shop.itbug.ticket.constant.TextConstant
import shop.itbug.ticket.entry.ResourcesCategory
import shop.itbug.ticket.entry.Role
import shop.itbug.ticket.entry.blog.Tag
import shop.itbug.ticket.entry.system.SystemAvator
import shop.itbug.ticket.ex.log
import shop.itbug.ticket.service.*
import shop.itbug.ticket.service.blog.BlogService
import shop.itbug.ticket.service.blog.CategoryService
import shop.itbug.ticket.service.blog.TagService
import java.io.IOException
import java.nio.charset.StandardCharsets

///
@Component
class InitAppRunner : ApplicationRunner {
    @Resource
    private lateinit var userService: UserService

    @Resource
    private lateinit var categoryService: CategoryService

    @Resource
    private lateinit var blogService: BlogService

    @Resource
    private lateinit var tagService: TagService

    @Resource
    private lateinit var resourceLoader: ResourceLoader

    @Resource
    private lateinit var roleService: RoleService

    @Resource
    private lateinit var resourcesCategoryService: ResourcesCategoryService

    @Resource
    private lateinit var systemAvatarService: SystemAvatarService

    @Throws(IOException::class)
    override fun run(args: ApplicationArguments) {
        roleInit()
        initAdminAccount() // 初始化管理员账号
        initCategory() // 初始化分类
        genderTestBlogs() // 初始化测试博客
        rcInit()
        initPics()
        checkAdminAccount() //
    }

    /**
     * 初始化图片资源
     */
    private fun initPics() {
        val count = systemAvatarService.count(1)
        if (count == 0L) {
            systemAvatarService.save(
                SystemAvator(
                    1,
                    "https://static.saintic.com/picbed/huang/2021/08/17/1629169985486.jpg"
                )
            )
            systemAvatarService.save(
                SystemAvator(
                    1,
                    "https://static.saintic.com/picbed/huang/2021/08/17/1629169985484.jpg"
                )
            )
            systemAvatarService.save(
                SystemAvator(
                    1,
                    "https://static.saintic.com/picbed/huang/2021/08/17/1629170003159.gif"
                )
            )
            systemAvatarService.save(
                SystemAvator(
                    1,
                    "https://static.saintic.com/picbed/huang/2021/08/17/1629170008448.png"
                )
            )
            systemAvatarService.save(
                SystemAvator(
                    1,
                    "https://static.saintic.com/picbed/huang/2021/08/17/1629170012855.png"
                )
            )
            systemAvatarService.save(
                SystemAvator(
                    1,
                    "https://static.saintic.com/picbed/huang/2021/08/17/1629170016884.png"
                )
            )
        }
    }

    private fun rcInit() {
        val count = resourcesCategoryService.count()
        if (count == 0L) {
            // 面基经历
            val resourcesCategory = ResourcesCategory()
            resourcesCategory.name = "典典面基经历"
            resourcesCategory.description = "分享一些面基发生有趣的事"
            resourcesCategory.logo = "https://i.loli.net/2021/07/21/yvDmkOLniR4sWu6.png"
            resourcesCategoryService.save(resourcesCategory)
        }
    }

    /**
     * 权限初始化
     * 将管理员的账号设置为管理员权限
     */
    private fun roleInit() {
        if (roleService.findRoleByName("admin") == null) {
            roleService.save(Role.createRole("admin", "超级管理员"))
        }

    }


    /**
     * 初始化管理员账号
     */
    private fun initAdminAccount() {
        val adminAccounts = userService.findUsersByType(100)
        if (adminAccounts.isEmpty()) {
            val user = userService.createAdminAccount("admin", "123456", "")
            roleService.addRole(user) { it.name == "admin" }
            log().info("管理员账号创建成功,用户名:admin,密码:123456")
        }

    }


    /**
     * 检测管理员账号
     */
    private fun checkAdminAccount() {
        val findUsersByType = userService.findUsersByType(100)
        if (findUsersByType.isNotEmpty()) {
            findUsersByType.forEach {
                if (it.hasAdminRole().not()) {
                    log().warn("${it.getShowName()} 此账号没有配置管理员账号,即将自动添加admin权限")
                    roleService.userAddRole(it.id!!, "admin")
                }
            }
        }
    }


    /**
     * 初始化分类
     */
    private fun initCategory() {
        categoryService.create("Flutter", TextConstant.flutterIntro, TextConstant.flutterLogo)
        categoryService.create("Java", TextConstant.javaIntro, TextConstant.javaLogo)
        categoryService.create("Kotlin", TextConstant.kotlinIntro, TextConstant.kotlinLogo)
    }

    /**
     * 初始化测试博客文章
     */
    @Throws(IOException::class)
    private fun genderTestBlogs() {
        if (blogService.isEmpty) {
            val flutter = categoryService.select("Flutter")
            val java = categoryService.select("Java")
            val kotlin = categoryService.select("Kotlin")
            flutter.id?.let {
                blogService.create(
                    "compose 桌面开发入门之输入框使用",
                    getBlog("blog1"),
                    it,
                    "test1",
                    getTags("compose", "android"), ""
                )
            }
            java.id?.let {
                blogService.create(
                    "compose 桌面开发入门之图片资源使用", getBlog("blog2"),
                    it, "", getTags("compose", "android"), ""
                )
            }
            flutter.id?.let {
                blogService.create(
                    "Flutter 另一种方法实现 RouteAware 页面跳转生命周期",
                    getBlog("blog3"),
                    it,
                    "",
                    getTags("flutter", "路由"),
                    ""
                )
            }
            kotlin.id?.let {
                blogService.create(
                    "Android Kotlin 加载一张网络图片", getBlog("blog4"),
                    it, "", getTags("kotlin", "图片"), ""
                )
            }
            kotlin.id?.let {
                blogService.create(
                    "学习kotlin过程中解决无法使用sp.edit{}语法糖问题",
                    getBlog("blog5"),
                    it,
                    "",
                    getTags("kotlin", "语法"), ""
                )
            }
        }
    }

    /// 获取博客内容
    @Throws(IOException::class)
    private fun getBlog(fileName: String): String {
        return IoUtil.read(
            resourceLoader.getResource("classpath:blogs/$fileName.md").inputStream,
            StandardCharsets.UTF_8
        )
    }

    /// 创建标签
    private fun getTags(vararg strings: String): HashSet<Tag> {
        val hashSet = HashSet<Tag>()
        for (string in strings) {
            hashSet.add(Tag(string))
        }
        return tagService.save(hashSet)
    }


}
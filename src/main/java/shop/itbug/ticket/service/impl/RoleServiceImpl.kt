package shop.itbug.ticket.service.impl

import jakarta.annotation.Resource
import org.springframework.data.domain.Example
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import shop.itbug.ticket.dao.RoleDao
import shop.itbug.ticket.entry.Role
import shop.itbug.ticket.entry.User
import shop.itbug.ticket.ex.log
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.exception.ResultDialogType
import shop.itbug.ticket.service.AddRoleBy
import shop.itbug.ticket.service.RoleService
import shop.itbug.ticket.service.UserService

@Service
class RoleServiceImpl : RoleService {
    @Resource
    private lateinit var roleDao: RoleDao

    @Resource
    private lateinit var userService: UserService

    /**
     * 添加一个权限
     *
     * @param role 权限对象
     * @return 保存后的对象
     */
    override fun save(role: Role): Role {
        if(role.id!=null){
            return roleDao.save(role)
        }
        val find = roleDao.findOne(Example.of(Role().apply { name = role.name })).orElse(null)
        if(find!=null){
             throw BizException("已存在[${role.name}]权限")
        }
        return roleDao.save(role)
    }

    /**
     * 查找用户的权限列表
     *
     * @param userId 用户id
     * @return 用户所拥有的权限列表
     */
    override fun getRolesByUserId(userId: Long): List<Role> {
        return ArrayList(userService.findByUserId(userId)?.roles ?: emptySet())
    }

    /**
     * 给一个用户添加一个权限
     *
     * @param userId   用户id
     * @param roleName 权限名字
     */
    override fun userAddRole(userId: Long, roleName: String) {
        var user = userService.findByUserId(userId) ?: throw BizException("添加权限失败,用户未找到",ResultDialogType.Dialog)
        val role = roleDao.findRoleByName(roleName) ?: throw BizException("添加权限失败,未找到权限",ResultDialogType.Dialog)
        val roles = user.roles ?: mutableSetOf()
        roles.add(role)
        user.roles = roles
        user = userService.save(user)
        val users = role.users ?: mutableSetOf()
        users.add(user)
        role.users = users
        roleDao.save(role)
        log().info("$userId 添加权限成功 $roleName")
    }

    /**
     * 根据name字段查找一个role对象
     *
     * @param name 权限名字
     * @return role 对象
     */
    override fun findRoleByName(name: String): Role? {
        return roleDao.findRoleByName(name)
    }

    override fun findAll(): List<Role> {
        return roleDao.findAll()
    }

    override fun findAllByPage(page: Int, pageSize: Int): Page<Role> {
        return roleDao.findAll(PageRequest.of(page, pageSize))
    }

    override fun delete(id: Long) {
        roleDao.deleteById(id)
    }

    override fun findBy(role: Role): Role? {
        return roleDao.findOne(Example.of(role)).get()
    }

    override fun addRole(user: User, addRoleBy: AddRoleBy) {
        user.id ?:throw BizException("找不到用户")
        val all = findAll()
         all.find { addRoleBy(it) }?.let {
             userAddRole(user.id!!,it.name)
         }
    }
}
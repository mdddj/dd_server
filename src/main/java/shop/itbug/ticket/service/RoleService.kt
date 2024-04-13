package shop.itbug.ticket.service

import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import shop.itbug.ticket.entry.Role
import shop.itbug.ticket.entry.User

typealias AddRoleBy = ( role: Role) -> Boolean
@Service
interface RoleService {
    /**
     * 添加一个权限
     *
     * @param role 权限对象
     * @return 保存后的对象
     */
    fun save(role: Role): Role

    /**
     * 查找用户的权限列表
     *
     * @param userId 用户id
     * @return 用户所拥有的权限列表
     */
    fun getRolesByUserId(userId: Long): List<Role>

    /**
     * 给一个用户添加一个权限
     *
     * @param userId   用户id
     * @param roleName 权限名字
     */
    fun userAddRole(userId: Long, roleName: String)

    /**
     * 根据name字段查找一个role对象
     * @param name  权限名字
     * @return  role 对象
     */
    fun findRoleByName(name: String): Role?


    /**
     * 获取全部权限定义
     */
    fun findAll() : List<Role>


    /**
     * 分页查找
     */
    fun findAllByPage(page: Int,pageSize: Int): Page<Role>

    /**
     * 删除某个权限
     */
    fun delete(id: Long)

    /**
     * 条件查找
     */
    fun findBy(role: Role) : Role?


    fun addRole(user: User,addRoleBy: AddRoleBy)

}
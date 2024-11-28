package shop.itbug.ticket.entry.directory

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
interface DirectoryService {
    fun findAll(): List<DirectoryDto>
    fun save(entity: DocDirectory): DocDirectory
    fun deleteById(id: Long)
    fun findById(id: Long): DocDirectory?
    fun findAllByPage(pageable: Pageable): Page<DocDirectory>
    fun saveDirectory(directoryDto: DirectoryDto,parentDirectory: DocDirectory? = null): DocDirectory

    /**
     * 获取全部的博客
     */
    fun findAllRoot(): List<DirectoryDto>

    /**
     * 删除某个博客记录
     */
    fun deleteByName(rootName: String)

    /**
     * 根据名称查找博客
     */
    fun findRootByName(name: String): DirectoryDto
}
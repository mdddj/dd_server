package shop.itbug.ticket.entry.directory

import jakarta.annotation.Resource
import jakarta.transaction.Transactional
import org.springframework.cache.annotation.CacheEvict
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.exception.CommonEnum
import shop.itbug.ticket.service.redis.RedisKeys

@Service
class DirectoryServiceImpl : DirectoryService {
    @Resource
    lateinit var directoryRepository: DirectoryRepository

    @Resource
    lateinit var markdownFileRepository: MarkdownRepository
    override fun findAll(): List<DocDirectory> {
        return directoryRepository.findAll()
    }

    override fun save(entity: DocDirectory): DocDirectory {
        return directoryRepository.save(entity)
    }

    override fun deleteById(id: Long) {
        directoryRepository.deleteById(id)
    }

    override fun findById(id: Long): DocDirectory? {
        return directoryRepository.findByIdOrNull(id)
    }

    override fun findAllByPage(pageable: Pageable): Page<DocDirectory> {
        return directoryRepository.findAll(pageable)
    }

    @Transactional
    @CacheEvict(RedisKeys.BLOG_KEY + "statistics", allEntries = true)
    override fun saveDirectory(directoryDto: DirectoryDto, parentDirectory: DocDirectory?): DocDirectory {
        if (parentDirectory == null) {
            //验证是否存在
            val name = directoryDto.name
            val find = directoryRepository.findByNameAndParentIsNull(name)
            if (find != null) {
                throw BizException("$name 已存在")
            }
        }

        // 将 DTO 转换为目录实体类
        val directory = DocDirectory().apply {
            name = directoryDto.name
            parent = parentDirectory
            introduce = directoryDto.introduce
        }
        // 保存当前目录
        val savedDirectory = directoryRepository.save(directory)

        // 保存该目录下的文件
        val files = directoryDto.files.map { MarkdownFile.create(it.name, it.content, savedDirectory, it.filename) }
        markdownFileRepository.saveAll(files)

        // 递归保存子目录
        val c = directoryDto.children.map { childDto ->
            saveDirectory(childDto, savedDirectory)  // 递归
        }
        if (parentDirectory != null) {
            parentDirectory.children = c
            directoryRepository.save(parentDirectory)//设置一下子节点
        }
        return savedDirectory
    }

    override fun findAllRoot(): List<DocDirectory> {
        return directoryRepository.findAllByParentIsNull()
    }

    @CacheEvict(RedisKeys.BLOG_KEY + "statistics", allEntries = true)
    override fun deleteByName(rootName: String) {
        val directory =
            directoryRepository.findByNameAndParentIsNull(rootName) ?: throw BizException(CommonEnum.NOT_FOUND)
        directoryRepository.delete(directory)

    }

    override fun findRootByName(name: String): DocDirectory {
        return directoryRepository.findByNameAndParentIsNull(name) ?: throw BizException(CommonEnum.NOT_FOUND)
    }
}
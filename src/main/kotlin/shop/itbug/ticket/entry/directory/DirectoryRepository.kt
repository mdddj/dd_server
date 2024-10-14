package shop.itbug.ticket.entry.directory


import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DirectoryRepository : JpaRepository<DocDirectory,Long>{

    /**
     * 查找全部的root项目
     */
    fun findAllByParentIsNull(): List<DocDirectory>

    /**
     * 根据名称查找root博客
     */
    fun findByNameAndParentIsNull(name: String): DocDirectory?
}

@Repository
interface MarkdownRepository : JpaRepository<MarkdownFile,Long>
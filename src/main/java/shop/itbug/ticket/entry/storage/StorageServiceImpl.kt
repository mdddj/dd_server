package shop.itbug.ticket.entry.storage

import cn.hutool.core.date.DateUtil
import cn.hutool.core.io.FileUtil
import cn.hutool.core.io.file.FileNameUtil
import jakarta.annotation.Resource
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import shop.itbug.ticket.entry.FileInfo
import shop.itbug.ticket.entry.User
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.service.FileInfoService
import shop.itbug.ticket.service.MinioService
import shop.itbug.ticket.service.ResourcesCategoryService
import shop.itbug.ticket.service.UserService
import shop.itbug.ticket.utils.FileManagerWrapper
import shop.itbug.ticket.utils.ImageCheck
import java.awt.Dimension
import java.io.IOException
import javax.imageio.ImageIO


@Service
class StorageServiceImpl :  StorageService {


    @Resource
    lateinit var storageRepository: StorageRepository


    @Resource
    lateinit var userService: UserService

    @Resource
    lateinit var resourcesCategoryService: ResourcesCategoryService


    override fun findAll(): List<Storage> {
        return storageRepository.findAll()
    }

    override fun save(entity: Storage): Storage {
        return storageRepository.save(entity)
    }

    override fun deleteById(id: Long) {
        storageRepository.deleteById(id)
    }

    override fun findById(id: Long): Storage? {
        return storageRepository.findByIdOrNull(id)
    }

    override fun findAllByPage(pageable: Pageable): Page<Storage> {
        return storageRepository.findAll(pageable)
    }



}
package shop.itbug.ticket.entry.storage

import jakarta.annotation.Resource
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import shop.itbug.ticket.service.ResourcesCategoryService
import shop.itbug.ticket.service.UserService


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
package ru.isokolov.demo.version.service

import jakarta.persistence.EntityManager
import mu.KotlinLogging
import org.springframework.stereotype.Service
import ru.isokolov.demo.version.model.Block
import ru.isokolov.demo.version.respository.BlockRepository

@Service
class BlockService(
    private val blockRepository: BlockRepository,
    private val entityManager: EntityManager
) {
    private val logger = KotlinLogging.logger {}

    fun waitBlock(id: String) {
        logger.info { "Start of wait $id" }
        while(true) {
            val block = getOrCreateThreadBlock(thread = id)
            if (block.isBlocked) {
                Thread.sleep(1_000)
            } else {
                break
            }
            entityManager.detach(block)
        }
        logger.info { "End of wait $id" }
    }

    private fun getOrCreateThreadBlock(thread: String): Block =
        blockRepository.findById(thread).orElse(null)
            ?: blockRepository.save(Block(id = thread, isBlocked = false))
}
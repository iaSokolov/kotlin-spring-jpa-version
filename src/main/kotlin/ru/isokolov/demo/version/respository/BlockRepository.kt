package ru.isokolov.demo.version.respository

import org.springframework.data.jpa.repository.JpaRepository
import ru.isokolov.demo.version.model.Block

interface BlockRepository: JpaRepository<Block, String>
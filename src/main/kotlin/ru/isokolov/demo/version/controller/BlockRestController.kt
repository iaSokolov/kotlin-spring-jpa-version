package ru.isokolov.demo.version.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.isokolov.demo.version.service.BlockService

@RestController
@RequestMapping(value = ["/block/"])
class BlockRestController(
    private val service: BlockService,
) {
    @GetMapping("wait/{id}")
    fun waitBlock(@PathVariable id: String): ResponseEntity<Any> =
        service
            .waitBlock(id = id)
            .run { return ResponseEntity(HttpStatus.OK) }
}




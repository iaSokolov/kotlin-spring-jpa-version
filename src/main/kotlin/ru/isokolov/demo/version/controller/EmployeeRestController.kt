package ru.isokolov.demo.version.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.isokolov.demo.version.dto.EmployeeDto
import ru.isokolov.demo.version.service.EmployeeService

@RestController
@RequestMapping(value = ["/employee"])
class EmployeeRestController(
    private val service: EmployeeService,
) {
    @GetMapping("/{id}")
    fun get(@PathVariable id: String): ResponseEntity<EmployeeDto> =
        service.get(id = id)
            ?.let { ResponseEntity(EmployeeDto(id = it.id, mainId = it.mainId), HttpStatus.OK) }
            ?: ResponseEntity(HttpStatus.NOT_FOUND)

    @PostMapping
    fun create(@RequestBody requestEmployee: EmployeeDto): ResponseEntity<EmployeeDto> =
        service.create(requestEmployee)
            .let { ResponseEntity(EmployeeDto(id = it.id, mainId = it.mainId), HttpStatus.CREATED) }

    @PatchMapping
    fun update(@RequestBody requestEmployee: EmployeeDto): ResponseEntity<EmployeeDto> =
        service.update(requestEmployee)
            .let { ResponseEntity(EmployeeDto(id = it.id, mainId = it.mainId), HttpStatus.CREATED) }
}




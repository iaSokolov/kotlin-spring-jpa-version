package ru.isokolov.demo.version.service

import jakarta.transaction.Transactional
import mu.KotlinLogging
import org.springframework.stereotype.Service
import ru.isokolov.demo.version.dto.EmployeeDto
import ru.isokolov.demo.version.model.Employee
import ru.isokolov.demo.version.respository.EmployeeRepository

@Service
class EmployeeService(
    private val repository: EmployeeRepository,
    private val blockService: BlockService,
) {
    val logger = KotlinLogging.logger {}

    fun get(id: String): Employee? = repository.findById(id).orElse(null)

    @Transactional
    fun create(requestEmployee: EmployeeDto): Employee =
        get(id = requestEmployee.id)
            ?.let { throw IllegalArgumentException("Employee ${it.id} already exists") }
            ?: repository.save(Employee(id = requestEmployee.id, mainId = requestEmployee.mainId))

    @Transactional
    fun update(requestEmployee: EmployeeDto): Employee {
        logger.info { "Start update employee ${requestEmployee.id}" }

        val employee = get(id = requestEmployee.id)
            ?: throw IllegalArgumentException("Employee ${requestEmployee.id} not found")

        blockService.waitBlock(id = "employee")

        employee.mainId = requestEmployee.mainId
        val updateEmployee = repository.save(employee)

        logger.info { "Complete update employee ${employee.id} successful" }
        return updateEmployee
    }
}
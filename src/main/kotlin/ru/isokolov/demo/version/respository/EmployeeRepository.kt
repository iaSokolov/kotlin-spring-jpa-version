package ru.isokolov.demo.version.respository

import org.springframework.data.jpa.repository.JpaRepository
import ru.isokolov.demo.version.model.Employee

interface EmployeeRepository : JpaRepository<Employee, String>
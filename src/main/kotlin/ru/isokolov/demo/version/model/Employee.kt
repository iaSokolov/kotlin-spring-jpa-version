package ru.isokolov.demo.version.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "employee")
class Employee(
    @Id
    @Column(name = "employee_id", length = 10)
    var id: String,

    @Column(name = "main_employee_id", nullable = false, length = 10)
    var mainId: String,
)
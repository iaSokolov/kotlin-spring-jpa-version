package ru.isokolov.demo.version.model

import jakarta.persistence.*

@Entity
@Table(name = "block")
class Block(
    @Id
    @Column(name = "id")
    val id: String,

    @Column(name = "block", nullable = false)
    val isBlocked: Boolean,
)
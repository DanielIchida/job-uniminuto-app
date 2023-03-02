package com.uniminuto.models

import java.time.LocalDateTime

data class Package(
    val id: Int,
    val code: String,
    val status: State,
    val dateTime: LocalDateTime
)

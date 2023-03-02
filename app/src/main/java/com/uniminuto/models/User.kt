package com.uniminuto.models

data class User(
    val id: Int,
    val name: String,
    val password: String,
    val rol: Roles
)

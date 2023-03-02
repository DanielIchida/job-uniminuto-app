package com.uniminuto.models

data class Provider(
    val id: Int,
    val nif: String,
    val name: String,
    val lastName: String,
    val phone: String,
    val address: String,
    val email: String
)

package com.example.serversmanagement.data.dto

data class CreateInstanceDto(
    val ipaddress: String,
    val memory: Int,
    val name: String,
    val status: Boolean,
    val type: String,
    val user: String
)

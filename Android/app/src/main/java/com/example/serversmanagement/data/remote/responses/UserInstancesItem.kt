package com.example.serversmanagement.data.remote.responses

data class UserInstancesItem(
    val id: String,
    val ipaddress: String,
    val memory: Int,
    val name: String,
    val status: Boolean,
    val type: String,
    val user: String
)
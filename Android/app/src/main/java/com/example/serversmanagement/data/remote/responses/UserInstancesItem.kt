package com.example.serversmanagement.data.remote.responses

import java.util.*

data class UserInstancesItem(
    val id: UUID,
    val ipaddress: String,
    val memory: Int,
    val name: String,
    val status: Boolean,
    val type: String,
    val user: String
)
package com.example.serversmanagement.data.models

import java.util.*

data class ServerListEntry(
    val id: UUID,
    val name: String,
    val ipaddress: String,
    val memory: Int,
    var status: Boolean,
    val type: String
)

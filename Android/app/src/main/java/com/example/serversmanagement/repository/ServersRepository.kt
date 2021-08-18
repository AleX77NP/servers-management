package com.example.serversmanagement.repository

import com.example.serversmanagement.data.dto.CreateInstanceDto
import com.example.serversmanagement.data.remote.responses.Message
import com.example.serversmanagement.data.remote.responses.UserInstances
import com.example.serversmanagement.util.Resource
import java.util.*

interface ServersRepository {
    /* suspend fun getMyInstances(user: String): Resource<UserInstances>
    suspend fun createServerInstance(instance: CreateInstanceDto) : Resource<Message>
    suspend fun updateInstanceStatus(id: UUID): Resource<Message>
    suspend fun deleteInstanceStatus(id: UUID): Resource<Unit>
    */
    // For testing use this as abstraction
}
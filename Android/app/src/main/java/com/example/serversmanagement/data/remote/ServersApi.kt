package com.example.serversmanagement.data.remote

import com.example.serversmanagement.data.dto.CreateInstanceDto
import com.example.serversmanagement.data.remote.responses.Message
import com.example.serversmanagement.data.remote.responses.UserInstances
import retrofit2.http.*
import java.util.*

interface ServersApi {

    @GET("api/servers/instances/{user}")
    suspend fun getMyInstances(
        @Path("user") user: String
    ) : UserInstances

    @POST("api/servers/instances/add")
    suspend fun createServerInstance(
        @Body instance: CreateInstanceDto
    ) : Message

    @PUT("api/servers/instances/update/{id}")
    suspend fun updateInstanceStatus(
        @Path("id") id: UUID
    ): Message

    @DELETE("api/servers/instances/{id}")
    suspend fun deleteInstance(
        @Path("id") id: UUID
    )
}
package com.example.serversmanagement.repository

import com.example.serversmanagement.data.dto.CreateInstanceDto
import com.example.serversmanagement.data.remote.ServersApi
import com.example.serversmanagement.data.remote.responses.Message
import com.example.serversmanagement.data.remote.responses.UserInstances
import com.example.serversmanagement.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import java.util.*
import javax.inject.Inject
import kotlin.Exception

@ActivityScoped
class ServersRepositoryImpl @Inject constructor(
    private val api: ServersApi
) {
    suspend fun getMyInstances(user: String): Resource<UserInstances> {
        val response = try {
            api.getMyInstances(user)
        } catch (e: Exception) {
            return Resource.Error("Couldn't get user's instances")
        }
        return Resource.Success(response)
    }

    suspend fun createServerInstance(instance: CreateInstanceDto) : Resource<Message> {
        val response = try {
            api.createServerInstance(instance)
        } catch (e: Exception) {
            return Resource.Error("Couldn't create new instance.")
        }
        return Resource.Success(response)
    }

    suspend fun updateInstanceStatus(id: UUID): Resource<Message> {
        val response = try {
            api.updateInstanceStatus(id)
        } catch (e: Exception) {
            return Resource.Error("Couldn't update status of this instance.")
        }
        return Resource.Success(response)
    }

    suspend fun deleteInstanceStatus(id: UUID): Resource<Unit> {
        val response = try {
            api.deleteInstance(id)
        } catch (e: Exception) {
            return Resource.Error("Couldn't delete instance.")
        }
        return Resource.Success(Unit)
    }


}
package com.example.serversmanagement.serverlist

import android.app.Application
import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.serversmanagement.R
import com.example.serversmanagement.data.dto.CreateInstanceDto
import com.example.serversmanagement.data.models.ServerListEntry
import com.example.serversmanagement.repository.ServersRepositoryImpl
import com.example.serversmanagement.util.Constants.DATASTORE_NAME
import com.example.serversmanagement.util.Constants.WEB_SERVER
import com.example.serversmanagement.util.Resource
import com.example.serversmanagement.util.UserInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.math.abs

@HiltViewModel
class ServerListViewModel @Inject constructor(
    private val repository: ServersRepositoryImpl,
    application: Application
): AndroidViewModel(application) {

    var serversList = mutableStateOf<List<ServerListEntry>>(listOf())
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var message = mutableStateOf("")

    var loggedInUser = mutableStateOf("")

    private val userInfo: UserInfo = UserInfo(application.baseContext)


    init {
        loadUserAndData()
    }

    private fun loadUserAndData() {
        viewModelScope.launch {
            val user = userInfo.getUser().collect {
                loggedInUser.value = it
                loadInstances()
            }

        }
    }

    fun loadInstances() {
        viewModelScope.launch {
            isLoading.value = true
            when(val result = repository.getMyInstances(loggedInUser.value)) {
                is Resource.Success -> {
                    val listEntries = result.data!!.mapIndexed { _, userInstancesItem ->
                        ServerListEntry(userInstancesItem.id, userInstancesItem.name, userInstancesItem.ipaddress,
                        userInstancesItem.memory, userInstancesItem.status, userInstancesItem.type)
                    }
                    serversList.value = listEntries
                    isLoading.value = false
                    loadError.value = ""
                }
                is Resource.Error -> {
                    loadError.value = result.message.toString()
                    isLoading.value = false
                }
            }
        }
    }

    fun createServerInstance(instance: CreateInstanceDto) {
        viewModelScope.launch( Dispatchers.Default) {
            when(val result = repository.createServerInstance(instance)) {
                is Resource.Success -> {
                    message.value = "Instance created."
                }
                is Resource.Error -> {
                    loadError.value = result.message.toString()
                }
            }
        }
    }

    fun deleteInstance(id: UUID) {
        viewModelScope.launch(Dispatchers.Default) {
            when(val result = repository.deleteInstance(id)) {
                is Resource.Success -> {
                    serversList.value = serversList.value.filter { item ->
                        item.id != id
                    }
                }
                is Resource.Error -> {
                    loadError.value = result.message.toString()
                }
            }
        }
    }

    fun updateInstanceStatus(id: String) {
        val uid = UUID.fromString(id)
        viewModelScope.launch(Dispatchers.Default) {
            when(val result = repository.updateInstanceStatus(uid)) {
                is Resource.Success -> {
                    message.value = "Instance updated."
                    loadInstances()
                }
                is Resource.Error -> {
                    loadError.value = result.message.toString()
                }
            }
        }
    }

    fun resetMessage() {
        message.value = ""
    }

    fun serverTypeImage(type: String): Int {
        return if (type == WEB_SERVER) R.drawable.webserver else R.drawable.dbserver
    }

    fun calculateMemory(num: Float): Int {
        val points = listOf(2,4,8,16,32,64,128)
        var mem = 2
        var difference = 128
        points.forEach{ point ->
            run {
                if (abs(point - num) < difference) {
                    difference = abs(point - num).toInt()
                    mem = point
                }
            }
        }
        return mem
    }

}
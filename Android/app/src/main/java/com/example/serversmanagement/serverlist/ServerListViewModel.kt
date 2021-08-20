package com.example.serversmanagement.serverlist

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.serversmanagement.R
import com.example.serversmanagement.data.models.ServerListEntry
import com.example.serversmanagement.repository.ServersRepositoryImpl
import com.example.serversmanagement.util.Constants.WEB_SERVER
import com.example.serversmanagement.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ServerListViewModel @Inject constructor(
    private val repository: ServersRepositoryImpl
): ViewModel() {

    var serversList = mutableStateOf<List<ServerListEntry>>(listOf())
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var message = mutableStateOf("")

    init {
        loadInstances()
    }

    fun loadInstances() {
        viewModelScope.launch {
            isLoading.value = true
            when(val result = repository.getMyInstances("milanovicaleX77@gmail.com")) {
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

}
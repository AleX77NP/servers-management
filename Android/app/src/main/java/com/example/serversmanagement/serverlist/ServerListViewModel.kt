package com.example.serversmanagement.serverlist

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.serversmanagement.R
import com.example.serversmanagement.data.models.ServerListEntry
import com.example.serversmanagement.repository.ServersRepositoryImpl
import com.example.serversmanagement.util.Constants.WEB_SERVER
import com.example.serversmanagement.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ServerListViewModel @Inject constructor(
    private val repository: ServersRepositoryImpl
): ViewModel() {

    var serversList = mutableStateOf<List<ServerListEntry>>(listOf())
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)

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
                    serversList.value += listEntries
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

    fun serverTypeImage(type: String): Int {
        return if (type == WEB_SERVER) R.drawable.webserver else R.drawable.dbserver
    }

}
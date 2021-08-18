package com.example.serversmanagement.serverlist

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun ServerList(
    navController: NavController,
    viewModel: ServerListViewModel = hiltViewModel()
) {
    val serverList by remember { viewModel.serversList }
    val loadError by remember { viewModel.loadError }
    val isLoading by remember { viewModel.isLoading }

    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ) {
        items(serverList) { instance ->
            InstanceItem(entry = instance, navController = navController)
        }
    }
}
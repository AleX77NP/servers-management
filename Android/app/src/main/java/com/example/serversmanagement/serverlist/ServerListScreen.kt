package com.example.serversmanagement.serverlist

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.serversmanagement.R

@Composable
fun ServerListScreen(
    navController: NavController
) {
    Title(text = stringResource(id = R.string.list_title))
}
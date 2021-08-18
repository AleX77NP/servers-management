package com.example.serversmanagement.serverlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.serversmanagement.R

@Composable
fun ServerListScreen(
    navController: NavController
) {
    Column {
        Title(text = stringResource(id = R.string.list_title))
        Spacer(modifier = Modifier.height(14.dp))
        ServerList(navController = navController)
    }
}
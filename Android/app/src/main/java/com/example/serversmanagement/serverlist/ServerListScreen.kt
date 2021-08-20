package com.example.serversmanagement.serverlist

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.serversmanagement.R
import com.example.serversmanagement.util.customFontFamily

@Composable
fun ServerListScreen(
    navController: NavController
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally
    , modifier = Modifier.fillMaxWidth()) {
        Title(text = stringResource(id = R.string.list_title))
        Button(onClick = {
                         navController.navigate("add_instance_screen")
        },
            modifier = Modifier.padding(8.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent
            )
        ) {
            Text(text = "Add instance +",
                fontFamily = customFontFamily,
                fontSize = 18.sp
            )
        }
        Spacer(modifier = Modifier.height(14.dp))
        ServerList(navController = navController)
    }
}
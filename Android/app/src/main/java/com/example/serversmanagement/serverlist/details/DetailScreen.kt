package com.example.serversmanagement.serverlist.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.serversmanagement.serverlist.ServerListViewModel
import com.example.serversmanagement.util.customFontFamily

@Composable
fun DetailScreen(
    name: String,
    ipaddress: String,
    status: Boolean,
    memory: Int,
    type: String,
    id: String,
    viewModel: ServerListViewModel = hiltViewModel()
) {
    var loadError by remember {
        viewModel.loadError
    }
    var message by remember {
        viewModel.message
    }
    var currentS by remember {
        mutableStateOf(status)
    }
    LaunchedEffect(key1 = message, block = {
        if(message == "Instance updated.") {
            currentS = !currentS
        }
        if(message.isNotEmpty()) {
            viewModel.resetMessage()
        }
    })


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 50.dp)
            .padding(top = 100.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = viewModel.serverTypeImage(type)),
            contentDescription = "Server type image",
            modifier = Modifier
                .size(180.dp)
                .padding(bottom = 40.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        ) {
            Text(text = "Instance name",
                fontFamily = customFontFamily,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold)
            Text(text = name, fontFamily = customFontFamily, fontSize = 18.sp)
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        ) {
            Text(text = "Instance IP address",
                fontFamily = customFontFamily,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold)
            Text(text = ipaddress, fontFamily = customFontFamily, fontSize = 18.sp)
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        ) {
            Text(text = "Server type",
                fontFamily = customFontFamily,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold)
            Text(text = type, fontFamily = customFontFamily, fontSize = 18.sp)
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        ) {
            Text(text = "Memory",
                fontFamily = customFontFamily,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold)
            Text(text = "$memory GB", fontFamily = customFontFamily, fontSize = 18.sp)
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        ) {
            Text(text = "Status",
                fontFamily = customFontFamily,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold)
            Button(onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if(currentS) Color.Green else Color.Red
                )
            ) {
                Text(text = if(currentS) "SERVER UP" else "SERVER_DOWN")
            }
        }
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            Spacer(Modifier.weight(1f))
            Button(
                modifier = Modifier.weight(1f),
                onClick = {
                    viewModel.updateInstanceStatus(id)
                }
            ) {
                Text(text = "Change status", fontFamily = customFontFamily, fontSize = 18.sp)
            }
        }

        if(loadError.isNotEmpty()) {
            Text(text = loadError,
                color= Color.Red,
                fontSize = 18.sp,
                modifier = Modifier.padding(8.dp)
            )
        } else if(message.isNotEmpty()) {
            Text(text = message,
                color= Color.Green,
                fontSize = 18.sp,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
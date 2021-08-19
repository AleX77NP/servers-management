package com.example.serversmanagement.serverlist.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
    viewModel: ServerListViewModel = hiltViewModel()
) {
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
            Text(text = "${memory.toString()} GB", fontFamily = customFontFamily, fontSize = 18.sp)
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
                    backgroundColor = if(status) Color.Green else Color.Red
                )
            ) {
                Text(text = if(status) "SERVER UP" else "SERVER_DOWN")
            }
        }
    }
}
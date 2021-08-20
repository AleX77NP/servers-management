package com.example.serversmanagement.serverlist.addinstance

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.serversmanagement.data.dto.CreateInstanceDto
import com.example.serversmanagement.serverlist.ServerListViewModel
import com.example.serversmanagement.util.customFontFamily

@Composable
fun CreateInstance(
    viewModel: ServerListViewModel = hiltViewModel()
) {
    var loadError by remember {
        viewModel.loadError
    }
    var message by remember {
        viewModel.message
    }
    var name by remember {
        mutableStateOf("")
    }
    var ipaddress by remember {
        mutableStateOf("")
    }
    var status by remember {
        mutableStateOf(false)
    }
    var memory by remember {
        mutableStateOf(0f)
    }
    var type by remember {
        mutableStateOf("Web Server")
    }

    Text(
        text = "Instance name", fontFamily = customFontFamily,
        modifier = Modifier.padding(bottom = 8.dp)
    )
    TextField(value = name, onValueChange = { name = it })

    Spacer(modifier = Modifier.height(20.dp))

    Text(
        text = "Instance ip address", fontFamily = customFontFamily,
        modifier = Modifier.padding(bottom = 8.dp)
    )
    TextField(value = ipaddress, onValueChange = { ipaddress = it })

    Spacer(modifier = Modifier.height(20.dp))

    Text(text = "Status")
    Switch(checked = status, onCheckedChange = { status = it })
    Text(
        text = if (status) "UP" else "DOWN", color = if (status) Color.Green else Color.Red,
        modifier = Modifier.padding(bottom = 8.dp), fontFamily = customFontFamily
    )

    Spacer(modifier = Modifier.height(20.dp))

    Text(text = "Memory")
    Slider(
        value = memory,
        onValueChange = { memory = it },
        valueRange = 0f..128f
    )
    Text(
        text = (viewModel.calculateMemory(memory)).toString(),
        modifier = Modifier.padding(bottom = 8.dp), fontFamily = customFontFamily
    )

    Spacer(modifier = Modifier.height(20.dp))

    Text(text = "Type")
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()
    ) {
        RadioButton(selected = type == "Web Server", onClick = { type = "Web Server" })
        RadioButton(selected = type == "Database Server", onClick = { type = "Database Server" })
    }
    Text(
        text = type,
        modifier = Modifier.padding(bottom = 8.dp), fontFamily = customFontFamily
    )

    Spacer(modifier = Modifier.height(20.dp))

    Button(onClick = {
        val instance = CreateInstanceDto(
            name = name,
            ipaddress = ipaddress,
            memory = viewModel.calculateMemory(memory),
            status = status,
            type = type,
            user = "milanovicaleX77@gmail.com"
        )
        viewModel.createServerInstance(instance)
    }) {
        Text("Submit", fontFamily = customFontFamily)
    }

    if (loadError.isNotEmpty()) {
        Text(
            text = loadError,
            color = Color.Red,
            fontSize = 18.sp,
            modifier = Modifier.padding(8.dp)
        )
    } else if (message.isNotEmpty()) {
        Text(
            text = message,
            color = Color.Green,
            fontSize = 18.sp,
            modifier = Modifier.padding(8.dp)
        )
    }
}
package com.example.serversmanagement.serverlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.serversmanagement.data.models.ServerListEntry
import com.example.serversmanagement.util.customFontFamily

@Composable
fun InstanceItem(
    entry: ServerListEntry,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: ServerListViewModel = hiltViewModel()
) {
    val serverType = viewModel.serverTypeImage(entry.type)
    var showDialog by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier
            .padding(bottom = 14.dp),
        contentAlignment = Center
    ) {
        if(showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(text = "Delete instance")},
                text = { Text(text = "Are You sure You want do delete this instance?") },
                confirmButton = { Text(text = "Yes", modifier = Modifier.clickable {
                    viewModel.deleteInstance(entry.id)
                    showDialog = false
                }) },
                dismissButton = { Text(text = "Cancel", Modifier.clickable {
                    showDialog = false
                })}
            )
        }
        Card(
            modifier = modifier
                .clickable {
                    navController.navigate(
                        "instance_details_screen/${entry.name}/${entry.ipaddress}/${entry.status}/${entry.memory}/${entry.type}/${entry.id}"
                    )
                },
            backgroundColor = Color.Transparent,
            shape = RoundedCornerShape(3.dp)
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                Image(
                    painter = painterResource(serverType),
                    contentDescription = "Instance icon",
                    modifier = Modifier
                        .size(90.dp)
                        .align(CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Column(modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = entry.name,
                        fontFamily = customFontFamily,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                    )
                    Button(onClick = { showDialog = true }, colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.LightGray
                    )) {
                        Text(text = "Delete", color = Color.Red)
                    }
                }
            }
        }
    }
}
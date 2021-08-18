package com.example.serversmanagement.serverlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
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
    var serverType by remember {
        mutableStateOf(viewModel.serverTypeImage(entry.type))
    }

    Box(
        modifier = Modifier
            .padding(bottom = 14.dp),
        contentAlignment = Center
    ) {
        Card(
            modifier = modifier
                .clickable {
                    navController.navigate(
                        "instance_details_screen/${entry.name}/${entry.ipaddress}/${entry.status}/${entry.memory}/${entry.type}"
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
                Text(
                    text = entry.name,
                    fontFamily = customFontFamily,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
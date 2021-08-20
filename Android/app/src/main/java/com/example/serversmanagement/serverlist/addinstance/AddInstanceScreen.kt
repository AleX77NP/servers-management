package com.example.serversmanagement.serverlist.addinstance

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.serversmanagement.util.customFontFamily

@Composable
fun AddInstanceScreen() {
    Column(
        modifier = Modifier
            .padding(horizontal = 50.dp)
            .padding(top = 25.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Add new instance",
            fontFamily = customFontFamily,
            fontSize = 21.sp,
            modifier = Modifier.padding(bottom = 25.dp)
        )

        CreateInstance()

    }
}
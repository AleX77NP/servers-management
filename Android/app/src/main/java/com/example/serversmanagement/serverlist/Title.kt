package com.example.serversmanagement.serverlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.serversmanagement.R

@Composable
fun Title(text: String) {
    val fontFamily = FontFamily(
        Font(R.font.teko_light, FontWeight.Light),
        Font(R.font.teko_medium, FontWeight.Medium),
        Font(R.font.teko_regular, FontWeight.Normal),
        Font(R.font.teko_semibold, FontWeight.SemiBold),
        Font(R.font.teko_bold, FontWeight.Bold)
    )

    Box(modifier = Modifier.fillMaxWidth(),
    contentAlignment = Alignment.Center) {
        Text(
            text = text,
            color = Color.Black,
            fontSize = 32.sp,
            fontFamily =  fontFamily,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}
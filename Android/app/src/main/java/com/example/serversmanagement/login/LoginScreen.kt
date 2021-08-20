package com.example.serversmanagement.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.serversmanagement.R
import com.example.serversmanagement.util.UserInfo
import com.example.serversmanagement.util.customFontFamily
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    userInfo: UserInfo,
    fn: () -> Unit
) {
    var email by remember {
        mutableStateOf("")
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 50.dp,
        )
    ) {
        Image(painter = painterResource(id = R.drawable.server_logo), contentDescription = "Logo")
        Text(text = "Server management app",
        fontFamily = customFontFamily,
        fontSize = 21.sp)

        Text(text = "Enter your email address",
            fontFamily = customFontFamily,
            fontSize = 21.sp,
            modifier = Modifier.padding(vertical = 14.dp)
        )
        TextField(value = email, onValueChange = {email = it})
        Button(
            modifier = Modifier.padding(top = 14.dp),
            onClick = {
                GlobalScope.launch {
                    userInfo.setUser(email).run {
                        fn()
                    }
                }
        }) {
            Text(text = "Login")
        }

    }
}
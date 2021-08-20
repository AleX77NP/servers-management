package com.example.serversmanagement

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.serversmanagement.login.LoginScreen
import com.example.serversmanagement.navigation.Navigation
import com.example.serversmanagement.ui.theme.ServersManagementTheme
import com.example.serversmanagement.util.Constants
import com.example.serversmanagement.util.UserInfo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var userInfo: UserInfo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userInfo = UserInfo(applicationContext)
        setContent {
            var user by remember {
                mutableStateOf("")
            }
            var loggedIn by remember {
                mutableStateOf(false)
            }
            var loading by remember {
                mutableStateOf(true)
            }
            fun login() {
                loggedIn = true
            }
            LaunchedEffect(key1 = user, block = {
                GlobalScope.launch {
                    val info = userInfo.getUser()
                    info.collect {
                        user = it
                        if (user != "") {
                            loading = false
                            loggedIn = true
                        } else {
                            loading = false
                        }
                    }
                }
            } )
            ServersManagementTheme {
                if(loading) {
                    Column(modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    if(loggedIn) {
                        Navigation()
                    } else {
                        LoginScreen(userInfo = userInfo) {
                            login()
                        }
                    }
                }
            }
        }
    }
}

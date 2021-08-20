package com.example.serversmanagement

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
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
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Constants.DATASTORE_NAME)
    private lateinit var userInfo: UserInfo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userInfo = UserInfo(dataStore = dataStore)
        setContent {
            var user by remember {
                mutableStateOf("")
            }
            var loggedIn by remember {
                mutableStateOf(false)
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
                            loggedIn = true
                        }
                    }
                }
            } )
            ServersManagementTheme {
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

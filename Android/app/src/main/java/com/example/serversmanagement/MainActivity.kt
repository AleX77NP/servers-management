package com.example.serversmanagement

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.serversmanagement.navigation.Navigation
import com.example.serversmanagement.ui.theme.ServersManagementTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ServersManagementTheme {
                Navigation()
        }
    }
}

}
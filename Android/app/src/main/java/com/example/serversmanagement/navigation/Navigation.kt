package com.example.serversmanagement.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.serversmanagement.serverlist.ServerListScreen
import com.example.serversmanagement.serverlist.ServerListViewModel
import com.example.serversmanagement.serverlist.details.DetailScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "server_list_screen")
    {
        composable("server_list_screen") {
            ServerListScreen(navController = navController)
        }
        composable("instance_details_screen/{name}/{ipaddress}/{status}/{memory}/{type}/{id}",
            arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType

                },
                navArgument("ipaddress") {
                    type = NavType.StringType
                },
                navArgument("status") {
                    type = NavType.BoolType
                },
                navArgument("memory") {
                    type = NavType.IntType
                },
                navArgument("type") {
                    type = NavType.StringType
                },
                navArgument("id") {
                    type = NavType.StringType
                }
            )) {
            val instanceName = remember {
                val name = it.arguments?.getString("name")
                name?.let { it } ?: "Linux Ubuntu"
            }
            val instanceIpAddress = remember {
                val ipaddress = it.arguments?.getString("ipaddress")
                ipaddress?.let { it } ?: "127.0.0.1"
            }
            val instanceStatus = remember {
                val status= it.arguments?.getBoolean("status")
                status?.let { it } ?: false
            }
            val instanceMemory = remember {
                val memory = it.arguments?.getInt("memory")
                memory?.let { it } ?: 1
            }
            val instanceType = remember {
                val type = it.arguments?.getString("type")
                type?.let { it } ?: "Web Server"
            }
            val instanceId = remember {
                val id = it.arguments?.getString("id")
                id?.let { it } ?: "id"
            }

            DetailScreen(
                name = instanceName,
                ipaddress = instanceIpAddress ,
                status =instanceStatus ,
                memory = instanceMemory ,
                type = instanceType,
                id = instanceId
            )
        }
    }
}
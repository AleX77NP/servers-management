package com.example.serversmanagement.util

import androidx.datastore.preferences.core.stringPreferencesKey

object Constants {
    const val BASE_URL = "http://192.168.100.2:5000/"
    const val WEB_SERVER = "Web Server"
    const val FETCH_MY_SERVERS_ERROR = "Couldn't load instances."
    val DATASTORE_NAME = "user_info"
    val USER_PREFERENCE_KEY = stringPreferencesKey("user")
}
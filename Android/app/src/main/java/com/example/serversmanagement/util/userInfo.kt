package com.example.serversmanagement.util

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.example.serversmanagement.util.Constants.USER_PREFERENCE_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import okio.IOException

class UserInfo(private val dataStore: DataStore<Preferences>) {

    suspend fun getUser(): Flow<String> {
        return dataStore.data.catch {
            exception ->
            if(exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map {
            preferences ->
            preferences[USER_PREFERENCE_KEY] ?: ""
        }
    }

    suspend fun setUser(user: String) {
        dataStore.edit {
            preferences ->
            preferences[USER_PREFERENCE_KEY] = user
        }
    }
}


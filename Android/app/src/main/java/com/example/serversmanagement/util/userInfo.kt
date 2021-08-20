package com.example.serversmanagement.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.serversmanagement.util.Constants.USER_PREFERENCE_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import okio.IOException

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = Constants.DATASTORE_NAME
)

class UserInfo(private val context: Context) {


    suspend fun getUser(): Flow<String> {
        return context.dataStore.data.catch {
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
        context.dataStore.edit {
            preferences ->
            preferences[USER_PREFERENCE_KEY] = user
        }
    }
}


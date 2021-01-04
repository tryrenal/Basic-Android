package com.renaldysabdo.basicandroid

import android.content.Context
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserManager(context: Context) {

    val dataStore = context.createDataStore("prefs_user")

    companion object{
        val USER_NAME_KEY = preferencesKey<String>("USER_NAME_KEY")
        val USER_AGE_KEY = preferencesKey<String>("USER_AGE_KEY")
    }

    suspend fun setDataUser(name: String, age: String) {
        dataStore.edit {
            it[USER_NAME_KEY] = name
            it[USER_AGE_KEY] = age
        }
    }

    val userNameFlow : Flow<String> = dataStore.data.map {
        it[USER_NAME_KEY] ?: ""
    }

    val userAgeFlow : Flow<String> = dataStore.data.map {
        it[USER_AGE_KEY] ?: ""
    }

}
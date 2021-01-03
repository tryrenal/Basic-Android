@file:Suppress("DEPRECATION")

package com.renaldysabdo.basicandroid

import android.content.Context
import android.preference.PreferenceManager
import androidx.core.content.edit

class ResourceManagement(context: Context) {
    companion object{
        private const val USER_NAME_KEY = "USER_NAME"
        private const val USER_AGE_KEY = "USER_AGE"
    }

    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    var username : String
        get() = sharedPreferences.getString(USER_NAME_KEY, "") ?: ""
        set(value) = sharedPreferences.edit { putString(USER_NAME_KEY, value) }

    var age : String
        get() = sharedPreferences.getString(USER_AGE_KEY, "") ?: ""
        set(value) = sharedPreferences.edit { putString(USER_AGE_KEY, value) }

}
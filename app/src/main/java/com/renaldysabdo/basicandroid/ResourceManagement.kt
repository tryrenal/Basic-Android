@file:Suppress("DEPRECATION")

package com.renaldysabdo.basicandroid

import android.content.Context
import android.preference.PreferenceManager
import androidx.core.content.edit
import me.ibrahimsn.library.LiveSharedPreferences


const val USER_NAME_KEY = "USER_NAME"
const val USER_AGE_KEY = "USER_AGE"

class ResourceManagement(context: Context) {

    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    val liveSharedPreferences = LiveSharedPreferences(sharedPreferences)

    fun setUserName(name: String) {
        sharedPreferences.edit { putString(USER_NAME_KEY, name) }
    }

    fun setUserAge(age: String){
        sharedPreferences.edit { putString(USER_AGE_KEY, age) }
    }
}



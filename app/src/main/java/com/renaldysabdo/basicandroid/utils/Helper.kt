package com.renaldysabdo.basicandroid.utils

import java.text.SimpleDateFormat
import java.util.*

object Helper {
    fun convertDateToString(date: Date) : String{
        val sdf = SimpleDateFormat("MM/dd/yyyy")
        return sdf.format(date)
    }
}
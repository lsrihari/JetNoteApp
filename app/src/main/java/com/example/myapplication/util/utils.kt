package com.example.myapplication.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun formatDate(time:Long):String{
    val date= Date(time)
    val format=SimpleDateFormat("EE, d MMM hh:mm aaa",
    Locale.getDefault()
    )
    return format.format(date)
}
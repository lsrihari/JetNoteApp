package com.example.myapplication.util

import androidx.room.TypeConverter
import java.sql.Timestamp
import java.util.*

class DateConvertor {
    @TypeConverter
    fun timeStampFromDate(date: Date):Long{
        return date.time
    }

    @TypeConverter
    fun dateFromTimestamp (timestamp: Long):Date?{
        return Date(timestamp)
    }
}
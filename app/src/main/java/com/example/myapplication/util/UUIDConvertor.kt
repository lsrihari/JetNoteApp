package com.example.myapplication.util

import androidx.room.TypeConverter
import java.util.*

class UUIDConvertor {
    @TypeConverter
    fun fromUUID(uuid: UUID):String?{
        return uuid.toString()
    }

    @TypeConverter
    fun uuidFromString(string:String?):UUID?{
        return UUID.fromString(string)
    }
}
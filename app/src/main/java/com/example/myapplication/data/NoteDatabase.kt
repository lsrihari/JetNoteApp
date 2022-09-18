package com.example.myapplication.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.myapplication.model.Note
import com.example.myapplication.util.DateConvertor
import com.example.myapplication.util.UUIDConvertor


@RequiresApi(Build.VERSION_CODES.O)
@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(DateConvertor::class,UUIDConvertor::class)
abstract  class NoteDatabase:RoomDatabase() {
    abstract fun noteDao():NoteDatabaseDao


}
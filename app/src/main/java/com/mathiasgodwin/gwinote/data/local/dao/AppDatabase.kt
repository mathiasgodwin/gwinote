package com.mathiasgodwin.gwinote.data.local.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.mathiasgodwin.gwinote.data.local.entity.ColorDataConverter
import com.mathiasgodwin.gwinote.data.local.entity.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
@TypeConverters(ColorDataConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "note_db"
                ).build().also { INSTANCE = it }
            }
    }
}
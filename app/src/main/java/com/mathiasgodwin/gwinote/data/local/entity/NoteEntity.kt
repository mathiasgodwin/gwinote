package com.mathiasgodwin.gwinote.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

@Entity
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val content: String,
    val color: ColorData
)

data class ColorData(val id: Long, val hex: String, val name: String)

class ColorDataConverter {
    @TypeConverter
    fun fromColorData(colorData: ColorData?): String? {
        return colorData?.let { "${it.id},${it.hex},${it.name}" }
    }

    @TypeConverter
    fun toColorData(value: String?): ColorData? {
        return value?.split(',')?.let { ColorData(it[0].toLong(), it[1], it[2]) }
    }
}
package com.houssam.guidomia.data.local

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object CarConverter {

    private val json = Json


    @TypeConverter
    fun fromList(value: List<String>): String {
        return json.encodeToString(value)
    }

    @TypeConverter
    fun toList(value: String): List<String> {
        return json.decodeFromString(value)
    }
}
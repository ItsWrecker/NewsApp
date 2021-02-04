package com.wrecker.newsapp.db.source.local.database

import androidx.room.TypeConverter
import com.wrecker.newsapp.db.entity.Source


class Converters {

    @TypeConverter
    fun fromSource(source: Source): String {
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source {
        return  Source(id = name, name = name)
    }
}
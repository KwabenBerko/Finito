package com.kwabenaberko.finito.model.repository.database

import android.arch.persistence.room.TypeConverter
import com.kwabenaberko.finito.model.Priority
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset

class Converters {
    @TypeConverter
    fun priorityEnumToInt(priority: Priority): Int{
        return priority.ordinal
    }

    @TypeConverter
    fun intToPriorityEnum(ordinal: Int): Priority{
        return Priority.values().get(ordinal)
    }

    @TypeConverter
    fun dateToLong(date: LocalDateTime): Long{
        return date.toInstant(ZoneOffset.UTC).toEpochMilli()
    }

    @TypeConverter
    fun longToDate(long: Long): LocalDateTime{
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(long), ZoneOffset.UTC)
    }

}
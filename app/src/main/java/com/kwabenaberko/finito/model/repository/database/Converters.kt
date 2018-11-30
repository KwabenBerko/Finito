package com.kwabenaberko.finito.model.repository.database

import android.arch.persistence.room.TypeConverter
import com.kwabenaberko.finito.model.Priority

class Converters {
    @TypeConverter
    fun priorityEnumToInt(priority: Priority): Int{
        return priority.ordinal
    }

    @TypeConverter
    fun intToPriorityEnum(ordinal: Int): Priority{
        return Priority.values().get(ordinal)
    }
}
package com.kwabenaberko.finito.util

import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset
import java.lang.reflect.Type

class DateTimeSerializer : JsonSerializer<LocalDateTime> {
    override fun serialize(src: LocalDateTime, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        val zonedDateTime = src.atZone(ZoneOffset.UTC)
        return JsonPrimitive(zonedDateTime.toString())
    }
}
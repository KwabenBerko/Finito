package com.kwabenaberko.finito.util

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZonedDateTime
import java.lang.reflect.Type

class DateTimeDeserializer : JsonDeserializer<LocalDateTime> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): LocalDateTime {
        return ZonedDateTime.parse(json.asJsonPrimitive.asString).toLocalDateTime()
    }
}
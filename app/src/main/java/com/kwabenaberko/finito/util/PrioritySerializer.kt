package com.kwabenaberko.finito.util

import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import com.kwabenaberko.finito.model.Priority
import java.lang.reflect.Type

class PrioritySerializer : JsonSerializer<Priority> {

    override fun serialize(src: Priority, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        return JsonPrimitive(src.ordinal.toString())
    }
}
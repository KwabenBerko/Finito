package com.kwabenaberko.finito.util

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.kwabenaberko.finito.model.Priority
import java.lang.reflect.Type

class PriorityDeserializer : JsonDeserializer<Priority>{

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Priority {
        return Priority.values()[json.asJsonPrimitive.asInt]
    }
}
package com.kwabenaberko.finito.util

import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter


object Helper {
    @JvmStatic
    fun formatDate(dateTime: LocalDateTime, format: String): String{
        val formatter = DateTimeFormatter.ofPattern(format)
        return dateTime.format(formatter)
    }

}
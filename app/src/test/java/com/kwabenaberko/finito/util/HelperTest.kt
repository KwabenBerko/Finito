package com.kwabenaberko.finito.util

import junit.framework.Assert.assertEquals
import org.junit.Test
import org.threeten.bp.LocalDateTime

class HelperTest {
    @Test
    fun testFormatDate(){
        val dateTime = LocalDateTime.parse("2018-12-11T16:01:30")
        val formatted = Helper.formatDate(dateTime, "d MMMM, yyyy")
        assertEquals("11 December, 2018", formatted)
    }
}
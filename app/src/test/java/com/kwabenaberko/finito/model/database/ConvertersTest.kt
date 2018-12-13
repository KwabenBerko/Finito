package com.kwabenaberko.finito.model.database

import com.kwabenaberko.finito.model.Priority
import com.kwabenaberko.finito.model.repository.database.Converters
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.threeten.bp.LocalDateTime

class ConvertersTest {

    private lateinit var converters: Converters
    private val localDateTime = LocalDateTime.parse("2018-12-13T12:19:13")
    private val longDateTime = 1544703553000

    @Before
    fun setup(){
        converters = Converters()
    }

    @Test
    fun testPriorityEnumToInt(){
        val priorityHighInt = converters.priorityEnumToInt(Priority.HIGH)
        val priorityMediumInt = converters.priorityEnumToInt(Priority.MEDIUM)
        val priorityLowInt = converters.priorityEnumToInt(Priority.LOW)

        assertEquals(2, priorityHighInt)
        assertEquals(1, priorityMediumInt)
        assertEquals(0, priorityLowInt)
    }

    @Test
    fun testIntToPriorityEnum(){
        val priorityHigh = converters.intToPriorityEnum(2)
        val priorityMedium = converters.intToPriorityEnum(1)
        val priorityLow = converters.intToPriorityEnum(0)

        assertEquals(Priority.HIGH, priorityHigh)
        assertEquals(Priority.MEDIUM, priorityMedium)
        assertEquals(Priority.LOW, priorityLow)
    }

    @Test
    fun testDateToLong(){
        assertEquals(longDateTime, converters.dateToLong(localDateTime))
    }

    @Test
    fun testLongToDate(){
        assertTrue(localDateTime.equals(converters.longToDate(longDateTime)))
    }
}
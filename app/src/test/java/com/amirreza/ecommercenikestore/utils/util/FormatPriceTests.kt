package com.amirreza.ecommercenikestore.utils.util

import org.junit.Assert.*

import org.junit.Test

class FormatPriceTests {

    @Test
    fun `input-1000873`() {
        assertEquals(formatPrice(1000873), "1,000,873")
    }

    @Test
    fun `input-0`() {
        assertEquals(formatPrice(0), "0")
    }

    @Test
    fun `input-123`() {
        assertEquals(formatPrice(123), "123")
    }

    @Test
    fun `input-1244`() {
        assertEquals(formatPrice(1244), "1,244")
    }

    @Test
    fun `input-1`() {
        assertEquals(formatPrice(1), "1")
    }

    @Test
    fun `input-1000`() {
        assertEquals(formatPrice(1000), "1,000")
    }

    @Test
    fun `input-700873`() {
        assertEquals(formatPrice(700873), "700,873")
    }

    @Test
    fun `input-1222221`() {
        assertEquals(formatPrice(1222221), "1,222,221")
    }

    @Test
    fun `input-122222`() {
        assertEquals(formatPrice(122222), "122,222")
    }

}
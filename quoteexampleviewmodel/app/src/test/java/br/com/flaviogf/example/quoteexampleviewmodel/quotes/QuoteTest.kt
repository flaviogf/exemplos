package br.com.flaviogf.example.quoteexampleviewmodel.quotes

import org.junit.Assert.assertEquals
import org.junit.Test

class QuoteTest {
    private val quote = Quote("Hello", "Tony Stark")

    @Test
    fun `should quote are text when is created`() {
        assertEquals("Hello", quote.text)
    }

    @Test
    fun `should quote are author when is created`() {
        assertEquals("Tony Stark", quote.author)
    }

    @Test
    fun `should to string return author and text`() {
        assertEquals("Tony Stark - Hello", quote.toString())
    }
}
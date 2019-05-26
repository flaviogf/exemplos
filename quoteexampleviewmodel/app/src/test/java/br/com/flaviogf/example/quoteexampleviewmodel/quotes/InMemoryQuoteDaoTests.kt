package br.com.flaviogf.example.quoteexampleviewmodel.quotes

import org.junit.Assert.assertEquals
import org.junit.Test

class InMemoryQuoteDaoTests {
    private val initialQuotes: List<Quote> = listOf(Quote("I'm iron man", "Tony Start"))

    private val quoteDao: QuoteDao = InMemoryQuoteDao(initialQuotes)

    @Test
    fun `should all return a list of quote`() {
        val quotes = quoteDao.all()

        assertEquals(1, quotes.count())
    }

    @Test
    fun `should add quote when add a quote`() {
        val quote = Quote("I'm captain america", "Steve")

        quoteDao.add(quote)

        val quotes = quoteDao.all()

        assertEquals(2, quotes.count())
    }
}
package br.com.flaviogf.example.quoteexampleviewmodel.quotes

import org.junit.Assert.assertEquals
import org.junit.Test


class QuoteRepositoryTests {
    @Test
    fun `should return a list of quote`() {
        val quoteDao = FakeQuoteDao()

        val quoteRepository = QuoteRepository(quoteDao)

        val quotes = quoteRepository.all()

        assertEquals(0, quotes.count())
    }

    @Test
    fun `should add a quote when add quote`() {
        val quote = Quote("Hello", "Tony")

        val quoteDao = FakeQuoteDao()

        val quoteRepository = QuoteRepository(quoteDao)

        quoteRepository.add(quote)

        val quotes = quoteRepository.all()

        assertEquals(1, quotes.count())
    }

    class FakeQuoteDao : QuoteDao {
        private val quotes: MutableList<Quote> = mutableListOf()

        override fun all(): List<Quote> {
            return quotes
        }

        override fun add(quote: Quote) {
            quotes.add(quote)
        }
    }
}
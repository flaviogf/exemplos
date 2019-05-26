package br.com.flaviogf.example.quoteexampleviewmodel.quotes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class QuoteViewModelTests {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val quoteDao = FakeQuoteDao()

    private val quoteRepository = QuoteRepository(quoteDao)

    private lateinit var model: QuoteViewModel

    @Before
    fun setUp() {
        model = QuoteViewModel(quoteRepository)
    }

    @Test
    fun `should return live data of list quote list all quotes`() {
        model.listAllQuotes().observeForever {
            assertEquals(0, it?.count())
        }
    }

    @Test
    fun `should add quote when add quote`() {
        val quote = Quote("Hello", "Tony Stark")

        model.addQuote(quote)

        model.listAllQuotes().observeForever {
            assertEquals(1, it?.count())
        }
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
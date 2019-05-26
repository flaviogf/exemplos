package br.com.flaviogf.example.quoteexampleviewmodel.quotes

class InMemoryQuoteDao(quotes: List<Quote>) : QuoteDao {
    private val quotes = mutableListOf<Quote>()

    init {
        this.quotes.addAll(quotes)
    }

    override fun all(): List<Quote> {
        return quotes.toList()
    }

    override fun add(quote: Quote) {
        quotes.add(quote)
    }
}
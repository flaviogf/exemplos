package br.com.flaviogf.example.quoteexampleviewmodel.quotes

class QuoteRepository(private val quoteDao: QuoteDao) {
    fun all(): List<Quote> {
        return quoteDao.all()
    }

    fun add(quote: Quote) {
        quoteDao.add(quote)
    }
}
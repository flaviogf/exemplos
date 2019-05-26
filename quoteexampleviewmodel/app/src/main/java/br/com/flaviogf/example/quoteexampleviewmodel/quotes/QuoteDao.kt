package br.com.flaviogf.example.quoteexampleviewmodel.quotes

interface QuoteDao {
    fun all(): List<Quote>
    fun add(quote: Quote)
}
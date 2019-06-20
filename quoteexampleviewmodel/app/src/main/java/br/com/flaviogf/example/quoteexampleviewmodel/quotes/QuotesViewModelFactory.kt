package br.com.flaviogf.example.quoteexampleviewmodel.quotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class QuotesViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val quote = Quote("Hello", "Tony")

        val quoteDao = InMemoryQuoteDao(listOf(quote))

        val quoteRepository = QuoteRepository(quoteDao)

        return QuoteViewModel(quoteRepository) as T
    }
}
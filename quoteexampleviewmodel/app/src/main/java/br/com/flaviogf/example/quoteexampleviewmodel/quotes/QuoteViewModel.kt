package br.com.flaviogf.example.quoteexampleviewmodel.quotes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuoteViewModel(private val quoteRepository: QuoteRepository) : ViewModel() {
    private val quotes: MutableLiveData<List<Quote>> by lazy {
        MutableLiveData<List<Quote>>().also {
            it.value = quoteRepository.all()
        }
    }

    fun listAllQuotes(): LiveData<List<Quote>> {
        return quotes
    }

    fun addQuote(quote: Quote) {
        quoteRepository.add(quote)

        quotes.value = quoteRepository.all()
    }
}
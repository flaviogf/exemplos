package br.com.flaviogf.example.quoteexampleviewmodel.quotes

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.flaviogf.example.quoteexampleviewmodel.R
import kotlinx.android.synthetic.main.activity_quotes.*

class QuotesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quotes)

        val model = ViewModelProviders.of(this, QuotesViewModelFactory()).get(QuoteViewModel::class.java)

        model.listAllQuotes().observe(this, Observer {
            quotes_list_view.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, it)
        })
    }
}

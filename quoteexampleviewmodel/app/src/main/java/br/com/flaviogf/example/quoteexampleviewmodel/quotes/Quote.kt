package br.com.flaviogf.example.quoteexampleviewmodel.quotes

data class Quote(
    val text: String,
    val author: String
) {
    override fun toString() = "$author - $text"
}
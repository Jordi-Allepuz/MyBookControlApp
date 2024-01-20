package com.example.mybookcontrolapp.mybookcontrolerapp.data.dataInfo

data class User(
    val Name: String = "",
    val Age: Int = 0,
    val email: String = "",
    val favoriteGenere: String = "",
    val books: List<Book> = emptyList()
)

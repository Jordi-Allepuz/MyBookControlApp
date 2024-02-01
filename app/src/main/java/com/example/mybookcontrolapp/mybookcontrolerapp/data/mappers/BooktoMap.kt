package com.example.mybookcontrolapp.mybookcontrolerapp.data.mappers

import com.example.mybookcontrolapp.mybookcontrolerapp.data.dataInfo.Book
import com.example.mybookcontrolapp.mybookcontrolerapp.data.dataInfo.User

fun BookToMap (book:Book):MutableMap<String, Any>{
    return mutableMapOf(
        "titulo" to book.titulo,
        "autor" to book.autor,
        "editorial" to book.editorial,
        "isbn" to book.isbn,
        "genero" to book.editorial,
        "portada" to book.portada,
        "likes" to book.likes)

}
package com.example.mybookcontrolapp.mybookcontrolerapp.data.dataInfo

import com.google.firebase.firestore.DocumentReference

data class User(
    val name: String = "",
    val age: String = "",
    val email: String = "",
    val genero_favorito: String = "",
    val libros_leidos: List<String> = emptyList()
)

package com.example.mybookcontrolapp.mybookcontrolerapp.data.dataInfo

data class User(
    val name: String = "",
    val age: String = "",
    val email: String = "",
    val genero_favorito: String = "",
    val foto:String = "",
    val libros_leidos: List<String> = emptyList(),

)

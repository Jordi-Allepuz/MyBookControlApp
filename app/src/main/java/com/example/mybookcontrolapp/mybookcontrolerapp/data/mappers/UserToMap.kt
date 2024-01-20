package com.example.mybookcontrolapp.mybookcontrolerapp.data.mappers

import com.example.mybookcontrolapp.mybookcontrolerapp.data.dataInfo.User


fun UserToMap (user: User):MutableMap<String, Any>{
    return mutableMapOf(
        "name" to user.Name,
        "age" to user.Age,
        "email" to user.email,
        "genero_favorito" to user.favoriteGenere,
        "libros_leidos" to user.books
    )

}
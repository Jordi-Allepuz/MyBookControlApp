package com.example.mybookcontrolapp.mybookcontrolerapp.data.mappers

import com.example.mybookcontrolapp.mybookcontrolerapp.data.dataInfo.User


fun UserToMap (user: User):MutableMap<String, Any>{
    return mutableMapOf(
        "name" to user.name,
        "age" to user.age,
        "email" to user.email,
        "genero_favorito" to user.genero_favorito,
//        "libros_leidos" to user.libros_leidos
    )

}
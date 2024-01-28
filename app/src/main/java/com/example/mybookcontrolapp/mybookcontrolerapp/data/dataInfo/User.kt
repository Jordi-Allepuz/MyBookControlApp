package com.example.mybookcontrolapp.mybookcontrolerapp.data.dataInfo

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.lang.ref.Reference

data class User(
    val name: String = "",
    val age: String = "",
    val email: String = "",
    val genero_favorito: String = "",
    val libros_leidos: List<String> = emptyList(),
    val profile_image:String = ""
)

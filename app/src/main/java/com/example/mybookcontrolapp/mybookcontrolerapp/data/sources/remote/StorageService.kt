package com.example.mybookcontrolapp.mybookcontrolerapp.data.sources.remote


import com.example.mybookcontrolapp.mybookcontrolerapp.data.dataInfo.User
import com.example.mybookcontrolapp.mybookcontrolerapp.data.mappers.UserToMap
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class StorageService @Inject constructor(private val firestore: FirebaseFirestore) {

    suspend fun registredUserData(user: User) {
        val usuarioMap = UserToMap(user)
        firestore.collection("usuarios").add(usuarioMap)
    }

    suspend fun cargarInfoUser(userName: String) :User?{
       val result = firestore.collection("usuarios").whereEqualTo("name", userName).get().await()
        return result.documents.firstOrNull()?.toObject<User>()
    }

}
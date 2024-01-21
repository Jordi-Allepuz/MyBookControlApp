package com.example.mybookcontrolapp.mybookcontrolerapp.data.sources.remote


import com.example.mybookcontrolapp.mybookcontrolerapp.data.dataInfo.Book
import com.example.mybookcontrolapp.mybookcontrolerapp.data.dataInfo.User
import com.example.mybookcontrolapp.mybookcontrolerapp.data.mappers.UserToMap
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class StorageService @Inject constructor(private val firebaseStorage: FirebaseFirestore) {

    suspend fun registredUserData(user: User) {
        val usuarioMap = UserToMap(user)
        firebaseStorage.collection("usuarios").add(usuarioMap)
    }

    suspend fun getInfoUser(email:String) :User?{
       val result = firebaseStorage.collection("usuarios").whereEqualTo("email", email).get().await()
        return result.documents.firstOrNull()?.toObject<User>()
    }

    suspend fun getInfoBook(id:String): Book?{
        val result = firebaseStorage.collection("libros").document(id).get().await()
        return result.toObject<Book>()
    }


//    suspend fun registredInfoBook(book: Book){
//        val bookMap = BookToMap(book)
//        firebaseStorage.collection("libros").add(bookMap)
//    }

}
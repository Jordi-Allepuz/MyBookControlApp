package com.example.mybookcontrolapp.mybookcontrolerapp.data.sources.remote


import com.example.mybookcontrolapp.mybookcontrolerapp.data.dataInfo.Book
import com.example.mybookcontrolapp.mybookcontrolerapp.data.dataInfo.User
import com.example.mybookcontrolapp.mybookcontrolerapp.data.mappers.UserToMap
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class StorageService @Inject constructor(private val firebaseStorage: FirebaseFirestore) {

    suspend fun registredUserData(user: User): Boolean {
        val usuarioMap = UserToMap(user)
        val result = firebaseStorage.collection("usuarios").add(usuarioMap).isComplete
        return result
    }

    suspend fun getInfoUser(email: String): User? {
        val result =
            firebaseStorage.collection("usuarios").whereEqualTo("email", email).get().await()
        return result.documents.firstOrNull()?.toObject<User>()
    }

    suspend fun getInfoBook(id: String): Book? {
        val result = firebaseStorage.collection("libros").document(id).get().await()
        return result.toObject<Book>()

    }

    suspend fun getBookList(id: String): Flow<List<Book>> = flow {
        firebaseStorage.collection("usuarios").document(id).collection("libros_leidos").get()
            .await().map { document ->
                document.toObject(Book::class.java)
            }
    }


//    suspend fun registredInfoBook(book: Book){
//        val bookMap = BookToMap(book)
//        firebaseStorage.collection("libros").add(bookMap)
//    }

}
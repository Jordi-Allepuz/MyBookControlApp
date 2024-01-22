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
        val result = firebaseStorage.collection("libros").document(id).get().addOnSuccessListener {
            val titulo = it.get("titulo") as String?
            val autor = it.get("autor") as String?
            val editorial = it.get("editorial") as String?
            val genero = it.get("genero") as String?
            val isbn = it.get("isbn") as String?
            val portada = it.get("portada") as String?
        }
        return Book(titulo, autor, editorial, genero, isbn, portada)
    }

    suspend fun getBookList(email: String): List<Book>? {
        val result =
            firebaseStorage.collection("usuarios").whereEqualTo("email", email).get().await()
        val userDocument = result.documents.firstOrNull() ?: return null
        return userDocument.get("libros_leidos") as? List<Book> ?: return emptyList()

    }


//    suspend fun registredInfoBook(book: Book){
//        val bookMap = BookToMap(book)
//        firebaseStorage.collection("libros").add(bookMap)
//    }

}
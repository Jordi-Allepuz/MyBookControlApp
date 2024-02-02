package com.example.mybookcontrolapp.mybookcontrolerapp.data.sources.remote


import com.example.mybookcontrolapp.mybookcontrolerapp.data.dataInfo.Book
import com.example.mybookcontrolapp.mybookcontrolerapp.data.dataInfo.User
import com.example.mybookcontrolapp.mybookcontrolerapp.data.mappers.bookToMap
import com.example.mybookcontrolapp.mybookcontrolerapp.data.mappers.userToMap
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class StorageService @Inject constructor(private val firebaseStorage: FirebaseFirestore, private val storage: FirebaseStorage) {

    suspend fun registredUserData(user: User): Boolean {
        val usuarioMap = userToMap(user)
        return firebaseStorage.collection("usuarios").add(usuarioMap).isComplete
    }

    suspend fun addBookUser(book: Book, id: String): Boolean {
        val bookMap = bookToMap(book)
        return firebaseStorage.collection("usuarios").document(id).collection("libros_favoritos")
            .add(bookMap).isComplete
    }

    suspend fun deleteBookUser(bookName: String, id: String):Boolean{
        val result = firebaseStorage.collection("usuarios").document(id)
            .collection("libros_favoritos").whereEqualTo("titulo", bookName).get().await()
        return if (result.documents.isEmpty()){
            false
        }else{
            result.documents.first().reference.delete().await()
            true
        }
    }


    suspend fun updateBookLikes(bookName:String, likes:Int):Boolean{
        val result = firebaseStorage.collection("libros").document(bookName).update("likes", likes)
        return result.isComplete
    }


    suspend fun getUserId(email: String): String? {
        val result =
            firebaseStorage.collection("usuarios").whereEqualTo("email", email).get().await()
        return result.documents.firstOrNull()?.id
    }


    suspend fun getInfoUser(email: String): User? {
        val result =
            firebaseStorage.collection("usuarios").whereEqualTo("email", email).get().await()
        return result.documents.firstOrNull()?.toObject<User>()
    }

    suspend fun getInfoBook(name: String): Book? {
        val result =
            firebaseStorage.collection("libros").whereEqualTo("titulo", name).get().await()
        return result.documents.firstOrNull()?.toObject<Book>()
    }


    suspend fun getFavoriteBooks(id: String): MutableList<Book> {
        val books = mutableListOf<Book>()
        try {
            val result = firebaseStorage
                .collection("usuarios")
                .document(id)
                .collection("libros_favoritos")
                .get()
                .await()

            for (documento in result) {
                val libro = documento.toObject(Book::class.java)
                books.add(libro)
            }
        } catch (e: Exception) {
            println("Error al obtener libros favoritos: ${e.message}")
        }
        return books
    }

    suspend fun getAllBooks(): MutableList<Book> {
        val books = mutableListOf<Book>()
        try {
            val result = firebaseStorage
                .collection("libros")
                .get()
                .await()

            for (documento in result) {
                val libro = documento.toObject(Book::class.java)
                books.add(libro)
            }
        } catch (e: Exception) {
            println("Error al obtener libros coleccion: ${e.message}")
        }
        return books

    }

    suspend fun getAllPhotos(): MutableList<String>{
        val photos = mutableListOf<String>()
        try {
            val result = storage.reference.child("users_Profiles").listAll().await()

            result.items.forEach{ file ->
                val url = file.downloadUrl.await()
                photos.add(url.toString())

            }
        }catch (e:Exception){
            println("Error al obtener photos : ${e.message}")
        }
        return photos
    }


}
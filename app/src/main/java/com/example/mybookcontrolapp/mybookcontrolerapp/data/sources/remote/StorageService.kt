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

// Clase para manejar operaciones de almacenamiento y recuperación de datos con Firebase Firestore y Storage.
class StorageService @Inject constructor(private val firebaseStorage: FirebaseFirestore, private val storage: FirebaseStorage) {

    // Registra los datos de un usuario en Firestore
    suspend fun registredUserData(user: User): Boolean {
        val usuarioMap = userToMap(user)
        return firebaseStorage.collection("usuarios").add(usuarioMap).isComplete
    }

    // Añade un libro a la colección de libros favoritos de un usuario
    suspend fun addBookUser(book: Book, id: String): Boolean {
        val bookMap = bookToMap(book)
        return firebaseStorage.collection("usuarios").document(id).collection("libros_favoritos")
            .add(bookMap).isComplete
    }


    // Elimina un libro de la colección de libros favoritos de un usuario
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


    // Obtiene el ID de un usuario por su email
    suspend fun getUserId(email: String): String? {
        val result =
            firebaseStorage.collection("usuarios").whereEqualTo("email", email).get().await()
        return result.documents.firstOrNull()?.id
    }


    // Obtiene la información de un usuario por su email y retorna un objeto User
    suspend fun getInfoUser(email: String): User? {
        val result =
            firebaseStorage.collection("usuarios").whereEqualTo("email", email).get().await()
        return result.documents.firstOrNull()?.toObject<User>()
    }


    // Obtiene la información de un libro por su nombre y retorna un objeto Book
    suspend fun getInfoBook(name: String): Book? {
        val result =
            firebaseStorage.collection("libros").whereEqualTo("titulo", name).get().await()
        return result.documents.firstOrNull()?.toObject<Book>()
    }



    // Obtiene los libros favoritos de un usuario y retorna una lista mutable de objetos Book.
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



    // Obtiene todos los libros disponibles y retorna una lista mutable de objetos Book.
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



    // Obtiene todas las fotos de perfiles de usuarios
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
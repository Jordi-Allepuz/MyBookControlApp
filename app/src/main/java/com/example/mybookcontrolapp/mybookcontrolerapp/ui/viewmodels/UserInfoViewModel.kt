package com.example.mybookcontrolapp.mybookcontrolerapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybookcontrolapp.mybookcontrolerapp.data.dataInfo.Book
import com.example.mybookcontrolapp.mybookcontrolerapp.data.dataInfo.User
import com.example.mybookcontrolapp.mybookcontrolerapp.data.sources.remote.AuthService
import com.example.mybookcontrolapp.mybookcontrolerapp.data.sources.remote.StorageService
import com.example.mybookcontrolapp.mybookcontrolerapp.domain.usecases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class UserInfoViewModel @Inject constructor(
    private val storageService: StorageService,
    private val authService: AuthService,
    private val useCases: UseCases
) :
    ViewModel() {

    private val _user = MutableLiveData<User>(null)
    val user: LiveData<User> = _user


    private val _userId = MutableLiveData<String>()
    val userId: LiveData<String> = _userId

    private val _book = MutableLiveData<Book>()
    val book: LiveData<Book> = _book

    private val _userBooks = MutableLiveData<MutableList<Book>>()
    val userBooks: LiveData<MutableList<Book>> = _userBooks

    private val _allBooks = MutableLiveData<MutableList<Book>>()
    val allBooks: LiveData<MutableList<Book>> = _allBooks


    // Método para obtener la información del usuario actual utilizando el email.
    fun getInfoUser() {
        viewModelScope.launch {
            _userId.value = authService.getCurrentUser()?.email
            val result = withContext(Dispatchers.IO) {
                storageService.getInfoUser(_userId.value!!)
            }
            if (result != null) {
                _user.value = result
                getUserId(result.email)
            } else {
                //error
            }
        }
    }


    // Método para obtener el ID del usuario a partir de su email.
    fun getUserId(email: String) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                storageService.getUserId(email)
            }
            _userId.value = result
            getFavoriteBooks(result!!)
        }
    }


    // Método para obtener los libros favoritos del usuario.
    fun getFavoriteBooks(id: String) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                storageService.getFavoriteBooks(id)
            }
            _userBooks.value = result
        }
    }


    // Método para obtener la información de un libro específico.
    fun getBookInfo(name: String, toInfo: () -> Unit) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                storageService.getInfoBook(name)
            }
            if (result != null) {
                _book.value = result
                toInfo()
            } else {
                //error
            }
        }
    }


    // Método para obtener todos los libros disponibles.
    fun getBookCollection(toList: () -> Unit) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                storageService.getAllBooks()
            }
            _allBooks.value = result
            toList()
        }
    }



    // Método para añadir un libro a los favoritos del usuario.
    fun addBookUser(book: Book, id: String, toUser: () -> Unit) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                storageService.addBookUser(book, id)
                _userBooks.value!!.add(book)
            }
            if (result != null) {
                toUser()
            } else {
                //
            }
        }
    }



    // Método para eliminar un libro de los favoritos del usuario.
    fun deleteBookUser(bookName: String, id: String, toUser: () -> Unit){
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                storageService.deleteBookUser(bookName, id)
            }
            if (result != null) {
                toUser()
            } else {
                //
            }
        }
    }


    // Método para actualizar el número de 'likes' de un libro.
    fun updateBookLikes(bookId: String, newLikes: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                storageService.updateBookLikes(bookId, newLikes)
            }
        }
    }


    // Método para visitar la tienda online.
    fun visitShop(url:String){
        viewModelScope.launch {
            useCases.executeShop(url)
        }
    }


    // Método para enviar un correo electrónico.
    fun sendEmail(emailAdress:String){
        viewModelScope.launch {
            useCases.executeEmail(emailAdress)
        }
    }



}
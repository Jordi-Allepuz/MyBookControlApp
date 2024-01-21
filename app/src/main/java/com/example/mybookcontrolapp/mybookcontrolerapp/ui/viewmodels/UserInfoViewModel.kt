package com.example.mybookcontrolapp.mybookcontrolerapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mybookcontrolapp.mybookcontrolerapp.data.dataInfo.Book
import com.example.mybookcontrolapp.mybookcontrolerapp.data.dataInfo.User
import com.example.mybookcontrolapp.mybookcontrolerapp.data.sources.remote.StorageService
import com.google.firebase.firestore.DocumentReference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class UserInfoViewModel @Inject constructor(private val storageService: StorageService) :
    ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    private val _book = MutableLiveData<Book>()
    val book: LiveData<Book> = _book

    private val _books = MutableLiveData<List<Book>>()
    val books: LiveData<List<Book>> = _books


    fun getInfoUser(email: String) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                storageService.getInfoUser(email)
            }
            if (result != null) {
                _user.value = result
            } else {
                //error
            }
        }
    }

    fun getLibro(id: String) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                storageService.getInfoBook(id)
            }
            if (result != null) {
                _book.value = result
            } else {
                //error
            }
        }
    }

    fun getLibros(user: User) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                user.libros_leidos.forEach {

                }
            }
        }
    }


//
//    fun guardarLibro(book: Book){
//        viewModelScope.launch {
//            val result = withContext(Dispatchers.IO){
//                storageService.registredInfoBook(book)
//            }
//        }
//    }


}
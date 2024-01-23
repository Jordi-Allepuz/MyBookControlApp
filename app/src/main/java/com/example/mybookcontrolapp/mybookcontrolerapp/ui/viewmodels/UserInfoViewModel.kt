package com.example.mybookcontrolapp.mybookcontrolerapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybookcontrolapp.mybookcontrolerapp.data.dataInfo.Book
import com.example.mybookcontrolapp.mybookcontrolerapp.data.dataInfo.User
import com.example.mybookcontrolapp.mybookcontrolerapp.data.sources.remote.AuthService
import com.example.mybookcontrolapp.mybookcontrolerapp.data.sources.remote.StorageService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class UserInfoViewModel @Inject constructor(
    private val storageService: StorageService,
    private val authService: AuthService
) :
    ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user


    private val _userId = MutableLiveData<String>()
    val userId: LiveData<String> = _userId

    private val _book = MutableLiveData<Book>()
    val book: LiveData<Book> = _book

    private val _books = MutableLiveData<MutableList<Book>>()
    val books: LiveData<MutableList<Book>> = _books

    init {
        val currentUserEmail = authService.getCurrentUser()?.email
        if (currentUserEmail != null) {
            getInfoUser(currentUserEmail)
        }
    }


    fun logOut(toLoginScreen: () -> Unit) {
        viewModelScope.launch {
            authService.logOut()
            toLoginScreen()
        }
    }


    fun getInfoUser(email: String) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                storageService.getInfoUser(email)
            }
            if (result != null) {
                _user.value = result
                getUserId(result.email)
            } else {
                //error
            }
        }
    }

    fun getUserId(email: String) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                storageService.getUserId(email)
            }
            _userId.value = result
            getFavoriteBooks(result!!)
        }
    }


    fun getFavoriteBooks(id: String) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                storageService.getFavoriteBooks(id)
            }
            _books.value = result
        }
    }

    fun getBookInfo(name: String, toInfo:() -> Unit) {
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




}
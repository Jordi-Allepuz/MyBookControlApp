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
            _userBooks.value = result
        }
    }

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

    fun getBookCollection(toList: () -> Unit) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                storageService.getAllBooks()
            }
            _allBooks.value = result
            toList()
        }
    }

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


    fun visitShop(url:String){
        viewModelScope.launch {
            useCases.executeShop(url)
        }
    }



    fun sendEmail(emailAdress:String){
        viewModelScope.launch {
            useCases.executeEmail(emailAdress)
        }
    }





}
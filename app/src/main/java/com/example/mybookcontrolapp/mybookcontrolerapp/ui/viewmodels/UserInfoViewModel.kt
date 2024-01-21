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
class UserInfoViewModel @Inject constructor(private val authService: AuthService, private val storageService: StorageService):ViewModel() {

    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> = _userName


    private val _age = MutableLiveData<String>()
    val age: LiveData<String> = _age

    private val _favoriteGenere = MutableLiveData<String>()
    val favoriteGenere: LiveData<String> = _favoriteGenere

    private val _readBooks = MutableLiveData<List<Book>>()
    val readBooks:LiveData<List<Book>> = _readBooks


    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    fun cargarInfoUser (userName: String){
        viewModelScope.launch {
            _isLoading.value= true
            val result = withContext(Dispatchers.IO){
                storageService.cargarInfoUser(userName)
            }
            if (result!=null){
                _userName.value= result.Name
                _age.value= result.Age
                _favoriteGenere.value= result.favoriteGenere
                _readBooks.value= result.books
            }else{
                //error
            }
            _isLoading.value= false
        }
    }



}
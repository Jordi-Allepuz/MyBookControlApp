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
class UserInfoViewModel @Inject constructor( private val storageService: StorageService):ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

//    init {
//        cargarInfoUser("valencianu@valencianu.com")
//    }

    fun cargarInfoUser (email:String){
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                storageService.cargarInfoUser(email)
            }
            if (result!=null){
                _user.value= result
            }else{
                //error
            }
        }
    }



}
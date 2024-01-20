package com.example.mybookcontrolapp.mybookcontrolerapp.ui.viewmodels


import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybookcontrolapp.Routes
import com.example.mybookcontrolapp.mybookcontrolerapp.data.sources.remote.AuthService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(private val authService: AuthService) : ViewModel() {

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email


    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _isLoginEnable = MutableLiveData<Boolean>()
    val isLoginEnable: LiveData<Boolean> = _isLoginEnable

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun onLoginChange(email: String, password: String) {
        _email.value = email
        _password.value = password
        _isLoginEnable.value = enableLogin(email, password)
    }

    fun enableLogin(email: String, password: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 6
    }


    fun login(email: String, password: String, toUserScreen: () -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true

            val result = withContext(Dispatchers.IO) {
                authService.login(email, password)
            }
            if (result != null) {
                toUserScreen()
            } else {
                //error
            }
            _isLoading.value = false
        }
    }


    fun signUp(email: String, password: String, toUserScreen: () -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            val result = withContext(Dispatchers.IO) {
                authService.signUp(email, password)
            }
            if (result != null) {
                toUserScreen()
            } else {
                //error
            }
            _isLoading.value = false
        }
    }


//    private fun isUserLogged():Boolean{
//        return authService.isUserLogged()
//    }
//
//
//    fun checkDestination(){
//        val isUserLogged: Boolean = isUserLogged()
//        if (isUserLogged){
//            Routes.UserBooksScreen.route
//        }else{
//            Routes.LoginScreen.route
//        }
//    }
//





}


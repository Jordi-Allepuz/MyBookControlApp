package com.example.mybookcontrolapp.mybookcontrolerapp.ui.viewmodels

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybookcontrolapp.mybookcontrolerapp.data.dataInfo.User
import com.example.mybookcontrolapp.mybookcontrolerapp.data.sources.remote.AuthService
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val authService: AuthService) : ViewModel() {


    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> = _userName

    private val _password1 = MutableLiveData<String>()
    val password1: LiveData<String> = _password1

    private val _password2 = MutableLiveData<String>()
    val password2: LiveData<String> = _password2

    private val _age = MutableLiveData<String>()
    val age: LiveData<String> = _age

    private val _favoriteGenere = MutableLiveData<String>()
    val favoriteGenere: LiveData<String> = _favoriteGenere

    private val _isSignUpEnable = MutableLiveData<Boolean>()
    val isSignUpEnable: LiveData<Boolean> = _isSignUpEnable

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    fun onLoginChange(
        email: String,
        userName: String,
        password1: String,
        password2: String,
        age: String,
        favoriteGenere: String
    ) {
        _email.value = email
        _userName.value = userName
        _password1.value = password1
        _password2.value = password2
        _age.value = age
        _favoriteGenere.value = favoriteGenere
        _isSignUpEnable.value =
            enableSignUp(email, userName, password1, password2, age, favoriteGenere)
    }

    fun enableSignUp(
        email: String,
        userName: String,
        password1: String,
        password2: String,
        age: String,
        favoriteGenere: String
    ): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email)
            .matches() && password1.length > 6 && password2.length > 6 &&
                password1 == password2
    }


    fun signUp(
        userName: String,
        email: String,
        password: String,
        age: String,
        favoriteGenere: String,
        toUserScreen: () -> Unit
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            val result = withContext(Dispatchers.IO) {
                authService.signUp(email, password)


            }
            if (result != null) {
                authService.registredUserData(User(userName, age, email, favoriteGenere))
                toUserScreen()
            } else {
                //error
            }
            _isLoading.value = false
        }
    }


}
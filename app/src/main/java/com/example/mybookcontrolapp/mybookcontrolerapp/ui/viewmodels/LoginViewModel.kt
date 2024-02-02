package com.example.mybookcontrolapp.mybookcontrolerapp.ui.viewmodels



import android.util.Log
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
            try{
                val result = withContext(Dispatchers.IO) {
                    authService.login(email, password)
                }
                if (result != null) {
                    toUserScreen()
                    _email.value = ""
                    _password.value = ""
                    _isLoginEnable.value= false
                    Log.i("Current User Email", "${authService.getCurrentUser()!!.email}")
                } else {
                   //
                }
            }catch (e:Exception){
            //
            }
            _isLoading.value = false
        }
    }



    fun logOut(toLoginScreen: () -> Unit) {
        viewModelScope.launch {
            authService.logOut()
            _email.value = ""
            _password.value = ""

            toLoginScreen()
        }
    }



    fun isUserLogged():Boolean{
        return  authService.isUserLogged()
    }


    fun checkDestination():String{
        val isUserLogged = isUserLogged()
        return if (isUserLogged){
            Routes.UserInfoScreen.route
        }else{
            Routes.LoginScreen.route
        }
    }



}


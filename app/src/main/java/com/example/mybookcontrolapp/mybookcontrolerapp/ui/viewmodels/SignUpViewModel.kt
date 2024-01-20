//package com.example.mybookcontrolapp.mybookcontrolerapp.ui.viewmodels
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.mybookcontrolapp.mybookcontrolerapp.data.sources.remote.AuthService
//import dagger.hilt.android.HiltAndroidApp
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//import javax.inject.Inject
//
//@HiltViewModel
//class SignUpViewModel @Inject constructor(private val authService: AuthService) :ViewModel() {
//
//    private val _email = MutableLiveData<String>()
//    val email: LiveData<String> = _email
//
//
//    private val _password = MutableLiveData<String>()
//    val password: LiveData<String> = _password
//
//    private val _isLoading = MutableLiveData<Boolean>()
//    val isLoading: LiveData<Boolean> = _isLoading
//
//
//    fun signUp(email:String, password:String){
//        viewModelScope.launch {
//            _isLoading.value= true
//            val result= withContext(Dispatchers.IO){
//                authService.signUp(email, password)
//            }
//            _isLoading.value= false
//        }
//
//    }
//
//
//}
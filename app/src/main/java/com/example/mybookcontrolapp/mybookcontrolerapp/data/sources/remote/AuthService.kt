package com.example.mybookcontrolapp.mybookcontrolerapp.data.sources.remote


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthService @Inject constructor( private val firebaseAuth: FirebaseAuth) {

    suspend fun login (email:String, password:String): FirebaseUser? {
        return firebaseAuth.signInWithEmailAndPassword(email, password).await().user
    }

    suspend  fun signUp(email: String, password: String): FirebaseUser? {
        return firebaseAuth.createUserWithEmailAndPassword(email, password).await().user
    }

    fun logOut(){
        return firebaseAuth.signOut()
    }

    fun isUserLogged():Boolean{
        return getCurrentUser() != null
    }

    fun getCurrentUser() = firebaseAuth.currentUser

}
package com.example.mybookcontrolapp.mybookcontrolerapp.data.sources.remote


import com.example.mybookcontrolapp.mybookcontrolerapp.data.dataInfo.User
import com.example.mybookcontrolapp.mybookcontrolerapp.data.mappers.UserToMap
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthService @Inject constructor( private val firebaseAuth: FirebaseAuth) {

    suspend fun login (email:String, password:String): FirebaseUser? {
        return firebaseAuth.signInWithEmailAndPassword(email, password).await().user
    }

    suspend  fun signUp(email: String, password: String): FirebaseUser? {
        return firebaseAuth.createUserWithEmailAndPassword(email, password).await().user
    }

    suspend fun logOut(){
        return firebaseAuth.signOut()
    }

    fun isUserLogged():Boolean{
        return firebaseAuth.currentUser != null
    }

    suspend fun registredUserData(user:User ){
        val usuarioMap = UserToMap(user)
        FirebaseFirestore.getInstance().collection("usuarios").add(usuarioMap)
    }

}
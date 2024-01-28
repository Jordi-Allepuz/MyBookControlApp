package com.example.mybookcontrolapp.mybookcontrolerapp.data.sources.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn (SingletonComponent::class)
object NetWorkModule {

    @Singleton
    @Provides
    fun provideFirebaseAuth()= FirebaseAuth.getInstance()

    @Singleton
    @Provides
    fun provideFirebaseStorage()= FirebaseFirestore.getInstance()

    @Singleton
    @Provides
    fun provideStorage():FirebaseStorage = Firebase.storage

}
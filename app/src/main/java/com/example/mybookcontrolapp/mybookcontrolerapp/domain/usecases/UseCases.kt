package com.example.mybookcontrolapp.mybookcontrolerapp.domain.usecases

import com.example.mybookcontrolapp.mybookcontrolerapp.domain.repositories.EmailRepositoryImpl
import com.example.mybookcontrolapp.mybookcontrolerapp.domain.repositories.ShopRepositoryImpl
import javax.inject.Inject

class UseCases  @Inject constructor(private val shopRepositoryImpl: ShopRepositoryImpl, private val emailRepositoryImpl: EmailRepositoryImpl){

    suspend fun executeShop (url:String){
        shopRepositoryImpl.visitShop(url)
    }

    suspend fun executeEmail (emailAddress:String){
        emailRepositoryImpl.visitEmail(emailAddress)
    }

}



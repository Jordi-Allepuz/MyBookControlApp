package com.example.mybookcontrolapp.mybookcontrolerapp.domain.usecases

import com.example.mybookcontrolapp.mybookcontrolerapp.data.repositories.PokemonRepositoryImpl
import com.example.mybookcontrolapp.mybookcontrolerapp.domain.repositories.ShopRepository
import javax.inject.Inject

class UseCases  @Inject constructor(private val repositoryImpl: PokemonRepositoryImpl){

    suspend fun execute (url:String){
        repositoryImpl.visitShop(url)
    }

}



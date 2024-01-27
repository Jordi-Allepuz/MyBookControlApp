package com.example.mybookcontrolapp.mybookcontrolerapp.data.repositories

import com.example.mybookcontrolapp.mybookcontrolerapp.data.sources.local.LocalDataSource
import com.example.mybookcontrolapp.mybookcontrolerapp.domain.repositories.ShopRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(private val localDataSource: LocalDataSource) :ShopRepository {

    override suspend fun visitShop(url: String) {
         withContext(Dispatchers.IO){
            localDataSource.openWebPage(url)
        }
    }

}
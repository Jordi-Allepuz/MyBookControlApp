package com.example.mybookcontrolapp.mybookcontrolerapp.domain.repositories

import com.example.mybookcontrolapp.mybookcontrolerapp.data.sources.local.LocalDataSource
import com.example.mybookcontrolapp.mybookcontrolerapp.domain.repositories.ShopRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

// Implementación del repositorio para operaciones relacionadas visitar página web.
class ShopRepositoryImpl @Inject constructor(private val localDataSource: LocalDataSource) :ShopRepository {


    // Método sobreescrito para abrir una URL en el navegador web del dispositivo.
    override suspend fun visitShop(url: String) {
         withContext(Dispatchers.IO){
            localDataSource.openWebPage(url)
        }
    }

}
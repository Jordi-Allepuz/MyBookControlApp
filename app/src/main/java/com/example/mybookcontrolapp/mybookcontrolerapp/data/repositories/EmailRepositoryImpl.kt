package com.example.mybookcontrolapp.mybookcontrolerapp.data.repositories

import com.example.mybookcontrolapp.mybookcontrolerapp.data.sources.local.LocalDataSource
import com.example.mybookcontrolapp.mybookcontrolerapp.domain.repositories.EmailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


// Implementación  de EmailRepository para manejar operaciones de email.
class EmailRepositoryImpl @Inject constructor(private val localDataSource: LocalDataSource): EmailRepository{

    // Sobrecarga de la función visitEmail para enviar un email a la dirección especificada.
    override suspend fun visitEmail(emailAddress: String) {
        withContext(Dispatchers.IO){
            localDataSource.sendEmail(emailAddress)
        }
    }
}


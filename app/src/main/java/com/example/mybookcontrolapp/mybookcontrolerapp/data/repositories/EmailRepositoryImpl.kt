package com.example.mybookcontrolapp.mybookcontrolerapp.data.repositories

import com.example.mybookcontrolapp.mybookcontrolerapp.data.sources.local.LocalDataSource
import com.example.mybookcontrolapp.mybookcontrolerapp.domain.repositories.EmailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EmailRepositoryImpl @Inject constructor(private val localDataSource: LocalDataSource): EmailRepository{


    override suspend fun visitEmail(emailAddress: String) {
        withContext(Dispatchers.IO){
            localDataSource.sendEmail(emailAddress)
        }
    }


}


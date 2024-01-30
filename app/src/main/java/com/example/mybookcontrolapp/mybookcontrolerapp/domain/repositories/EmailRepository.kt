package com.example.mybookcontrolapp.mybookcontrolerapp.domain.repositories

interface EmailRepository {
    suspend fun visitEmail(emailAddress: String)
}

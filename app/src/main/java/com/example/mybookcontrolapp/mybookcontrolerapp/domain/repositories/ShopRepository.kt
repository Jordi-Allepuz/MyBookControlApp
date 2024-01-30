package com.example.mybookcontrolapp.mybookcontrolerapp.domain.repositories


interface ShopRepository {
    suspend fun visitShop(url:String)
}
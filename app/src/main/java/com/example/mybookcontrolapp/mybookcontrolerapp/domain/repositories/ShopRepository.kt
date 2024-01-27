package com.example.mybookcontrolapp.mybookcontrolerapp.domain.repositories

import android.content.Context

interface ShopRepository {
    suspend fun visitShop(url:String)
}
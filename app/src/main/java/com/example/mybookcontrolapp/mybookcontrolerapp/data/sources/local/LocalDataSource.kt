package com.example.mybookcontrolapp.mybookcontrolerapp.data.sources.local

import android.content.Context
import android.content.Intent
import android.net.Uri
import dagger.hilt.android.qualifiers.ApplicationContext

import javax.inject.Inject

class LocalDataSource @Inject constructor(@ApplicationContext private val context: Context) {

    fun openWebPage( url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }


}
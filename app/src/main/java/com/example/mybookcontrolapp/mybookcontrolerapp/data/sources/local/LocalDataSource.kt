package com.example.mybookcontrolapp.mybookcontrolerapp.data.sources.local

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import dagger.hilt.android.qualifiers.ApplicationContext

import javax.inject.Inject

class LocalDataSource @Inject constructor(@ApplicationContext private val context: Context) {

    // Método para abrir una página web en el navegador predeterminado del dispositivo.
    fun openWebPage( url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    // Método para enviar un correo electrónico a la dirección proporcionada.
    @SuppressLint("QueryPermissionsNeeded")
    fun sendEmail(emailAddress: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:$emailAddress")
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
            context.startActivity(intent)
    }
}



package com.example.mybookcontrolapp.mybookcontrolerapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.mybookcontrolapp.mybookcontrolerapp.data.dataInfo.Book
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.viewmodels.UserInfoViewModel

@Composable
fun BookInfoScreen(
    userInfoViewModel: UserInfoViewModel,
    navigationController: NavHostController
) {

    val book: Book? by userInfoViewModel.book.observeAsState()


    if (book == null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BookInfo(book!!)
        }
    }
}


@Composable
fun BookInfo(book: Book) {
    Column(Modifier.fillMaxWidth()) {
        Text(text = "Titulo Libro: ${book.titulo}")
        Text(text = "Editorial: ${book.editorial}")
        Text(text = "Genero: ${book.genero}")
        Text(text = "Genero: ${book.autor}")
        Text(text = "Isbn: ${book.isbn}")
        Image(painter = rememberImagePainter(data = book.portada), contentDescription = null )
    }
}

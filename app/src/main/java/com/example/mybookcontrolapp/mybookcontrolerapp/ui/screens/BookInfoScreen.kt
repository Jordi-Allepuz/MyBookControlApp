package com.example.mybookcontrolapp.mybookcontrolerapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.mybookcontrolapp.mybookcontrolerapp.data.dataInfo.Book
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.components.ModalDrawer
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.components.TopBar
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.viewmodels.LoginViewModel
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.viewmodels.UserInfoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BookInfoScreen(
    userInfoViewModel: UserInfoViewModel,
    loginViewModel: LoginViewModel,
    navigationController: NavHostController
) {

    val coroutina = rememberCoroutineScope()
    val estadoDrawer = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerContent = { ModalDrawer(estadoDrawer = estadoDrawer, coroutina = coroutina, userInfoViewModel, navigationController) },
        gesturesEnabled = false,
        drawerState = estadoDrawer
    ) {
        Scaffold(
            topBar = { TopBar("BOOK INFO", estadoDrawer, coroutina, loginViewModel,userInfoViewModel, navigationController, badgedOn = true) },
            content = { paddingValues ->
                BookInfoContent(
                    userInfoViewModel,
                    navigationController,
                    paddingValues
                )
            },
            bottomBar = { }
        )
    }

}





@Composable
fun BookInfoContent(
    userInfoViewModel: UserInfoViewModel,
    navigationController: NavHostController,
    paddingValues: PaddingValues
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
                .fillMaxSize().padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BookInfo(book!!)
        }
    }
}


@OptIn(ExperimentalCoilApi::class)
@Composable
fun BookInfo(book: Book) {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Titulo Libro: ${book.titulo}", color = Color.Black)
        Row() {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f)
            ) {

                Text(text = "Editorial: ${book.editorial}", color = Color.Black)
                Text(text = "Genero: ${book.genero}", color = Color.Black)
                Text(text = "Genero: ${book.autor}", color = Color.Black)
                Text(text = "Isbn: ${book.isbn}", color = Color.Black)
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f)
            ) {
                Image(
                    painter = rememberImagePainter(data = book.portada),
                    contentDescription = null,
                    modifier = Modifier.width(200.dp), contentScale = ContentScale.Crop,
                )
            }

        }
    }
}



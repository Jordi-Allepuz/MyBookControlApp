package com.example.mybookcontrolapp.mybookcontrolerapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.mybookcontrolapp.mybookcontrolerapp.data.dataInfo.Book
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.components.BottomBar
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.components.CardBookInfo
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.components.ModalDrawer
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.components.TopBar
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.viewmodels.LoginViewModel
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.viewmodels.UserInfoViewModel
import kotlin.math.log


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CollectionBookScreen(
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
            topBar = {
                TopBar(
                    titulo = "LISTADO LIBROS",
                    estadoDrawer,
                    coroutina,
                    loginViewModel,
                    userInfoViewModel,
                    navigationController, badgedOn = false
                )
            },
            content = { paddingValues ->
                CollectionBookContent(
                    userInfoViewModel,
                    navigationController,
                    paddingValues
                )
            },
            bottomBar = { BottomBar(navigationController, loginViewModel)}
        )
    }

}




@Composable
fun CollectionBookContent(
    userInfoViewModel: UserInfoViewModel,
    navigationController: NavHostController,
    paddingValues: PaddingValues
) {
    val books: List<Book>? by userInfoViewModel.allBooks.observeAsState()

    if (books == null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
        ) {
            LazyColumn(content = {
                items(books!!) { book ->
                    CardBookInfo(book, userInfoViewModel, navigationController)
                }
            })
        }
    }
}


package com.example.mybookcontrolapp.mybookcontrolerapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mybookcontrolapp.Routes
import com.example.mybookcontrolapp.mybookcontrolerapp.data.dataInfo.Book
import com.example.mybookcontrolapp.mybookcontrolerapp.data.dataInfo.User
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.components.BottomBar
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.components.CardAddBook
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.components.CardBook
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.components.ModalDrawer
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.components.TopBar
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.viewmodels.LoginViewModel
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.viewmodels.UserInfoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UserInfoScreen(
    userInfoViewModel: UserInfoViewModel,
    loginViewModel: LoginViewModel,
    navigationController: NavHostController
) {

    val coroutina = rememberCoroutineScope()
    val estadoDrawer = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerContent = { ModalDrawer(estadoDrawer = estadoDrawer, coroutina = coroutina) },
        gesturesEnabled = false,
        drawerState = estadoDrawer
    ) {
        Scaffold(
            topBar = {
                TopBar(
                    "USER INFO",
                    estadoDrawer,
                    coroutina,
                    userInfoViewModel,
                    loginViewModel,
                    navigationController,
                    badgedOn = false
                )
            },
            content = { paddingValues ->
                UserInfoContent(
                    userInfoViewModel,
                    loginViewModel,
                    navigationController,
                    paddingValues
                )
            },
            bottomBar = { BottomBar() }
        )
    }

}


@Composable
fun UserInfoContent(
    userInfoViewModel: UserInfoViewModel,
    loginViewModel: LoginViewModel,
    navigationController: NavHostController,
    paddingValues: PaddingValues
) {

    val user: User? by userInfoViewModel.user.observeAsState()
    val books: List<Book>? by userInfoViewModel.userBooks.observeAsState()

    LaunchedEffect(Unit) {
        userInfoViewModel.getInfoUser()
    }

    if (user == null || books == null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UserInfo(user!!)
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            )
            ReadBooks(books!!, user!!.name, userInfoViewModel, navigationController)
        }
    }
}


@Composable
fun UserInfo(user: User) {
    Column(Modifier.fillMaxWidth()) {
        Text(text = "Nombre Usuario: ${user.name}", color = Color.Black)
        Text(text = "Edad Usuario: ${user.age}", color = Color.Black)
        Text(text = "Genero de lectura favorito: ${user.genero_favorito}", color = Color.Black)
    }
}


@Composable
fun ReadBooks(
    books: List<Book>,
    userName: String,
    userInfoViewModel: UserInfoViewModel,
    navigationController: NavHostController
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "LIBROS FAVORITOS DE ${userName.uppercase()}", color = Color.Black,fontWeight = FontWeight.ExtraBold)
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp),
            content = {
                items(books) { book ->
                    CardBook(
                        book,
                        userInfoViewModel,
                        navigationController
                    )
                }
                item {
                    CardAddBook(userInfoViewModel, navigationController)
                }
            })
    }
}









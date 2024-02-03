package com.example.mybookcontrolapp.mybookcontrolerapp.ui.screens.userInfo

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.mybookcontrolapp.mybookcontrolerapp.data.dataInfo.Book
import com.example.mybookcontrolapp.mybookcontrolerapp.data.dataInfo.User
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.components.BottomBar
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.components.CardAddBook
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.components.CardBook
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.components.ModalDrawer
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.components.TopBar
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.viewmodels.LoginViewModel
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.viewmodels.UserInfoViewModel
import com.example.mybookcontrolapp.ui.theme.caveat
import com.example.mybookcontrolapp.ui.theme.greatvibes
import com.example.mybookcontrolapp.ui.theme.yellowtail

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
        drawerContent = { ModalDrawer(estadoDrawer = estadoDrawer, coroutina = coroutina, userInfoViewModel, navigationController) },
        gesturesEnabled = false,
        drawerState = estadoDrawer
    ) {
        Scaffold(
            topBar = {
                TopBar(
                    "USER INFO",
                    estadoDrawer,
                    coroutina,
                    loginViewModel,
                    userInfoViewModel,
                    navigationController,
                    badgedOn = false
                )
            },
            content = { paddingValues ->
                UserInfoContent(
                    userInfoViewModel,
                    navigationController,
                    paddingValues
                )
            },
            bottomBar = { BottomBar(navigationController, loginViewModel) }
        )
    }

}


@Composable
fun UserInfoContent(
    userInfoViewModel: UserInfoViewModel,
    navigationController: NavHostController,
    paddingValues: PaddingValues
) {
    val brush = Brush.linearGradient(listOf(MaterialTheme.colorScheme.background, Color(0xFFD53706)))

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
                .fillMaxSize().verticalScroll(rememberScrollState())
                .background(brush)
                .padding(paddingValues)
                .padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UserInfo(user!!)
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
            )
            ReadBooks(books!!, user!!.name, userInfoViewModel, navigationController)
        }
    }
}


@OptIn(ExperimentalCoilApi::class)
@Composable
fun UserInfo(user: User) {
    Column(Modifier.fillMaxWidth().border(2.dp, Color.Black, RoundedCornerShape(4.dp)).padding(9.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = rememberImagePainter(data = user.foto), contentDescription = null, modifier = Modifier.size(120.dp) )
        Text(text = "Nombre: ${user.name}", color = Color.Black,fontWeight = FontWeight.ExtraBold)
        Text(text = "Edad: ${user.age} años", color = Color.Black,fontWeight = FontWeight.ExtraBold)
        Text(text = "Genero de lectura favorito: ${user.genero_favorito}", color = Color.Black,fontWeight = FontWeight.ExtraBold)
    }
}


@Composable
fun ReadBooks(
    books: List<Book>,
    userName: String,
    userInfoViewModel: UserInfoViewModel,
    navigationController: NavHostController
) {
    Column(modifier= Modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "LIBROS FAVORITOS DE ${userName.uppercase()}", color = Color.Black,fontWeight = FontWeight.ExtraBold, fontFamily = yellowtail)
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
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









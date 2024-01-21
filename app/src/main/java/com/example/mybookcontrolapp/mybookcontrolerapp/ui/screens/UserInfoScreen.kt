package com.example.mybookcontrolapp.mybookcontrolerapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.mybookcontrolapp.Routes
import com.example.mybookcontrolapp.mybookcontrolerapp.data.dataInfo.Book
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.viewmodels.LoginViewModel
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.viewmodels.UserInfoViewModel
import kotlin.math.log

@Composable
fun UserInfoScreen(loginViewModel: LoginViewModel,userInfoViewModel: UserInfoViewModel, navigationController: NavHostController) {
    val userName: String by userInfoViewModel.userName.observeAsState(initial = "")
    val age: String by userInfoViewModel.age.observeAsState(initial = "")
    val favoriteGenere: String by userInfoViewModel.favoriteGenere.observeAsState(initial = "")
    userInfoViewModel.cargarInfoUser(loginViewModel.email.value!!)

    val isLoading: Boolean by loginViewModel.isLoading.observeAsState(false)
    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
        }else{
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UserInfo(userName, age, favoriteGenere)
            ReadBooks(userInfoViewModel)
            Botones(userInfoViewModel, loginViewModel , navigationController)
        }
    }

}



@Composable
fun UserInfo(userName:String, age:String, generoFavorito:String) {
    Column(Modifier.fillMaxWidth()) {
        Text(text ="Nombre Usuario: $userName" )
        Text(text ="Edad Usuario: $age" )
        Text(text = "Genero de lectura favorito: $generoFavorito")

    }
}


@Composable
fun ReadBooks(userInfoViewModel: UserInfoViewModel){
    val books: List<Book> by userInfoViewModel.readBooks.observeAsState(initial = emptyList())
    LazyRow(content = {items(books) {book -> CardBooks(book)} } )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardBooks(book: Book){
    Card(onClick = { /*TODO*/ }) {
        Column() {
//            Image(imageVector = , contentDescription = )
            Text(text = "Libro" )
        }
    }
}


@Composable
fun Botones(
    userInforViewModel: UserInfoViewModel,
    loginViewModel: LoginViewModel,
    navigationController: NavHostController
) {
    Row() {
        Button(onClick = {
            loginViewModel.logOut()
            navigationController.navigate(Routes.LoginScreen.route)
        }) {
            Text(text = "LOG OUT")
        }
    }
}



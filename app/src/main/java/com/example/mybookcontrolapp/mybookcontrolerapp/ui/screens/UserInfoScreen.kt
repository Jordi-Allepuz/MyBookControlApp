package com.example.mybookcontrolapp.mybookcontrolerapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.mybookcontrolapp.Routes
import com.example.mybookcontrolapp.mybookcontrolerapp.data.dataInfo.Book
import com.example.mybookcontrolapp.mybookcontrolerapp.data.dataInfo.User
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.viewmodels.LoginViewModel
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.viewmodels.UserInfoViewModel

@Composable
fun UserInfoScreen(
    userInfoViewModel: UserInfoViewModel,
    loginViewModel: LoginViewModel,
    navigationController: NavHostController
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
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UserInfo(user!!)
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
            ReadBooks(books!!, userInfoViewModel, navigationController)
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
            Botones(userInfoViewModel, loginViewModel, navigationController)
        }
    }
}


@Composable
fun UserInfo(user: User) {
    Column(Modifier.fillMaxWidth()) {
        Text(text = "Nombre Usuario: ${user.name}")
        Text(text = "Edad Usuario: ${user.age}")
        Text(text = "Genero de lectura favorito: ${user.genero_favorito}")
    }
}


@Composable
fun ReadBooks(
    books: List<Book>,
    userInfoViewModel: UserInfoViewModel,
    navigationController: NavHostController
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp),
        content = {
            items(books) { book ->
                CardBooks(
                    book,
                    userInfoViewModel,
                    navigationController
                )
            }
            item {
                IconButton(onClick = {
                    userInfoViewModel.getBookCollection {
                        navigationController.navigate(
                            Routes.CollectionBookScreen.route
                        )
                    }
                }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                }
            }
        })

}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalCoilApi::class)
@Composable
fun CardBooks(
    book: Book,
    userInfoViewModel: UserInfoViewModel,
    navigationController: NavHostController
) {
    Card(onClick = {
        userInfoViewModel.getBookInfo(book.titulo) {
            navigationController.navigate(
                Routes.BookInfoScreen.route
            )
        }
    }, modifier = Modifier.padding(horizontal = 20.dp)) {
        Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center) {
            Image(
                painter = rememberImagePainter(data = book.portada),
                contentDescription = null,
                modifier = Modifier.width(200.dp), contentScale = ContentScale.Crop,
            )
            Text(
                text = book.titulo,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}


@Composable
fun Botones(
    userInfoViewModel: UserInfoViewModel,
    loginViewModel: LoginViewModel,
    navigationController: NavHostController
) {
    Row() {
        Button(onClick = {
            loginViewModel.logOut {
                navigationController.navigate(Routes.LoginScreen.route)
            }
        }) {
            Text(text = "LOG OUT")
        }
    }
}




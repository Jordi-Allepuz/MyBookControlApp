package com.example.mybookcontrolapp.mybookcontrolerapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.mybookcontrolapp.Routes
import com.example.mybookcontrolapp.mybookcontrolerapp.data.dataInfo.Book
import com.example.mybookcontrolapp.mybookcontrolerapp.data.dataInfo.User
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.viewmodels.LoginViewModel
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.viewmodels.UserInfoViewModel

@Composable
fun UserInfoScreen(
    userInfoViewModel: UserInfoViewModel,
    navigationController: NavHostController
) {

    val user: User? by userInfoViewModel.user.observeAsState()

    if (user == null) {
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
//            ReadBooks(user!!, userInfoViewModel)
            Botones(userInfoViewModel, navigationController)
        }
    }
}


@Composable
fun UserInfo(user: User) {
    Column(Modifier.fillMaxWidth()) {
        Text(text = "Nombre Usuario: ${user.name}")
        Text(text = "Edad Usuario: ${user.age}")
        Text(text = "Genero de lectura favorito: ${user.genero_favorito}")
        Text(text = user.libros_leidos.size.toString())
    }
}


//@Composable
//fun ReadBooks(user: User, userInfoViewModel: UserInfoViewModel) {
//    val book: Book? by userInfoViewModel.book.observeAsState()
//
//    Column() {
//        user.libros_leidos.forEach {
//            userInfoViewModel.getLibro(it)
//            if (book== null){
//                CircularProgressIndicator()
//            }else{
//                Text(text = book!!.autor )
//            }
//
//        }
//    }
//    LazyRow(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(200.dp),
//        content = { items(books) { book -> CardBooks(book) } })
//}


//@OptIn(ExperimentalMaterial3Api::class, ExperimentalCoilApi::class)
//@Composable
//fun CardBooks(book: Book) {
//    Card(onClick = { /*TODO*/ }) {
//        Column() {
//            Image(
//                painter = rememberImagePainter(data = book.bookPortada),
//                contentDescription = null,
//                modifier = Modifier.width(100.dp),
//                contentScale = ContentScale.Crop
//            )
//            Text(text = "Libro")
//        }
//    }
//}


@Composable
fun Botones(
    userInfoViewModel: UserInfoViewModel,
    navigationController: NavHostController
) {
    Row() {
        Button(onClick = {
            userInfoViewModel.logOut { navigationController.navigate(Routes.LoginScreen.route) }
        }) {
            Text(text = "LOG OUT")
        }
    }
}



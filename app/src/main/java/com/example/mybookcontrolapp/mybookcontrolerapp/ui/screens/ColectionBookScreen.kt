package com.example.mybookcontrolapp.mybookcontrolerapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.mybookcontrolapp.Routes
import com.example.mybookcontrolapp.mybookcontrolerapp.data.dataInfo.Book
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.viewmodels.UserInfoViewModel
import okhttp3.Route


@OptIn(ExperimentalCoilApi::class)
@Composable
fun CollectionBookScreen(
    userInfoViewModel: UserInfoViewModel,
    navigationController: NavHostController
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


        Text(text = books!!.size.toString())
        LazyColumn(content = {
            items(books!!) { book ->
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clickable {
                        userInfoViewModel.addBookUser(
                            book,
                            userInfoViewModel.userId.value!!.toString()
                        ) {
                            navigationController.navigate(
                                Routes.UserInfoScreen.route
                            )
                        }
                    }) {
                    Text(text = book.titulo)
                    Text(text = book.editorial)
                    Image(
                        painter = rememberImagePainter(data = book.portada),
                        contentDescription = null,
                        modifier = Modifier.width(200.dp), contentScale = ContentScale.Crop,
                    )
                }

            }
        })
    }
}
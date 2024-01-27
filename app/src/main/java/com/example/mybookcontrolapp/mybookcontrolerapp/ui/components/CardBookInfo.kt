package com.example.mybookcontrolapp.mybookcontrolerapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.mybookcontrolapp.Routes
import com.example.mybookcontrolapp.mybookcontrolerapp.data.dataInfo.Book
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.viewmodels.UserInfoViewModel


@OptIn(ExperimentalCoilApi::class)
@Composable
fun CardBookInfo(
    book: Book,
    userInfoViewModel: UserInfoViewModel,
    navigationController: NavHostController,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .padding(vertical = 16.dp, horizontal = 20.dp)
            .clickable {
                userInfoViewModel.addBookUser(
                    book,
                    userInfoViewModel.userId.value!!.toString()
                ) {
                    navigationController.navigate(
                        Routes.UserInfoScreen.route
                    )
                }
            }, shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = book.titulo)
            Row() {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f)
                ) {

                    Text(text = book.editorial)
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
}

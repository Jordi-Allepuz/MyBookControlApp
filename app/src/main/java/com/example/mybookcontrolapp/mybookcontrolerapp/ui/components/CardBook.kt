package com.example.mybookcontrolapp.mybookcontrolerapp.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.mybookcontrolapp.Routes
import com.example.mybookcontrolapp.mybookcontrolerapp.data.dataInfo.Book
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.viewmodels.UserInfoViewModel
import com.example.mybookcontrolapp.ui.theme.greatvibes
import com.example.mybookcontrolapp.ui.theme.kaushanscript
import com.example.mybookcontrolapp.ui.theme.yellowtail


@OptIn(ExperimentalMaterial3Api::class, ExperimentalCoilApi::class)
@Composable
fun CardBook(
    book: Book,
    userInfoViewModel: UserInfoViewModel,
    navigationController: NavHostController
) {

    val userId: String? by userInfoViewModel.userId.observeAsState()

    var isVisible by remember { mutableStateOf(true) }

    AnimatedVisibility(
        visible = isVisible,
        exit = shrinkOut(animationSpec = tween(durationMillis = 500)),
    ) {
        Card(
            onClick = {
                userInfoViewModel.getBookInfo(book.titulo) {
                    navigationController.navigate(
                        Routes.BookInfoScreen.route
                    )
                }
            },
            modifier = Modifier
                .width(250.dp)
                .height(400.dp)
                .padding(horizontal = 20.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary)
        ) {
            Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center) {
                Image(
                    painter = rememberImagePainter(data = book.portada),
                    contentDescription = null,
                    modifier = Modifier
                        .width(220.dp)
                        .weight(5f),
                    contentScale = ContentScale.Crop,
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                )
                Text(
                    text = book.titulo,
                    textAlign = TextAlign.Center,
                    fontFamily = kaushanscript,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.5f),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black
                )
                IconButton(
                    onClick = {
                        isVisible = false
                        userInfoViewModel.deleteBookUser(
                            book.titulo, userId!!
                        ) { navigationController.navigate(Routes.UserInfoScreen.route) }
                    }, modifier = Modifier
                        .weight(0.5f)
                        .align(Alignment.End)
                        .padding(4.dp)
                ) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "null")
                }
            }
        }

    }

}


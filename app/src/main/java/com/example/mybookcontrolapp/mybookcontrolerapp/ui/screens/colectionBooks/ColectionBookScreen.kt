package com.example.mybookcontrolapp.mybookcontrolerapp.ui.screens.colectionBooks

import android.annotation.SuppressLint
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mybookcontrolapp.R
import com.example.mybookcontrolapp.mybookcontrolerapp.data.dataInfo.Book
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.components.BottomBar
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.components.CardBookInfo
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.components.ModalDrawer
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.components.TopBar
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.viewmodels.LoginViewModel
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.viewmodels.UserInfoViewModel


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CollectionBookScreen(
    userInfoViewModel: UserInfoViewModel,
    loginViewModel: LoginViewModel,
    navigationController: NavHostController
) {

    val coroutina = rememberCoroutineScope() // Crea un scope de corutina para lanzar tareas asíncronas.
    val estadoDrawer = rememberDrawerState(initialValue = DrawerValue.Closed) // Estado inicial del drawer lateral.

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

    val lazyListState = rememberLazyListState()
    var scrolled = 0f
    var previousOffset = 0

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
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
        ) {
            LazyColumn(state = lazyListState,content = {
                item {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(225.dp)
                            .padding(top = 6.dp)
                            .graphicsLayer {
                                scrolled += lazyListState.firstVisibleItemScrollOffset - previousOffset
                                translationY = scrolled * 0.5f
                                previousOffset = lazyListState.firstVisibleItemScrollOffset
                            },
                        contentScale = ContentScale.Crop,
                        painter = painterResource(id = R.drawable.drawableimage),
                        contentDescription = ""
                    )
                }
                items(books!!) { book ->
                    CardBookInfo(book, userInfoViewModel, navigationController)
                }
            })
        }
    }
}


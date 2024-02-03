package com.example.mybookcontrolapp.mybookcontrolerapp.ui.screens.bookInfo

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.mybookcontrolapp.mybookcontrolerapp.data.dataInfo.Book
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.components.BottomBar
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.components.ModalDrawer
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.components.TopBar
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.viewmodels.LoginViewModel
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.viewmodels.UserInfoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BookInfoScreen(
    userInfoViewModel: UserInfoViewModel,
    loginViewModel: LoginViewModel,
    navigationController: NavHostController
) {
    val book: Book? by userInfoViewModel.book.observeAsState() // Observa cambios en el libro seleccionado.

    val coroutina =
        rememberCoroutineScope() // Crea un scope de corutina para lanzar tareas asíncronas.
    val estadoDrawer = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawer(
                estadoDrawer = estadoDrawer,
                coroutina = coroutina,
                userInfoViewModel,
                navigationController
            )
        },
        gesturesEnabled = false, // Deshabilita gestos para abrir/cerrar el drawer.
        drawerState = estadoDrawer
    ) {
        Scaffold(
            topBar = {
                TopBar(
                    "BOOK INFO",
                    estadoDrawer,
                    coroutina,
                    loginViewModel,
                    userInfoViewModel,
                    navigationController,
                    badgedOn = true
                )
            },
            content = { paddingValues ->
                BookInfoContent(
                    paddingValues,
                    book!!
                )
            },
            bottomBar = { BottomBar(navigationController, loginViewModel) }
        )
    }

}


@Composable
fun BookInfoContent(
    paddingValues: PaddingValues,
    book: Book
) {

    val brush =
        Brush.linearGradient(listOf(MaterialTheme.colorScheme.background, Color(0xFFEBB25B)))

    if (book == null) {
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
                .background(brush)
                .verticalScroll(rememberScrollState())
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            BookInfo(book)
        }
    }
}


@OptIn(ExperimentalCoilApi::class)
@Composable
fun BookInfo(book: Book) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp).padding(horizontal = 10.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(5.dp, Color.LightGray, RoundedCornerShape(8.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Titulo Libro: ${book.titulo}",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Editorial: ${book.editorial}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "Género: ${book.genero}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(text = "Autor: ${book.autor}", style = MaterialTheme.typography.bodyLarge)
                    Text(text = "Isbn: ${book.isbn}", style = MaterialTheme.typography.bodyLarge)
                }

                Image(
                    painter = rememberImagePainter(data = book.portada),
                    contentDescription = "portada",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
            }
        }
    }
}



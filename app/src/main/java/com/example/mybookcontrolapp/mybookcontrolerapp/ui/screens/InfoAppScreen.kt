package com.example.mybookcontrolapp.mybookcontrolerapp.ui.screens


import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mybookcontrolapp.R
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.components.BottomBar
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.components.ModalDrawer
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.components.TopBar
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.viewmodels.LoginViewModel
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.viewmodels.UserInfoViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoAppScreen(
    loginViewModel: LoginViewModel,
    userInfoViewModel: UserInfoViewModel,
    navigationController: NavHostController
) {
    val activity = LocalContext.current as Activity
    val coroutina = rememberCoroutineScope()
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
        gesturesEnabled = false,
        drawerState = estadoDrawer
    ) {
        Scaffold(
            topBar = {
                TopBar(
                    "INFO APP",
                    estadoDrawer,
                    coroutina,
                    loginViewModel,
                    userInfoViewModel,
                    navigationController,
                    badgedOn = false,
                )
            },
            bottomBar = { BottomBar(navigationController) },
            content = {
                ContentInfo(navigationController)
            })
    }
}


@Composable
fun ContentInfo(navigationController: NavHostController) {
    Column() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(3f)
        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize(), verticalArrangement = Arrangement.Center
            ) {
                Divider(thickness = 4.dp)
                Text(
                    text = "Autor: Jordi Allepuz Janoher",
                    modifier = Modifier.padding(10.dp),
                    fontSize = 20.sp,
                    color = Color.Black
                )
                Divider(thickness = 4.dp)
                Text(
                    text = "Nombre App: My Book Controller",
                    modifier = Modifier.padding(10.dp),
                    fontSize = 20.sp,
                    color = Color.Black
                )
                Divider(thickness = 4.dp)
                Text(
                    text = "Version: 1.0",
                    modifier = Modifier.padding(10.dp),
                    fontSize = 20.sp,
                    color = Color.Black
                )
                Divider(thickness = 4.dp)
                Text(
                    text = "Contacto: joralljan@alu.edu.gva.es ",
                    modifier = Modifier.padding(10.dp),
                    fontSize = 20.sp,
                    color = Color.Black
                )
                Divider(thickness = 4.dp)
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        )
        {
            Row(
                verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 16.dp)
            ) {
                var show by remember { mutableStateOf(false) }
                LaunchedEffect(key1 = Unit) {
                    show = true
                }
                val scale by animateFloatAsState(
                    targetValue = if (show) 1f else 0f,
                    animationSpec = tween(durationMillis = 1500, easing = LinearOutSlowInEasing)
                )
                Image(
                    painter = painterResource(id = R.drawable.jordiphoto),
                    contentDescription = "photoperfil",
                    Modifier
                        .size(80.dp)
                        .graphicsLayer {
                            scaleX = scale
                            scaleY = scale
                        }
                        .clip(CircleShape), contentScale = ContentScale.Crop
                )
                AnimatedVisibility(
                    visible = show,
                    enter = slideInHorizontally(
                        initialOffsetX = { it },
                        animationSpec = tween(durationMillis = 1500)
                    )
                ) {
                    Text(
                        text = "JORDI ALLEPUZ JANOHER",
                        Modifier.padding(start = 8.dp),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }

            }
        }
    }
}





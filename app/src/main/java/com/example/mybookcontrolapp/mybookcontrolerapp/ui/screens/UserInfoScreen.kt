package com.example.mybookcontrolapp.mybookcontrolerapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.mybookcontrolapp.Routes
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.viewmodels.LoginViewModel
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.viewmodels.UserInfoViewModel

@Composable
fun UserInfoScreen(loginViewModel: LoginViewModel,userInfoViewModel: UserInfoViewModel, navigationController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UserInfo(userInfoViewModel)
        ReadBooks(userInfoViewModel)
        Botones(userInfoViewModel, loginViewModel , navigationController)
    }
}



@Composable
fun UserInfo(userInfoViewModel: UserInfoViewModel) {
    Column() {

    }
}


@Composable
fun ReadBooks(userInfoViewModel: UserInfoViewModel){
    LazyRow(content =  )
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



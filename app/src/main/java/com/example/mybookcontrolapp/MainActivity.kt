package com.example.mybookcontrolapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.screens.bookInfo.BookInfoScreen
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.screens.colectionBooks.CollectionBookScreen
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.screens.infoApp.InfoAppScreen
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.screens.logIn.LoginScreen
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.screens.signUp.SignUpScreen
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.screens.userInfo.UserInfoScreen
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.viewmodels.LoginViewModel
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.viewmodels.SignUpViewModel
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.viewmodels.UserInfoViewModel
import com.example.mybookcontrolapp.ui.theme.MyBookControlAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val loginViewModel: LoginViewModel by viewModels()
    private val signUpViewModel: SignUpViewModel by viewModels()
    private val userInfoViewModel: UserInfoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyBookControlAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navigationController = rememberNavController()
                    NavHost(
                        navController = navigationController,
                        startDestination = loginViewModel.checkDestination()
                    ) {
                        composable(Routes.LoginScreen.route) {
                            LoginScreen(loginViewModel, navigationController)
                        }
                        composable(Routes.SignUpScreen.route) {
                            SignUpScreen(signUpViewModel, navigationController)
                        }
                        composable(Routes.UserInfoScreen.route) {
                            UserInfoScreen(userInfoViewModel, loginViewModel, navigationController)
                        }
                        composable(Routes.BookInfoScreen.route) {
                            BookInfoScreen(userInfoViewModel,loginViewModel, navigationController)
                        }
                        composable(Routes.CollectionBookScreen.route) {
                            CollectionBookScreen(userInfoViewModel, loginViewModel, navigationController)
                        }
                        composable(Routes.InfoAppScreen.route) {
                            InfoAppScreen(loginViewModel,userInfoViewModel,navigationController)
                        }
                    }
                }
            }
        }
    }
}


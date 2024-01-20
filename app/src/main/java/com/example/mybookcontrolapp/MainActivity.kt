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
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.screens.LoginScreen
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.screens.UserBooksScreen
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.viewmodels.LoginViewModel
import com.example.mybookcontrolapp.ui.theme.MyBookControlAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val loginViewModel: LoginViewModel by viewModels()

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
                        startDestination = Routes.LoginScreen.route
                    ) {
                        composable(Routes.LoginScreen.route) {
                            LoginScreen(loginViewModel, navigationController)
                        }
                        composable(Routes.UserBooksScreen.route) {
                            UserBooksScreen(navigationController)
                        }
                        composable(Routes.SignUpScreen.route) {

                        }
                    }
                }
            }
        }
    }
}


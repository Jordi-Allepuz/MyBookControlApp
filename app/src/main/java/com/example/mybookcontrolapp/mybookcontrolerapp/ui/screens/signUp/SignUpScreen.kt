package com.example.mybookcontrolapp.mybookcontrolerapp.ui.screens.signUp

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mybookcontrolapp.Routes
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.components.DropDownMenuGenere
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.components.ProgressIndicatorLogo
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.viewmodels.SignUpViewModel


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignUpScreen(
    signUpViewModel: SignUpViewModel,
    navController: NavHostController,
) {

    val email: String by signUpViewModel.email.observeAsState(initial = "")
    val userName: String by signUpViewModel.userName.observeAsState(initial = "")
    val age: String by signUpViewModel.age.observeAsState(initial = "")
    val password1: String by signUpViewModel.password1.observeAsState(initial = "")
    val password2: String by signUpViewModel.password2.observeAsState(initial = "")
    val favoriteGenere: String by signUpViewModel.favoriteGenere.observeAsState(initial = "")
    val isSignUpEnable: Boolean by signUpViewModel.isSignUpEnable.observeAsState(initial = false)


    Scaffold(
        topBar = {/*empty*/ },
        content = { paddingValues ->
            ContentNewUSer(
                signUpViewModel,
                email,
                userName,
                age,
                password1,
                password2,
                favoriteGenere,
                paddingValues,
            )
        },
        bottomBar = { /*empty*/ },
        floatingActionButton = {
            FabNewUser(
                navController,
                signUpViewModel,
                email,
                userName,
                age,
                password1,
                favoriteGenere,
                isSignUpEnable
            )
        },
        floatingActionButtonPosition = FabPosition.End,
    )
}


@Composable
fun FabNewUser(
    navController: NavHostController,
    singUpViewModel: SignUpViewModel,
    email: String,
    userName: String,
    age: String,
    password1: String,
    favoriteGenere: String,
    isSignUpEnable: Boolean,
) {
    val contentToast = LocalContext.current.applicationContext
    FloatingActionButton(
        onClick = {
            if (isSignUpEnable) {
                Toast.makeText(
                    contentToast,
                    "REGISTRADO",
                    Toast.LENGTH_LONG
                ).show()
                singUpViewModel.registerUser(
                    userName,
                    email,
                    age,
                    favoriteGenere
                )
                singUpViewModel.signUp(
                    email,
                    password1
                ) { navController.navigate(Routes.UserInfoScreen.route) }
            } else {
                Toast.makeText(
                    contentToast,
                    "REVISA LOS CAMPOS",
                    Toast.LENGTH_LONG
                ).show()
            }
        }, contentColor = Color.Black, containerColor = Color(0xFFe4bc7f)
    ) {
        Icon(imageVector = Icons.Rounded.Add, contentDescription = "check")
    }
}


@Composable
fun ContentNewUSer(
    singUpViewModel: SignUpViewModel,
    email: String,
    userName: String,
    age: String,
    password1: String,
    password2: String,
    favoriteGenere: String,
    paddingValues: PaddingValues,
) {


    var passwordVisibility by rememberSaveable { mutableStateOf(true) }
    val isLoading: Boolean by singUpViewModel.isLoading.observeAsState(false)

    LaunchedEffect(Unit) {
        singUpViewModel.getPhotos()
    }


    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            ProgressIndicatorLogo()
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
        ) {

            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(bottom = 80.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Nombre(
                    userName,
                    age,
                    email,
                    favoriteGenere,
                    password1,
                    password2,
                    singUpViewModel
                )
                Edad(
                    userName,
                    age,
                    email,
                    favoriteGenere,
                    password1,
                    password2,
                    singUpViewModel
                )
                Email(
                    userName,
                    age,
                    email,
                    favoriteGenere,
                    password1,
                    password2,
                    singUpViewModel
                )
                Password1(
                    userName,
                    age,
                    email,
                    favoriteGenere,
                    password1,
                    password2,
                    singUpViewModel,
                    passwordVisibility,
                    setPasswordVisibility = { passwordVisibility = it }
                )
                Password2(
                    userName,
                    age,
                    email,
                    favoriteGenere,
                    password1,
                    password2,
                    singUpViewModel,
                    passwordVisibility,
                    setPasswordVisibility = { passwordVisibility = it }
                )
                DropDownMenuGenere(
                    singUpViewModel,
                    email,
                    userName,
                    age,
                    password1,
                    password2,
                    favoriteGenere
                )
            }
        }
    }
}







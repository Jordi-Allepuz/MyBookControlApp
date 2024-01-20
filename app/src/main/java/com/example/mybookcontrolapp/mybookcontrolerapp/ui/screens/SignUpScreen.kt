package com.example.mybookcontrolapp.mybookcontrolerapp.ui.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Person2
import androidx.compose.material.icons.rounded.SwipeDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mybookcontrolapp.Routes
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.viewmodels.SignUpViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignUpScreen(signUpViewModel: SignUpViewModel, navController: NavHostController) {


    Scaffold(
//        topBar = { TopBarNewUser(navController) },
        content = { ContentNewUSer(signUpViewModel, navController) },
//        bottomBar = { BottomBarNewUser(navController) },
        floatingActionButton = { FabNewUser(navController) },
        floatingActionButtonPosition = FabPosition.End,
    )
}


@Composable
fun FabNewUser(navController: NavHostController) {
    val contentToast = LocalContext.current.applicationContext
    FloatingActionButton(onClick = {
        Toast.makeText(
            contentToast,
            "Rellena todos los campos para registrarte",
            Toast.LENGTH_LONG
        ).show()
    }) {
        Icon(imageVector = Icons.Rounded.Add, contentDescription = "check")
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentNewUSer(singUpViewModel: SignUpViewModel, navController: NavHostController) {

    val email: String by singUpViewModel.email.observeAsState(initial = "")
    val userName: String by singUpViewModel.userName.observeAsState(initial = "")
    val age: String by singUpViewModel.age.observeAsState(initial = "")
    val password1: String by singUpViewModel.password1.observeAsState(initial = "")
    val password2: String by singUpViewModel.password2.observeAsState(initial = "")
    val favoriteGenere: String by singUpViewModel.favoriteGenere.observeAsState(initial = "")
    val isSignUpEnable: Boolean by singUpViewModel.isSignUpEnable.observeAsState(initial = false)


    var avisoContraseña by rememberSaveable {
        mutableStateOf("Introduce contraseña")
    }
    var avisoContraseñaConf by rememberSaveable {
        mutableStateOf("Vuelve a introducir contraseña")
    }


    var passwordVisibility by rememberSaveable { mutableStateOf(true) }


    Box(modifier = Modifier.fillMaxSize()) {
        Column() {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(bottom = 80.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    OutlinedTextField(
                        value = userName,
                        onValueChange = {
                            singUpViewModel.onLoginChange(
                                email,
                                it,
                                password1,
                                password2,
                                age,
                                favoriteGenere
                            )
                        },
                        label = { Text(text = "Nombre Usuario") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Rounded.Person,
                                contentDescription = "name"
                            )
                        },
                        modifier = Modifier.size(300.dp, 60.dp),
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = age,
                        onValueChange = {
                            singUpViewModel.onLoginChange(
                                email,
                                userName,
                                password1,
                                password2,
                                it,
                                favoriteGenere
                            )
                        },
                        label = { Text(text = "Edad") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Rounded.Person2,
                                contentDescription = "edad"
                            )
                        },
                        modifier = Modifier.size(300.dp, 60.dp),
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = email,
                        onValueChange = {
                            singUpViewModel.onLoginChange(
                                it,
                                userName,
                                password1,
                                password2,
                                age,
                                favoriteGenere
                            )
                        },
                        label = { Text(text = "Correo electronico") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Rounded.Email,
                                contentDescription = "email"
                            )
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        modifier = Modifier.size(300.dp, 60.dp),
                        singleLine = true

                    )
                    OutlinedTextField(
                        value = password1,
                        onValueChange = {
                            singUpViewModel.onLoginChange(
                                email,
                                userName,
                                it,
                                password2,
                                age,
                                favoriteGenere
                            )
                        },
                        label = { Text(text = "Contraseña") },
                        trailingIcon = {
                            val passwordImagen = if (passwordVisibility) {
                                Icons.Filled.VisibilityOff
                            } else {
                                Icons.Filled.Visibility
                            }
                            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                                Icon(
                                    imageVector = passwordImagen,
                                    contentDescription = "visibility"
                                )

                            }
                        },
                        modifier = Modifier.size(300.dp, 90.dp),
                        visualTransformation = if (passwordVisibility) {
                            PasswordVisualTransformation()
                        } else {
                            VisualTransformation.None
                        },
                        supportingText = { Text(text = "Introduce contraseña") },
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = password2,
                        onValueChange = {
                            singUpViewModel.onLoginChange(
                                email,
                                userName,
                                password1,
                                it,
                                age,
                                favoriteGenere
                            )
                        },
                        label = { Text(text = "Contraseña") },
                        trailingIcon = {
                            val passwordImagen = if (passwordVisibility) {
                                Icons.Filled.VisibilityOff
                            } else {
                                Icons.Filled.Visibility
                            }
                            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                                Icon(
                                    imageVector = passwordImagen,
                                    contentDescription = "visibility"
                                )

                            }
                        },
                        modifier = Modifier.size(300.dp, 90.dp),
                        visualTransformation = if (passwordVisibility) {
                            PasswordVisualTransformation()
                        } else {
                            VisualTransformation.None
                        },
                        supportingText = { Text(text = "Confirmar contraseña") },
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = favoriteGenere,
                        onValueChange = {
                            singUpViewModel.onLoginChange(
                                email,
                                userName,
                                password1,
                                password2,
                                age,
                                it
                            )
                        },
                        label = { Text(text = "Genero favorito") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Rounded.Person2,
                                contentDescription = "favorito"
                            )
                        },
                        modifier = Modifier.size(300.dp, 60.dp),
                        singleLine = true
                    )
                    Button(onClick = {
                        singUpViewModel.signUp(
                            userName,
                            email,
                            password1,
                            age,
                            favoriteGenere
                        ) { navController.navigate(Routes.UserBooksScreen.route) }
                    }) {
                        Text(text = "SIGN UP")
                    }
                }
            }
        }
    }
}





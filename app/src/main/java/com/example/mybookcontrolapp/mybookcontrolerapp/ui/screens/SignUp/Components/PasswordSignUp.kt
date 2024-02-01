package com.example.mybookcontrolapp.mybookcontrolerapp.ui.screens.SignUp.Components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.viewmodels.SignUpViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Password1(
    userName: String,
    age: String,
    email: String,
    favoriteGenere: String,
    password1: String,
    password2: String,
    singUpViewModel: SignUpViewModel,
    passwordVisibility: Boolean,
    setPasswordVisibility: (Boolean) -> Unit
) {
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
            IconButton(onClick = { setPasswordVisibility(!passwordVisibility) }) {
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
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Password2(
    userName: String,
    age: String,
    email: String,
    favoriteGenere: String,
    password1: String,
    password2: String,
    singUpViewModel: SignUpViewModel,
    passwordVisibility: Boolean,
    setPasswordVisibility: (Boolean) -> Unit
) {
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
            IconButton(onClick = { setPasswordVisibility(!passwordVisibility) }) {
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
}




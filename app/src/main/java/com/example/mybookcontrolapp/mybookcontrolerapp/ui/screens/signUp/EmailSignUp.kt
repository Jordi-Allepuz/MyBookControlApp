package com.example.mybookcontrolapp.mybookcontrolerapp.ui.screens.signUp

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.viewmodels.SignUpViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Email(
    userName: String,
    age: String,
    email: String,
    favoriteGenere: String,
    password1: String,
    password2: String,
    singUpViewModel: SignUpViewModel
) {
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
        label = { Text(text = "Correo electronico", color = Color.White) },
        colors = TextFieldDefaults.outlinedTextFieldColors(textColor = Color.White),
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
}
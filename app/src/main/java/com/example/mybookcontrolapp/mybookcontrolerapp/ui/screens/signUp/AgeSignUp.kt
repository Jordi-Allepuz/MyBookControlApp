package com.example.mybookcontrolapp.mybookcontrolerapp.ui.screens.signUp

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person2
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.viewmodels.SignUpViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Edad(
    userName: String,
    age: String,
    email: String,
    favoriteGenere: String,
    password1: String,
    password2: String,
    singUpViewModel: SignUpViewModel
) {
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
}

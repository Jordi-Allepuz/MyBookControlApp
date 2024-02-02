package com.example.mybookcontrolapp.mybookcontrolerapp.ui.screens.signUp

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
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
fun Nombre(
    userName: String,
    age: String,
    email: String,
    favoriteGenere: String,
    password1: String,
    password2: String,
    singUpViewModel: SignUpViewModel
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
}


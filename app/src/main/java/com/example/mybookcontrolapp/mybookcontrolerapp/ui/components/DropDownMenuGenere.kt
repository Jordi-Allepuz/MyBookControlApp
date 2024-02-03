package com.example.mybookcontrolapp.mybookcontrolerapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.SwipeDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.data.EnumGenere
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.viewmodels.SignUpViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownMenuGenere(
    singUpViewModel: SignUpViewModel,
    email: String,
    userName: String,
    age: String,
    password1: String,
    password2: String,
    favoriteGenere: String,
) {

    // Estado para controlar si el menú desplegable está expandido o no.
    var expanded by remember { mutableStateOf(false) }
    // Lista de géneros literarios disponibles para seleccionar.
    val generes = EnumGenere.values()

    Column(verticalArrangement = Arrangement.SpaceBetween) {
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
            label = { Text(text = "Selecciona una genero", color = Color.White) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.SwipeDown,
                    contentDescription = "genero"
                )
            },
            readOnly = true,
            enabled = false,
            modifier = Modifier
                .size(300.dp, 60.dp)
                .clickable { expanded = true },
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = Color.Black)
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .size(300.dp)
                .fillMaxHeight()
        ) {
            generes.forEach { genere ->
                DropdownMenuItem(onClick = {
                    singUpViewModel.onLoginChange(
                        email,
                        userName,
                        password1,
                        password2,
                        age,
                        genere.toString()
                    )
                    expanded = false
                }, text = {
                        Text(text = genere.toString())
                    }
                )

            }
        }
    }
}
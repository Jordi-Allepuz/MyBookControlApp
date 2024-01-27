package com.example.mybookcontrolapp.mybookcontrolerapp.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BadgedBoxBook() {
    var contador by rememberSaveable { mutableStateOf(0) }

    IconButton(onClick = { contador += 1 }) {
        BadgedBox(badge = { Text(text = "$contador") }) {
            Icon(imageVector = Icons.Default.Favorite, contentDescription = "back", tint = Color(
                0xFFF44336
            )
            )
        }
    }
}
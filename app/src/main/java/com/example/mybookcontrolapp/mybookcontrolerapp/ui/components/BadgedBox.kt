package com.example.mybookcontrolapp.mybookcontrolerapp.ui.components

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import com.example.mybookcontrolapp.mybookcontrolerapp.data.dataInfo.Book
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.viewmodels.UserInfoViewModel
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BadgedBoxBook() {
    var contador by rememberSaveable { mutableStateOf(0) }

    var scale by remember { mutableStateOf(1f) }
    val animatedScale by animateFloatAsState(
        targetValue = scale,
        animationSpec = tween(durationMillis = 1000, easing = LinearOutSlowInEasing)
    )

    LaunchedEffect(contador) {
        if (contador > 0) {
            scale = 3f
            delay(500)
            scale = 1f
        }
    }


    IconButton(onClick = {
        contador += 1
    }) {
        BadgedBox(badge = { Text(text = "$contador") }) {
            Icon(
                imageVector = Icons.Default.Favorite, contentDescription = "like", tint = Color(
                    0xFFF44336
                ),
                modifier = Modifier.scale(animatedScale)
            )
        }
    }
}
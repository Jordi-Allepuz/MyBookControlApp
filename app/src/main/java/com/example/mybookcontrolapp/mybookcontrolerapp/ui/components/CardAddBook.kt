package com.example.mybookcontrolapp.mybookcontrolerapp.ui.components



import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mybookcontrolapp.Routes
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.viewmodels.UserInfoViewModel

// composable que muestra una tarjeta para agregar un libro.
@Composable
fun CardAddBook(userInfoViewModel: UserInfoViewModel, navigationController: NavHostController) {
    Card(modifier = Modifier
        .width(250.dp)
        .height(400.dp)
        .padding(horizontal = 20.dp), colors = CardDefaults.cardColors(containerColor = Color.Transparent)) {
        Box(modifier = Modifier.fillMaxSize().border(2.dp, Color.Black, RoundedCornerShape(4.dp)), contentAlignment = Alignment.Center ){
            IconButton(onClick = {
                userInfoViewModel.getBookCollection {
                    navigationController.navigate(
                        Routes.CollectionBookScreen.route
                    )
                }
            }, modifier= Modifier.size(150.dp)) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = Color.Black,
                    modifier= Modifier.size(150.dp)
                )
            }
        }

    }
}

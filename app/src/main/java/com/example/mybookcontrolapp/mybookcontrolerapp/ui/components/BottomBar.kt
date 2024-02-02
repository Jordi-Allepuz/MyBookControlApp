package com.example.mybookcontrolapp.mybookcontrolerapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mybookcontrolapp.Routes
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.viewmodels.LoginViewModel


// Composable que define una barra de navegación inferior personalizada.
@Composable
fun BottomBar(navigationController: NavHostController, loginViewModel: LoginViewModel) {

    BottomAppBar(modifier = Modifier.fillMaxWidth(),
        actions = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                // IconButton para navegar a la pantalla de información del usuario.
                IconButton(onClick = { navigationController.navigate(Routes.UserInfoScreen.route) }) {
                    Icon(
                        imageVector = Icons.Default.Face,
                        contentDescription = "user",
                        tint = Color(
                            0xFFEBE3E3
                        ),
                        modifier = Modifier.size(40.dp)
                    )
                }
                Spacer(modifier = Modifier
                    .fillMaxHeight()
                    .width(100.dp))

                // IconButton para cerrar sesión.
                IconButton(onClick = {
                    loginViewModel.logOut {
                        navigationController.navigate(Routes.LoginScreen.route)
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.Logout,
                        contentDescription = "out",
                        tint = Color(
                            0xFFEBE3E3
                        ),
                        modifier = Modifier.size(40.dp)
                    )
                }
            }
        })
}

package com.example.mybookcontrolapp.mybookcontrolerapp.ui.components

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.mybookcontrolapp.Routes
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.viewmodels.LoginViewModel

@Composable
fun OverFlowMenu(
    expanded: Boolean,
    onDismiss: () -> Unit,
    loginViewModel: LoginViewModel,
    navigationController: NavHostController
) {


    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismiss
    ) {
        DropdownMenuItem(onClick = {
            loginViewModel.logOut {
                navigationController.navigate(Routes.LoginScreen.route)
            }
        },
            text = { Text(text = "LOG OUT") }
        )
    }
}




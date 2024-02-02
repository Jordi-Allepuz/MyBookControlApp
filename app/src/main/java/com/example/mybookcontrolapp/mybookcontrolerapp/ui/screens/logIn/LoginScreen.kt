package com.example.mybookcontrolapp.mybookcontrolerapp.ui.screens.logIn

import android.app.Activity
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mybookcontrolapp.R
import com.example.mybookcontrolapp.Routes
import com.example.mybookcontrolapp.mybookcontrolerapp.ui.viewmodels.LoginViewModel

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel,
    navigationController: NavHostController
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(8.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val isLoading: Boolean by loginViewModel.isLoading.observeAsState(false)
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        } else {
            Header()
            Body(
                loginViewModel,
                navigationController
            )
            Footer( navigationController)
        }
    }
}


/*HEADER*/

@Composable
fun Header() {
    val activity = LocalContext.current as Activity
    Row(modifier= Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.Top) {
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "close app",
            tint = Color.Black,
            modifier = Modifier.clickable { activity.finish() })
    }
}


/*BODY*/

@Composable
fun Body(
    loginViewModel: LoginViewModel,
    navigationController: NavHostController
) {
    val email: String by loginViewModel.email.observeAsState(initial = "")
    val password: String by loginViewModel.password.observeAsState(initial = "")
    val isLoginEnable: Boolean by loginViewModel.isLoginEnable.observeAsState(initial = false)

    Column(modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(rememberScrollState())) {
        ImageLogo(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.size(50.dp))
        Email(email) {
            loginViewModel.onLoginChange(email = it, password = password)
        }
        Spacer(modifier = Modifier.size(4.dp))
        Password(password) {
            loginViewModel.onLoginChange(email = email, password = it)
        }
        Spacer(modifier = Modifier.size(8.dp))
        ForgotPassword(Modifier.align(Alignment.End))
        Spacer(modifier = Modifier.size(16.dp))
        LoginButton(
            isLoginEnable,
            loginViewModel,
            navigationController,
            email,
            password
        )
        Spacer(modifier = Modifier.size(20.dp))
        LoginDivider()
    }
}

@Composable
fun ImageLogo(modifier: Modifier) {
    var show by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = Unit) {
        show = true
    }
    val scale by animateFloatAsState(
        targetValue = if (show) 1f else 0f,
        animationSpec = tween(durationMillis = 1000, easing = LinearOutSlowInEasing)
    )
    Image(
        painter = painterResource(id = R.drawable.logoapp),
        contentDescription = "logo", modifier = modifier
            .size(150.dp)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .clip(CircleShape)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Email(email: String, onTextChanged: (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Email") },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFFE91E63),
            containerColor = Color(0xFFF8C07F),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Red
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Password(password: String, onTextChanged: (String) -> Unit) {
    var passwordVisibility by rememberSaveable { mutableStateOf(true) }

    TextField(
        value = password,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Password") },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFFE91E63),
            containerColor = Color(0xFFF8C07F),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Red
        ),
        trailingIcon = {
            val passwordImagen = if (passwordVisibility) {
                Icons.Filled.VisibilityOff
            } else {
                Icons.Filled.Visibility
            }
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(imageVector = passwordImagen, contentDescription = "visibility")
            }
        },
        visualTransformation = if (!passwordVisibility) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        }
    )
}


@Composable
fun ForgotPassword(modifier: Modifier) {
    Text(
        text = "Olvidaste contraseña?",
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Blue,
        modifier = modifier
    )
}


@Composable
fun LoginButton(
    loginEnable: Boolean,
    loginViewModel: LoginViewModel,
    navigationController: NavHostController,
    email: String,
    password: String
) {

    Button(
        onClick = {
            loginViewModel.login(
                email, password
            ) {
                navigationController.navigate(Routes.UserInfoScreen.route)
            }
        },
        enabled = loginEnable,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF036392),
            disabledContainerColor = Color(0xCC8AC7FF),
            contentColor = Color.White,
            disabledContentColor = Color.White
        )
    )
    {
        Text(text = "Log in")
    }
}


@Composable
fun LoginDivider() {
    Row(modifier= Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Divider(
            Modifier
                .background(Color.Blue)
                .height(1.dp)
                .weight(1f)
        )
        Text(
            text = "OR",
            modifier = Modifier.padding(horizontal = 18.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold, color = Color.Blue
        )
        Divider(
            Modifier
                .background(Color.Blue)
                .height(1.dp)
                .weight(1f)
        )
    }
}


/*FOOTER*/

@Composable
fun Footer(
    navigationController: NavHostController
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        SingUp(navigationController)
        Spacer(modifier = Modifier.size(60.dp))
    }

}

@Composable
fun SingUp(navigationController: NavHostController) {

    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "No tienes una cuenta?", fontSize = 12.sp, color = Color.Blue, fontWeight = FontWeight.Bold)
        Button(
            onClick = {
                navigationController.navigate(Routes.SignUpScreen.route)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF036392),
                contentColor = Color.White,
            )
        ) {
            Text(text = "Regístrate")
        }

    }
}


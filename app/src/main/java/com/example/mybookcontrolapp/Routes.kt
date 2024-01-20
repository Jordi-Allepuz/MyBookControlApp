package com.example.mybookcontrolapp


sealed class Routes(val route: String) {
    object LoginScreen : Routes("login")
    object SignUpScreen : Routes("signups")
    object UserBooksScreen : Routes ("userbooks")


}

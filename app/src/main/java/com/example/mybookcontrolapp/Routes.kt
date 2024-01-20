package com.example.mybookcontrolapp


sealed class Routes(val route: String) {
    object LoginScreen : Routes("login")
    object UserBooksScreen : Routes ("userbooks")


}

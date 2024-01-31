package com.example.mybookcontrolapp


sealed class Routes(val route: String) {
    object LoginScreen : Routes("login")
    object UserInfoScreen : Routes ("userbooks")
    object SignUpScreen : Routes ("signupscreen")
    object BookInfoScreen : Routes ("bookinfoscreen")
    object CollectionBookScreen : Routes ("collectionbookscreen")
    object InfoAppScreen : Routes ("infoappscreen")
}

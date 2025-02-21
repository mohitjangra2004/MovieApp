package com.example.movieapp.representation.navigation

sealed class Screen(val route : String) {

    data object Home : Screen("home")
    data object Details : Screen("details/{content}"){

        fun createRoute(contentId:String) = "details/$contentId"
    }

}
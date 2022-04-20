package com.github.amrmsaraya.nagwa.presentation.navigation

sealed class Screen(val route: String) {
    object Home : Screen(
        route = "home"
    )
}

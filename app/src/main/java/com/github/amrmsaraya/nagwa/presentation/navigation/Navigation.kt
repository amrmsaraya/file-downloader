package com.github.amrmsaraya.nagwa.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.github.amrmsaraya.nagwa.presentation.home.HomeScreen
import com.github.amrmsaraya.nagwa.presentation.home.HomeViewModel

@Composable
fun Navigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            val viewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(
                modifier = modifier,
                viewModel = viewModel
            )
        }
    }
}

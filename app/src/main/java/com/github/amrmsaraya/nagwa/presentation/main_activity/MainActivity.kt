package com.github.amrmsaraya.nagwa.presentation.main_activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.github.amrmsaraya.nagwa.presentation.navigation.Navigation
import com.github.amrmsaraya.nagwa.presentation.theme.AssignmentTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setContent {
            App()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    AssignmentTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            val navController = rememberNavController()
            Scaffold(
                topBar = { SmallTopAppBar(title = { Text(text = "File Downloader") }) }
            ) {
                Navigation(
                    navController = navController,
                    modifier = Modifier.padding(it)
                )
            }
        }
    }
}

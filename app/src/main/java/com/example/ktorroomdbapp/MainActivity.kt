package com.example.ktorroomdbapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ktorroomdbapp.ui.theme.KtorRoomDBAppTheme


class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            KtorRoomDBAppTheme {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "screenMain") {

                composable(route = "screenMain") {
                    StartScreen(viewModel,navController)
                }
                composable(route = "FavoriteScreen") {
                    FavoriteScreenApp(navController)
                }
            }
            }
        }
    }
}








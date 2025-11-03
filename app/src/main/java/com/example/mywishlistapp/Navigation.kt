package com.example.mywishlistapp

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable


@Serializable
object HomeScreen

@Serializable
object AddScreen

@Composable
fun Navigation (
    viewModel: WishViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController,
        startDestination = HomeScreen
    ) {
        composable<HomeScreen> {
            HomeView { navController.navigate(AddScreen) }
        }

        composable<AddScreen> {
            AddUpdateWishScreen(
                0L,
                viewModel,
                {}
            ) {
                navController.navigateUp()
            }
        }
    }
}
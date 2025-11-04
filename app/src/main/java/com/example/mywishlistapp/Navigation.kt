package com.example.mywishlistapp

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable


@Serializable
object HomeScreen

@Serializable
data class AddScreen(
    val id: Long
)

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
            HomeView(
                viewModel,
                { navController.navigate(AddScreen(0L)) }
            ) { id ->
                navController.navigate(AddScreen(id))
            }
        }

        composable<AddScreen> { backStackEntry ->
            val addScreen: AddScreen = backStackEntry.toRoute()
            viewModel.loadWish(addScreen.id)

            AddUpdateWishScreen(
                addScreen.id,
                viewModel,
                {
                    navController.navigateUp()
                }
            ) {
                navController.navigateUp()
            }
        }
    }
}
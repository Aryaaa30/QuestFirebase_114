package com.example.session14_firebase.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.session14_firebase.ui.view.DetailView
import com.example.session14_firebase.ui.view.HomeView
import com.example.session14_firebase.ui.view.InsertMhsView

@Composable
fun PengelolaHalaman(
    modifier: Modifier,
    navController: NavHostController = rememberNavController()){
    NavHost(
        navController=navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier,
    ){
        composable(DestinasiHome.route){
            HomeView(
                navigateToItemEntry = {navController.navigate(DestinasiEntry.route)},
                onDetailClick = { nim ->
                    navController.navigate("${DestinasiDetail.route}/$nim")
                }
            )
        }
        composable(DestinasiEntry.route) {
            InsertMhsView(
                onBack = {
                    navController.navigate(DestinasiHome.route) {
                        popUpTo(DestinasiHome.route) {
                            inclusive = true
                        }
                    }
                },
                onNavigate = {
                    // Add your navigation code here after the form is successfully inserted
                    navController.navigate(DestinasiHome.route)
                }
            )
        }
        composable(DestinasiDetail.routesWithArg, arguments = listOf(navArgument(DestinasiDetail.NIM) {
            type = NavType.StringType })
        ){
            val nim = it.arguments?.getString(DestinasiDetail.NIM)
            nim?.let { nim ->
                DetailView(
                    navigateToItemUpdate = { navController.navigate("${DestinasiUpdate.route}/$nim") },
                    navigateBack = { navController.navigate(DestinasiHome.route) {
                        popUpTo(DestinasiHome.route) { inclusive = true }
                    }
                    }
                )
            }
        }
    }
}
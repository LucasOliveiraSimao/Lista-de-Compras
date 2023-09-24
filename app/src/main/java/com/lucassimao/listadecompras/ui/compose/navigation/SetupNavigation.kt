package com.lucassimao.listadecompras.ui.compose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lucassimao.listadecompras.ui.compose.home.HomeScreen
import com.lucassimao.listadecompras.ui.compose.insert.InsertScreen
import com.lucassimao.listadecompras.utils.Routes.HOME
import com.lucassimao.listadecompras.utils.Routes.INSERT

@Composable
fun SetupNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = HOME) {
        composable(HOME) { HomeScreen(navController) }
        composable(INSERT) { InsertScreen(navController) }
    }
}
package com.lucassimao.listadecompras.ui.compose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.lucassimao.listadecompras.data.model.PurchaseModel
import com.lucassimao.listadecompras.ui.compose.home.HomeScreen
import com.lucassimao.listadecompras.ui.compose.insert.InsertScreen
import com.lucassimao.listadecompras.ui.compose.update.UpdateScreen
import com.lucassimao.listadecompras.utils.CustomType
import com.lucassimao.listadecompras.utils.Routes.HOME
import com.lucassimao.listadecompras.utils.Routes.INSERT
import com.lucassimao.listadecompras.utils.Routes.UPDATE

@Composable
fun SetupNavigation(navController: NavHostController) {
    val key = "purchase"
    NavHost(navController = navController, startDestination = HOME) {
        composable(HOME) { HomeScreen(navController) }
        composable(INSERT) { InsertScreen(navController) }
        composable(
            "$UPDATE/{$key}",
            arguments = listOf(navArgument(key) { type = CustomType() })
        ) { backStackEntry ->
            val purchase = backStackEntry.arguments!!.getParcelable<PurchaseModel>(key)
            UpdateScreen(navController, purchase)
        }
    }
}
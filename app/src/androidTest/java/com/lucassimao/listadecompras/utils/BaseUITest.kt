package com.lucassimao.listadecompras.utils

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.testing.TestNavHostController
import com.lucassimao.listadecompras.ui.compose.home.HomeScreen
import com.lucassimao.listadecompras.ui.compose.insert.InsertScreen
import org.junit.Before
import org.junit.Rule

open class BaseUITest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupAppNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())

            NavHost(
                navController = navController,
                startDestination = Routes.HOME
            ) {
                composable(Routes.HOME) { HomeScreen(navController) }
                composable(Routes.INSERT) { InsertScreen(navController) }
            }
        }
    }
}
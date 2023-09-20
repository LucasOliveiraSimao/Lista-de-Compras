package com.lucassimao.listadecompras.ui

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.testing.TestNavHostController
import com.lucassimao.listadecompras.ui.compose.home.HomeScreen
import com.lucassimao.listadecompras.ui.compose.insert.InsertScreen
import com.lucassimao.listadecompras.utils.Routes
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class InsertScreenTest {

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
                composable(Routes.INSERT) { InsertScreen() }
            }
        }
    }

    @Test
    fun when_product_name_quantity_and_price_fields_are_empty_save_button_is_disable() {
        goToInsertScreen()
        composeTestRule.onNode(hasText("Salvar")).assertIsNotEnabled()
    }

    @Test
    fun when_product_name_field_is_filled_in_and_quantity_and_price_fields_are_empty_save_button_is_disable() {
        goToInsertScreen()
        composeTestRule.onNode(hasTestTag("ProductNameField")).performTextInput("Arroz")
        composeTestRule.onNode(hasText("Salvar")).assertIsNotEnabled()
    }

    @Test
    fun when_product_name_and_quantity_fields_are_filled_in_and_price_field_is_empty_save_button_is_disable() {
        goToInsertScreen()
        composeTestRule.onNode(hasTestTag("ProductNameField")).performTextInput("Arroz")
        composeTestRule.onNode(hasTestTag("ProductAmountField")).performTextClearance()
        composeTestRule.onNode(hasTestTag("ProductAmountField")).performTextInput("2")
        composeTestRule.onNode(hasText("Salvar")).assertIsNotEnabled()
    }

    @Test
    fun when_product_name_quantity_and_price_fields_are_filled_in_save_button_is_activated() {
        goToInsertScreen()
        composeTestRule.onNode(hasTestTag("ProductNameField")).performTextInput("Arroz")
        composeTestRule.onNode(hasTestTag("ProductAmountField")).performTextClearance()
        composeTestRule.onNode(hasTestTag("ProductAmountField")).performTextInput("2")
        composeTestRule.onNode(hasTestTag("ProductPriceField")).performTextInput("700")
        composeTestRule.onNode(hasText("Salvar")).assertIsEnabled()
    }

    private fun goToInsertScreen() {
        composeTestRule.onNode(hasTestTag("Fab")).performClick()
    }

}
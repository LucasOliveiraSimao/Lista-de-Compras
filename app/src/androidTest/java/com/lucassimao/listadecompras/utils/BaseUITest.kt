package com.lucassimao.listadecompras.utils

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.testing.TestNavHostController
import com.lucassimao.listadecompras.data.model.PurchaseModel
import com.lucassimao.listadecompras.ui.compose.home.HomeScreen
import com.lucassimao.listadecompras.ui.compose.insert.InsertScreen
import com.lucassimao.listadecompras.ui.compose.navigation.Routes
import com.lucassimao.listadecompras.ui.compose.update.UpdateScreen
import org.junit.Before
import org.junit.Rule

open class BaseUITest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    val save = "Salvar"
    val cancel = "Cancelar"

    val rice = "Arroz"
    val quantity = "2 x"
    val newQuantity = "1 x"
    val amount = "2"
    val newAmount = "1"
    val price14 = "Total: R$ 14,00"
    val price12 = "R$ 6,00"
    val textBread = "Pão"
    val sevenHundred = "700"
    val sixHundred = "600"

    val buttonAdd = "button_add"
    val buttonSave = "button_save"
    val purchaseList = "purchase_list"
    val productName = "product_name"
    val productAmount = "product_amount"
    val productPrice = "product_price"

    private val key = "purchase"

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
                composable(
                    "${Routes.UPDATE}/{$key}",
                    arguments = listOf(navArgument(key) { type = CustomType() })
                ) { backStackEntry ->
                    val purchase = backStackEntry.arguments!!.getParcelable<PurchaseModel>(key)
                    UpdateScreen(navController, purchase)
                }
            }
        }
    }

    fun addProduct() {
        composeTestRule.onNode(hasTestTag(productName)).performTextInput(rice)
        composeTestRule.onNode(hasTestTag(productAmount)).performTextInput(amount)
        composeTestRule.onNode(hasTestTag(productPrice)).performTextInput(sevenHundred)
    }

    fun saveProduct() {
        composeTestRule.onNode(hasText(save)).performClick()
    }

    fun goToInsertScreen() {
        composeTestRule.onNode(hasTestTag(buttonAdd)).performClick()
    }
}
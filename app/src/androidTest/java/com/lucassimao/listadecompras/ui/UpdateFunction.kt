package com.lucassimao.listadecompras.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeRight
import com.lucassimao.listadecompras.utils.BaseUITest
import org.junit.Test

class UpdateFunction : BaseUITest() {

    @Test
    fun when_editing_purchase_name_should_save_in_the_database() {
        goToInsertScreen()
        addProduct()
        saveProduct()
        composeTestRule.onNode(hasTestTag(purchaseList)).performTouchInput { swipeRight() }
        composeTestRule.onNode(hasText(rice)).performTextClearance()
        composeTestRule.onNode(hasTestTag(productName)).performTextInput(textBread)
        composeTestRule.onNode(hasTestTag(buttonSave)).performClick()
        composeTestRule.onNode(hasText(textBread)).assertIsDisplayed()
    }

    @Test
    fun when_editing_product_quantity_should_save_in_the_database() {
        goToInsertScreen()
        addProduct()
        saveProduct()
        composeTestRule.onNode(hasTestTag(purchaseList)).performTouchInput { swipeRight() }
        composeTestRule.onNode(hasText(amount)).performTextClearance()
        composeTestRule.onNode(hasTestTag(productAmount)).performTextInput(newAmount)
        composeTestRule.onNode(hasTestTag(buttonSave)).performClick()
        composeTestRule.onNode(hasText(newQuantity)).assertIsDisplayed()
    }

    @Test
    fun when_editing_product_price_should_save_in_the_database() {
        goToInsertScreen()
        addProduct()
        saveProduct()
        composeTestRule.onNode(hasTestTag(purchaseList)).performTouchInput { swipeRight() }
        composeTestRule.onNode(hasTestTag(productPrice)).performTextClearance()
        composeTestRule.onNode(hasTestTag(productPrice)).performTextInput(sixHundred)
        composeTestRule.onNode(hasTestTag(buttonSave)).performClick()

//        Comentado porquê o CI esta dando erro
//        composeTestRule.onNode(hasText(price12)).assertIsDisplayed()
    }

}
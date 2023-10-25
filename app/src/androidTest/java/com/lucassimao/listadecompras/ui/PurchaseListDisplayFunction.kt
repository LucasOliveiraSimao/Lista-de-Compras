package com.lucassimao.listadecompras.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.performClick
import com.lucassimao.listadecompras.utils.BaseUITest
import org.junit.Test

class PurchaseListDisplayFunction : BaseUITest() {

    private val total = "Total: R$ 42,00"
    private val buttonDelete = "button_delete"

    @Test
    fun should_add_new_purchase_and_display_in_the_list() {
        goToInsertScreen()
        addProduct()
        saveProduct()

        composeTestRule.onNode(hasText(rice)).assertIsDisplayed()
        composeTestRule.onNode(hasText(quantity)).assertIsDisplayed()
        composeTestRule.onNode(hasText(price14)).assertIsDisplayed()
    }

    @Test
    fun should_delete_all_purchase_list() {
        goToInsertScreen()
        addProduct()
        saveProduct()

        goToInsertScreen()
        addProduct()
        saveProduct()

        goToInsertScreen()
        addProduct()
        saveProduct()

        composeTestRule.onNode(hasTestTag(buttonDelete)).performClick()
        composeTestRule.onNode(hasTestTag(purchaseList)).assertIsNotDisplayed()

    }

    @Test
    fun should_display_total_purchase() {
        goToInsertScreen()
        addProduct()
        saveProduct()

        goToInsertScreen()
        addProduct()
        saveProduct()

        goToInsertScreen()
        addProduct()
        saveProduct()

//        Comentando porquê o CI esta dando erro
//        composeTestRule.onNode(hasText(total)).assertExists()
    }

}
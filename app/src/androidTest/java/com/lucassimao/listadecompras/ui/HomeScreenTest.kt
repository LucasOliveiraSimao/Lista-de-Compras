package com.lucassimao.listadecompras.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.lucassimao.listadecompras.utils.BaseUITest
import org.junit.Test

class HomeScreenTest : BaseUITest() {

    private val textSave = "Salvar"
    private val textCancel = "Cancelar"
    private val textProductName = "Arroz"
    private val textProductAmount = "2"
    private val resultProductAmount = "2 x"
    private val textProductPrice = "700"
    private val resultProductPrice = "R$ 7,00"
    private val textTotal = "Total: R$ 42,00"

    private val textSuccessMessage = "Item salvo com sucesso"

    private val tagProductNameField = "ProductNameField"
    private val tagProductAmountField = "ProductAmountField"
    private val tagProductPriceField = "ProductPriceField"

    private val tagFab = "Tag Test Button Add"
    private val tagDelete = "Test Tag Button Delete"
    private val tagList = "Tag Test List"
    private val tagTotal = "Test Tag Text Total"

    @Test
    fun when_adding_new_product_should_display_in_the_list() {
        addNewProduct()

        addProduct()
        saveProduct()

        composeTestRule.onNode(hasText(textCancel)).performClick()

        composeTestRule.onNode(hasText(textProductName)).assertIsDisplayed()
        composeTestRule.onNode(hasText(resultProductAmount)).assertIsDisplayed()
//        composeTestRule.onNode(hasText(resultProductPrice)).assertIsDisplayed()
    }

    @Test
    fun when_add_product_data_and_click_the_delete_all_button_should_delete_all_items_from_the_list() {
        addNewProduct()

        addProduct()
        saveProduct()

        addProduct()
        saveProduct()

        addProduct()
        saveProduct()

        composeTestRule.onNode(hasText(textCancel)).performClick()

        composeTestRule.onNode(hasTestTag(tagDelete)).performClick()
        composeTestRule.onNode(hasTestTag(tagList)).assertIsNotDisplayed()

    }

    @Test
    fun should_display_total_purchase() {
        addNewProduct()

        addProduct()
        saveProduct()

        addProduct()
        saveProduct()

        addProduct()
        saveProduct()

        composeTestRule.onNode(hasText(textCancel)).performClick()
        composeTestRule.onNode(hasTestTag(tagTotal)).assertExists(textTotal)
    }

    private fun addNewProduct() {
        composeTestRule.onNode(hasTestTag(tagFab)).performClick()
    }

    private fun saveProduct() {
        composeTestRule.onNode(hasText(textSave)).performClick()
        composeTestRule.onNode(hasText(textSuccessMessage)).assertIsDisplayed()
    }

    private fun addProduct() {
        composeTestRule.onNode(hasTestTag(tagProductNameField)).performTextInput(textProductName)
        composeTestRule.onNode(hasTestTag(tagProductAmountField))
            .performTextInput(textProductAmount)
        composeTestRule.onNode(hasTestTag(tagProductPriceField)).performTextInput(textProductPrice)
    }

}
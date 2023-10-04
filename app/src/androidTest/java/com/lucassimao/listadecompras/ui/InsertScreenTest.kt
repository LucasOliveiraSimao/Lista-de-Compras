package com.lucassimao.listadecompras.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.lucassimao.listadecompras.utils.BaseUITest
import org.junit.Test


class InsertScreenTest:BaseUITest() {

    private val textSave = "Salvar"
    private val textCancel = "Cancelar"
    private val textProductName = "Arroz"
    private val textProductAmount = "2"
    private val textProductPrice = "700"

    private val textSuccessMessage = "Item salvo com sucesso"

    private val tagProductNameField = "ProductNameField"
    private val tagProductAmountField = "ProductAmountField"
    private val tagProductPriceField = "ProductPriceField"

    private val tagFab = "Fab"


    @Test
    fun when_product_name_quantity_and_price_fields_are_empty_save_button_is_disable() {
        goToInsertScreen()
        composeTestRule.onNode(hasText(textSave)).assertIsNotEnabled()
    }

    @Test
    fun when_product_name_field_is_filled_in_and_quantity_and_price_fields_are_empty_save_button_is_disable() {
        goToInsertScreen()
        composeTestRule.onNode(hasTestTag(tagProductNameField)).performTextInput(textProductName)
        composeTestRule.onNode(hasText(textSave)).assertIsNotEnabled()
    }

    @Test
    fun when_product_name_and_quantity_fields_are_filled_in_and_price_field_is_empty_save_button_is_disable() {
        goToInsertScreen()
        composeTestRule.onNode(hasTestTag(tagProductNameField)).performTextInput(textProductName)
        composeTestRule.onNode(hasTestTag(tagProductAmountField)).performTextInput(textProductAmount)
        composeTestRule.onNode(hasText(textSave)).assertIsNotEnabled()
    }

    @Test
    fun when_product_name_quantity_and_price_fields_are_filled_in_save_button_is_activated() {
        goToInsertScreen()
        composeTestRule.onNode(hasTestTag(tagProductNameField)).performTextInput(textProductName)
        composeTestRule.onNode(hasTestTag(tagProductAmountField)).performTextInput(textProductAmount)
        composeTestRule.onNode(hasTestTag(tagProductPriceField)).performTextInput(textProductPrice)
        composeTestRule.onNode(hasText(textSave)).assertIsEnabled()
    }

    @Test
    fun when_enter_product_data_and_click_on_the_save_button_a_success_message_is_displayed(){
        goToInsertScreen()
        composeTestRule.onNode(hasTestTag(tagProductNameField)).performTextInput(textProductName)
        composeTestRule.onNode(hasTestTag(tagProductAmountField)).performTextInput(textProductAmount)
        composeTestRule.onNode(hasTestTag(tagProductPriceField)).performTextInput(textProductPrice)
        composeTestRule.onNode(hasText(textSave)).performClick()
        composeTestRule.onNode(hasText(textSuccessMessage)).assertIsDisplayed()
    }

    @Test
    fun when_click_on_the_cancel_button_return_to_the_home_screen(){
        goToInsertScreen()
        composeTestRule.onNode(hasText(textCancel)).performClick()
        composeTestRule.onNode(hasTestTag(tagFab)).assertExists()
    }

    private fun goToInsertScreen() {
        composeTestRule.onNode(hasTestTag(tagFab)).performClick()
    }

}
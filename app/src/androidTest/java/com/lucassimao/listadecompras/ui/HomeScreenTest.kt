package com.lucassimao.listadecompras.ui

import androidx.compose.ui.test.assertIsDisplayed
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

    private val textSuccessMessage = "Item salvo com sucesso"

    private val tagProductNameField = "ProductNameField"
    private val tagProductAmountField = "ProductAmountField"
    private val tagProductPriceField = "ProductPriceField"

    private val tagFab = "Fab"

    @Test
    fun when_adding_new_product_should_display_in_the_list() {
        composeTestRule.onNode(hasTestTag(tagFab)).performClick()
        composeTestRule.onNode(hasTestTag(tagProductNameField)).performTextInput(textProductName)
        composeTestRule.onNode(hasTestTag(tagProductAmountField))
            .performTextInput(textProductAmount)
        composeTestRule.onNode(hasTestTag(tagProductPriceField)).performTextInput(textProductPrice)
        composeTestRule.onNode(hasText(textSave)).performClick()
        composeTestRule.onNode(hasText(textSuccessMessage)).assertIsDisplayed()
        composeTestRule.onNode(hasText(textCancel)).performClick()

        composeTestRule.onNode(hasText(textProductName)).assertIsDisplayed()
        composeTestRule.onNode(hasText(resultProductAmount)).assertIsDisplayed()
        composeTestRule.onNode(hasText(resultProductPrice)).assertIsDisplayed()
    }

}
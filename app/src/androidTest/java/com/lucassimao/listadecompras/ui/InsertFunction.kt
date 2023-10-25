package com.lucassimao.listadecompras.ui

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.lucassimao.listadecompras.utils.BaseUITest
import org.junit.Test


class InsertFunction:BaseUITest() {

    @Test
    fun test_if_save_button_is_disabled_when_name_quantity_and_price_of_the_product_are_empty() {
        goToInsertScreen()
        composeTestRule.onNode(hasTestTag(buttonSave)).assertIsNotEnabled()
    }

    @Test
    fun test_if_save_button_is_disabled_when_name_is_filled_in_but_quantity_and_price_of_the_product_are_empty() {
        goToInsertScreen()
        composeTestRule.onNode(hasTestTag(productName)).performTextInput(rice)
        composeTestRule.onNode(hasText(save)).assertIsNotEnabled()
    }

    @Test
    fun test_if_save_button_is_disabled__when_name_and_quantity_are_filled_in_but_product_price_is_empty() {
        goToInsertScreen()
        composeTestRule.onNode(hasTestTag(productName)).performTextInput(rice)
        composeTestRule.onNode(hasTestTag(productAmount)).performTextInput(amount)
        composeTestRule.onNode(hasText(save)).assertIsNotEnabled()
    }

    @Test
    fun test_if_save_button_is_enabled_when_name_quantity_and_price_are_filled_in() {
        goToInsertScreen()
        composeTestRule.onNode(hasTestTag(productName)).performTextInput(rice)
        composeTestRule.onNode(hasTestTag(productAmount)).performTextInput(amount)
        composeTestRule.onNode(hasTestTag(productPrice)).performTextInput(sevenHundred)
        composeTestRule.onNode(hasText(save)).assertIsEnabled()
    }

    @Test
    fun should_return_home_screen_when_clicking_cancel_button(){
        goToInsertScreen()
        composeTestRule.onNode(hasText(cancel)).performClick()
        composeTestRule.onNode(hasTestTag(buttonAdd)).assertExists()
    }

}
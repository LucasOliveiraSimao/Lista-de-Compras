package com.lucassimao.listadecompras.ui

import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeLeft
import com.lucassimao.listadecompras.utils.BaseUITest
import org.junit.Test

class DeleteFunction : BaseUITest() {
    @Test
    fun should_delete_purchase_from_list() {
        goToInsertScreen()
        addProduct()
        saveProduct()

        composeTestRule.onNode(hasTestTag(purchaseList)).performTouchInput { swipeLeft() }
        itemDoesNotExist()
    }

    private fun itemDoesNotExist() {
        composeTestRule.onNode(hasText(rice)).assertDoesNotExist()
        composeTestRule.onNode(hasText(quantity)).assertDoesNotExist()
        composeTestRule.onNode(hasText(price14)).assertDoesNotExist()
    }
}
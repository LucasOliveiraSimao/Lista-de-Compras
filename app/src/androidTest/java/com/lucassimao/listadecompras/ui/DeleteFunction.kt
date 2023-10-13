package com.lucassimao.listadecompras.ui

import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeLeft
import com.lucassimao.listadecompras.utils.BaseUITest
import org.junit.Test

class DeleteFunction : BaseUITest() {
    @Test
    fun must_delete_purchase_from_list() {
        goToInsertScreen()
        addProduct()
        cancel()
        composeTestRule.onNode(hasTestTag(tagList)).performTouchInput { swipeLeft() }
        itemDoesNotExist()
    }

    private fun itemDoesNotExist() {
        composeTestRule.onNode(hasText(textProductName)).assertDoesNotExist()
        composeTestRule.onNode(hasText(textProductAmount2)).assertDoesNotExist()
        composeTestRule.onNode(hasText(textProductPrice2)).assertDoesNotExist()
    }
}
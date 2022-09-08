package com.lucassimao.listadecompras.ui

import com.adevinta.android.barista.assertion.BaristaListAssertions.assertDisplayedAtPosition
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.interaction.BaristaClickInteractions.clickOn
import com.lucassimao.listadecompras.R
import com.lucassimao.listadecompras.utils.BaseUITest
import org.junit.Test

class HomeFragmentTest : BaseUITest() {

    @Test
    fun shouldDisplayTitle() {
        assertDisplayed(R.string.app_name)
    }

    @Test
    fun shouldDisplayListOfPurchase() {
        assertDisplayedAtPosition(
            R.id.rv_list_purchases,
            0,
            R.id.item_name,
            R.string.test_product_name
        )
        assertDisplayedAtPosition(
            R.id.rv_list_purchases,
            0,
            R.id.item_quantity,
            R.string.test_product_quantity
        )
        assertDisplayedAtPosition(
            R.id.rv_list_purchases,
            0,
            R.id.item_price,
            R.string.test_product_price
        )
    }

    @Test
    fun shouldDisplayPriceTotal() {
        assertDisplayed(R.id.tv_total)
    }

    @Test
    fun shouldNavigateToInsertScreen() {
        clickOn(R.id.fab_insert_purchase)
    }

}
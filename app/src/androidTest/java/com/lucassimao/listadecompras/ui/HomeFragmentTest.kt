package com.lucassimao.listadecompras.ui

import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.interaction.BaristaClickInteractions.clickOn
import com.adevinta.android.barista.interaction.BaristaListInteractions.clickListItemChild
import com.lucassimao.listadecompras.R
import com.lucassimao.listadecompras.utils.BaseUITest
import org.junit.Test

class HomeFragmentTest : BaseUITest() {

    @Test
    fun shouldDisplayTitle() {
        assertDisplayed(R.string.app_name)
    }

    @Test
    fun shouldDeleteItemOfList() {
        clickListItemChild(R.id.rv_list_purchases, 0, R.id.item_delete_purchase);
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
package com.lucassimao.listadecompras.ui

import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.interaction.BaristaClickInteractions.clickOn
import com.adevinta.android.barista.interaction.BaristaEditTextInteractions.writeTo
import com.lucassimao.listadecompras.R
import com.lucassimao.listadecompras.utils.BaseUITest
import org.junit.Test


class InsertFragmentTest : BaseUITest() {

    @Test
    fun shouldDisplayWarningMessage_whenProductNameFieldIsEmpty() {
        clickOn(R.id.fab_insert_purchase)
        clickOn(R.id.btn_insert)
        assertDisplayed(R.string.warning_message)
    }

    @Test
    fun shouldDisplayWarningMessage_whenProductNameFieldIsBlank() {
        clickOn(R.id.fab_insert_purchase)
        writeTo(R.id.et_purchase_name, "  ")
        clickOn(R.id.btn_insert)
        assertDisplayed(R.string.warning_message)
    }

    @Test
    fun shouldDisplayWarningMessage_whenProductQuantityFieldIsEmpty() {
        clickOn(R.id.fab_insert_purchase)
        writeTo(R.id.et_purchase_name, "Arroz")
        clickOn(R.id.btn_insert)
        assertDisplayed(R.string.warning_message)
    }

    @Test
    fun shouldDisplayWarningMessage_whenProductQuantityFieldIsBlank() {
        clickOn(R.id.fab_insert_purchase)
        writeTo(R.id.et_purchase_name, "Arroz")
        writeTo(R.id.et_purchase_quantity, "  ")
        clickOn(R.id.btn_insert)
        assertDisplayed(R.string.warning_message)
    }

    @Test
    fun shouldDisplayWarningMessage_whenProductPriceFieldIsEmpty() {
        clickOn(R.id.fab_insert_purchase)
        writeTo(R.id.et_purchase_name, "Arroz")
        writeTo(R.id.et_purchase_quantity, "1")
        clickOn(R.id.btn_insert)
        assertDisplayed(R.string.warning_message)
    }

    @Test
    fun shouldDisplayWarningMessage_whenProductPriceFieldIsBlank() {
        clickOn(R.id.fab_insert_purchase)
        writeTo(R.id.et_purchase_name, "Arroz")
        writeTo(R.id.et_purchase_quantity, "1")
        writeTo(R.id.et_purchase_price, "  ")
        clickOn(R.id.btn_insert)
        assertDisplayed(R.string.warning_message)
    }

    @Test
    fun shouldDisplaySaveMessage_whenFillFieldsAndClickSave() {
        clickOn(R.id.fab_insert_purchase)
        writeTo(R.id.et_purchase_name, "Arroz")
        writeTo(R.id.et_purchase_quantity, "1")
        writeTo(R.id.et_purchase_price, "7.52")
        clickOn(R.id.btn_insert)
        assertDisplayed(R.string.save_message)
    }

    @Test
    fun shouldReturnHomeScreen_whenClickCancel() {
        clickOn(R.id.fab_insert_purchase)
        clickOn(R.id.btn_cancel)
        assertDisplayed(R.id.fab_insert_purchase)
    }
}
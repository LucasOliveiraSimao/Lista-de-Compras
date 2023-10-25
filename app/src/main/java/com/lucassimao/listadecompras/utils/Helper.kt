package com.lucassimao.listadecompras.utils

import android.text.Editable
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.lucassimao.listadecompras.data.model.PurchaseModel

fun isFieldValid(field: Editable): Boolean {
    if (field.isEmpty()) {
        return false
    }
    if (field.isBlank()) {
        return false
    }
    return true
}

fun Fragment.warningMessage(message: String): Snackbar = Snackbar.make(
    requireView(),
    message,
    Snackbar.LENGTH_SHORT
)

fun String.putBrazilianMoneySymbol(): String {
    return "R$ $this"
}

fun calculateTotalPurchase(value: List<PurchaseModel>): String {
    var totalAmountOfPurchase = 0.0
    value.forEach {
        totalAmountOfPurchase += multiplyQuantityTimesPrice(it)
    }
    return totalAmountOfPurchase.putTwoDecimalPlaces().putCommaPrice()
}

fun String.removeComma(): String {
    val stringWithoutComma = this.replace(",", "")
    return stringWithoutComma ?: ""
}

private fun multiplyQuantityTimesPrice(it: PurchaseModel) =
    (it.item_price.putPointPrice().toDouble().times(it.item_quantity))

fun Int.putTimesSymbol(): String {
    return "$this x"
}

fun String.putPointPrice():String{
    return this.replace(",",".")
}

fun String.putCommaPrice():String{
    return this.replace(".",",")
}

fun Double.putTwoDecimalPlaces():String{
    return "%.2f".format(this)
}
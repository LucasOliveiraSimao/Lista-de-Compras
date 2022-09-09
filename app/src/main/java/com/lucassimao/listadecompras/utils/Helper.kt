package com.lucassimao.listadecompras.utils

import android.text.Editable
import android.view.View
import com.google.android.material.snackbar.Snackbar
import java.text.DecimalFormat

fun isFieldValid(field: Editable): Boolean {
    if (field.isEmpty()) {
        return false
    }
    if (field.isBlank()) {
        return false
    }
    return true
}

fun warningMessage(view: View, message: String): Snackbar = Snackbar.make(
    view,
    message,
    Snackbar.LENGTH_SHORT
)

fun formatPrice(priceTotal: Double): String {
    val formatPrice = DecimalFormat("#.##")
    return formatPrice.format(priceTotal).toString()
}
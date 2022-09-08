package com.lucassimao.listadecompras.utils

import android.text.Editable
import android.view.View
import com.google.android.material.snackbar.Snackbar

fun isFieldValid(view: View, message: String, field: Editable): Boolean {
    return if (field.isEmpty()) {
        Snackbar.make(view, message, 4000).show()
        false
    } else if (field.isBlank()) {
        Snackbar.make(view, message, 4000).show()
        false
    } else {
        true
    }
}
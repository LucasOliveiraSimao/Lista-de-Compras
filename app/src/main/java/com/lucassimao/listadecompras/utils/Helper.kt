package com.lucassimao.listadecompras.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

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

private const val replaceRegex: String = "[R$,.\u00A0]"
private const val replaceFinal: String = "R$\u00A0"

fun EditText.formatMoneyToBrazilianFormat() {
    this.addTextChangedListener(object : TextWatcher {

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(editable: Editable?) {
            try {
                val stringEditable = editable.toString()
                if (stringEditable.isEmpty()) return

                removeTextChangedListener(this)
                val cleanString = stringEditable.replace(replaceRegex.toRegex(), "")

                val parsed = BigDecimal(cleanString)
                    .setScale(2, BigDecimal.ROUND_FLOOR)
                    .divide(BigDecimal(100), BigDecimal.ROUND_FLOOR)

                val decimalFormat =
                    NumberFormat.getCurrencyInstance(Locale("pt", "BR")) as DecimalFormat
                val formatted = decimalFormat.format(parsed)

                val stringFinal = formatted.replace(replaceFinal, "")

                setText(stringFinal)
                setSelection(stringFinal.length)
                addTextChangedListener(this)
            } catch (_: Exception) {
            }
        }

    })
}

fun String.putBrazilianMoneySymbol(): String {
    return "R$ $this"
}

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
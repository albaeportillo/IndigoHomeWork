package com.indigoag.repository.util

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

fun Double.formatAmount(): String {
    val otherSymbols =
        DecimalFormatSymbols(Locale.getDefault()).apply { decimalSeparator = '.' }
    val dec = DecimalFormat("0.00", otherSymbols)
    return dec.format(this)

}

fun String.capitalizeFirstWords(): String {
    val letters = this.toLowerCase(Locale.getDefault()).toCharArray()
    var firstLetterFound = false

    letters.forEachIndexed { index, c ->
        if (c.isLetter()) {
            if (!firstLetterFound) {
                letters[index] = c.toUpperCase()
                firstLetterFound = true
            }
        } else if (c == ' ' || c == '\n') {
            firstLetterFound = false
        }
    }
    return letters.concatToString()
}
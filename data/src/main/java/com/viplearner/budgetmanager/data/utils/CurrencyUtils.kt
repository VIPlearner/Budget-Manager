package com.viplearner.budgetmanager.data.utils

import timber.log.Timber
import java.text.NumberFormat
import java.util.Locale

fun getPrettyAmountFromCents(amountString: String): String {
    if (amountString.isBlank()) {
        return ""
    }
    return try{
        NumberFormat.getCurrencyInstance(Locale("en", "US"))
            .format((amountString.toDouble() / 100.00))
    }catch (ex: Exception){
        Timber.d(ex)
        ""
    }
}
package com.viplearner.budgetmanager.data.entities

import androidx.annotation.DrawableRes
import com.viplearner.budgetmanager.data.R

enum class TransactionType(
    val value: String,
    @DrawableRes val icon: Int,
){
    TRANSPORTATION("Transportation", R.drawable.transportation),
    FOOD("Food", R.drawable.food),
    SHOPPING("Shopping", R.drawable.shopping),
    EDUCATION("Education", R.drawable.education)
}
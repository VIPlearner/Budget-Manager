package com.viplearner.budgetmanager.data.entities

import android.graphics.Color
import androidx.annotation.DrawableRes

data class BudgetCategoryEntity(
    val transactionType: TransactionType,
    val totalBudget: Float,
    val spentBudget: Float,
    val isEnabled: Boolean = true
)

package com.viplearner.budgetmanager.ext

import com.viplearner.budgetmanager.data.entities.BudgetCategoryEntity
import com.viplearner.budgetmanager.ui.component.BudgetCategory
import com.viplearner.budgetmanager.ui.theme.colorItemList

fun BudgetCategoryEntity.toBudgetCategory() =
    BudgetCategory(
        categoryName = transactionType.value,
        totalBudget = totalBudget,
        spentBudget = spentBudget,
        icon = transactionType.icon,
        remainingBudget = totalBudget-spentBudget,
        color = colorItemList[transactionType.ordinal],
        isEnabled = isEnabled
    )
package com.viplearner.budgetmanager.ui.screens.main.state

import com.viplearner.budgetmanager.ui.component.BudgetCategory

sealed class MainScreenViewState {
    data class Loaded(
        val budgetCategoryList: List<BudgetCategory>,
        val monthYearText: String,
        val currentMonth: Int,
        val currentYear: Int,
        val totalBudgetStr: String,
        val spentBudgetStr: String,
        val remainingBudgetStr: String
    ) : MainScreenViewState()
    data object Loading : MainScreenViewState()
    data class Error(
        val errorMessage : String
    ) : MainScreenViewState()
}
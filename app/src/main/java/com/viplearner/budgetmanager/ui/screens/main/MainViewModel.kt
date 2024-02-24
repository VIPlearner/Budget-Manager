package com.viplearner.budgetmanager.ui.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viplearner.budgetmanager.data.entities.TransactionType
import com.viplearner.budgetmanager.data.room.entities.Transaction
import com.viplearner.budgetmanager.data.service.TransactionService
import com.viplearner.budgetmanager.data.utils.getCurrentMonth
import com.viplearner.budgetmanager.data.utils.getCurrentYear
import com.viplearner.budgetmanager.data.utils.getFirstDayOfCurrentMonth
import com.viplearner.budgetmanager.data.utils.getMonthYearText
import com.viplearner.budgetmanager.ext.toBudgetCategory
import com.viplearner.budgetmanager.ui.screens.main.state.MainScreenViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val transactionService: TransactionService
): ViewModel() {
    private val _viewState: MutableStateFlow<MainScreenViewState> = MutableStateFlow(
        MainScreenViewState.Loading
    )

    val viewState = _viewState.asStateFlow()

    init{
        onMonthYearSelected(getCurrentMonth(), getCurrentYear())
    }

    fun onMonthYearSelected(month: Int, year: Int) = viewModelScope.launch(
        Dispatchers.IO
    ){
        transactionService.getBudgetCategories(month, year)
            .onStart {
                _viewState.value = MainScreenViewState.Loading
            }
            .collectLatest { it ->
                val budgetCategories = it.map {budgetCategoryEntity ->
                    budgetCategoryEntity.toBudgetCategory()
                }
                _viewState.value = MainScreenViewState.Loaded(
                    budgetCategories,
                    currentMonth = month,
                    currentYear = year,
                    monthYearText = getMonthYearText(month, year),
                    totalBudgetStr = "\$${
                        String.format(
                            "%.1f",
                            budgetCategories.sumOf { it.totalBudget.toDouble() })
                    }",
                    spentBudgetStr = "\$${
                        String.format(
                            "%.1f",
                            budgetCategories.sumOf { it.spentBudget.toDouble() })
                    }",
                    remainingBudgetStr = "\$${
                        String.format(
                            "%.1f",
                            budgetCategories.sumOf { it.remainingBudget.toDouble() })
                    }"

                )
            }

    }

    fun addTransaction(amount: String, transactionType: TransactionType)=viewModelScope.launch(Dispatchers.IO){
        transactionService.addTransaction(amount, transactionType)
    }



}
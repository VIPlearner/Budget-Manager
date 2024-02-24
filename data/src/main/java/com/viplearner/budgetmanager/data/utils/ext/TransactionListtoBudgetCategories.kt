package com.viplearner.budgetmanager.data.utils.ext

import com.viplearner.budgetmanager.data.entities.BudgetCategoryEntity
import com.viplearner.budgetmanager.data.entities.TransactionType
import com.viplearner.budgetmanager.data.room.entities.Transaction

fun List<Transaction>.toBudgetCategories(): List<BudgetCategoryEntity>{
    val map = mutableMapOf<TransactionType, Float>()

    for (value in enumValues<TransactionType>()){
        map[value] = 0f
    }
    for (transaction in this){
        if(map.contains(transaction.category)){
            map[transaction.category] = (map[transaction.category]!! + transaction.amount.toFloat())
        }
        else {
            map[transaction.category] = transaction.amount.toFloat()
        }
    }

    return map.map {
        BudgetCategoryEntity(
            transactionType = it.key,
            totalBudget = 200f,
            spentBudget = it.value/100,
        )
    }
}
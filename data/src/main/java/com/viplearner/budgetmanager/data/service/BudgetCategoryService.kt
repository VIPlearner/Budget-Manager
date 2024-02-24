package com.viplearner.budgetmanager.data.service

import com.viplearner.budgetmanager.data.entities.BudgetCategoryEntity
import com.viplearner.budgetmanager.data.entities.TransactionType
import com.viplearner.budgetmanager.data.room.BudgetManagerRoomDatabase
import com.viplearner.budgetmanager.data.room.dao.TransactionDao
import com.viplearner.budgetmanager.data.room.entities.Transaction
import com.viplearner.budgetmanager.data.utils.ext.toBudgetCategories
import com.viplearner.budgetmanager.data.utils.getEpochDatesOfMonth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.temporal.TemporalAmount
import javax.inject.Inject
import javax.inject.Singleton

class TransactionService @Inject constructor(
    db: BudgetManagerRoomDatabase
) {
    private val dao = db.transactionDao
    fun getBudgetCategories(month: Int, year: Int): Flow<List<BudgetCategoryEntity>> {
        val startAndEndDates = getEpochDatesOfMonth(year, month)
        return dao.getTransactionsWithinDateRange(startAndEndDates.first, startAndEndDates.second).map {
            it.toBudgetCategories()
        }
    }

    fun addTransaction(amount: String, transactionType: TransactionType){
        dao.upsertNotification(
            Transaction(
                amount = amount,
                category = transactionType,
                time = System.currentTimeMillis()
            )
        )
    }

}

@Module
@InstallIn(SingletonComponent::class)
object TransactionServiceModule {
    @Provides
    @Singleton
    fun provideTransactionService(
        db: BudgetManagerRoomDatabase
    ): TransactionService = TransactionService(db)
}
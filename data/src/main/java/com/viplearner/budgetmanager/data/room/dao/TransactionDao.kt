package com.viplearner.budgetmanager.data.room.dao

import android.app.Notification
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.viplearner.budgetmanager.data.room.entities.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Upsert
    fun upsertNotification(transaction: Transaction)

    @Query("SELECT * FROM `Transaction` WHERE time BETWEEN :startDate AND :endDate")
    fun getTransactionsWithinDateRange(startDate: Long, endDate: Long): Flow<List<Transaction>>

    @Query("SELECT * FROM `Transaction` WHERE time >= :startDate")
    fun getTransactionsFromDateTillNow(startDate: Long): Flow<List<Transaction>>

    @Query("DELETE FROM `Transaction` WHERE id = :id")
    fun deleteNotification(id: Int)
}
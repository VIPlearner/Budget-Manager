/*
* Copyright (c) 2023. Nomba Financial Services
*
* author: Joshua Owolabi
* email: joshua.owolabi@nomba.com
* github: @IsaacNewton111
*
*/

package com.viplearner.budgetmanager.data.room

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.viplearner.budgetmanager.data.entities.TransactionType
import com.viplearner.budgetmanager.data.room.dao.TransactionDao
import com.viplearner.budgetmanager.data.room.entities.Transaction

@Database(
    entities = [Transaction::class],
    version = 1
)
abstract class BudgetManagerRoomDatabase: RoomDatabase() {
    abstract val transactionDao: TransactionDao
}
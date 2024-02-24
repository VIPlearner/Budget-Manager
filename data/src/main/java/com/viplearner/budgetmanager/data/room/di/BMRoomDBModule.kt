package com.viplearner.budgetmanager.data.room.di

import android.content.Context
import androidx.room.Room
import com.viplearner.budgetmanager.data.room.BudgetManagerRoomDatabase
import com.viplearner.budgetmanager.data.room.entities.TransactionTypeConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BMRoomDBModule {
    companion object {
        @Provides
        @Singleton
        fun provideBudgetManagerRoomDatabase(
            @ApplicationContext context: Context,
        ): BudgetManagerRoomDatabase {
            return Room.databaseBuilder(
                context = context,
                klass = BudgetManagerRoomDatabase::class.java,
                name = "BMRoomDatabase.db"
            ).build()
        }

        @Provides
        @Singleton
        fun providesTransactionDao(
            roomDB: BudgetManagerRoomDatabase
        )= roomDB.transactionDao
    }
}
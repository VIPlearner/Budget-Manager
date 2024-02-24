package com.viplearner.budgetmanager.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.viplearner.budgetmanager.data.entities.TransactionType
import java.util.UUID

@Entity
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Int = UUID.randomUUID().hashCode(),
    val amount: String,
    @TypeConverters(TransactionTypeConverter::class)
    val category: TransactionType,
    val time: Long,
)

@ProvidedTypeConverter
class TransactionTypeConverter {
    @TypeConverter
    fun toTransactionType(value: Int): TransactionType = enumValues<TransactionType>()[value]
    @TypeConverter
    fun fromTransactionType(value: TransactionType): Int = value.ordinal
}

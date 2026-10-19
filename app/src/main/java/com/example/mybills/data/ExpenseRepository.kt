package com.example.mybills.data

import com.example.mybills.domain.Expense
import kotlinx.coroutines.flow.Flow

interface ExpenseRepository {

    suspend fun insert(
        description:String,
        amountInCents:Long,
        category: String,
        dataLabel:String,
        id: Long? = null)

    suspend fun delete(id:Long)

    fun getAll(): Flow<List<Expense>>

    suspend fun getBy(id: Long): Expense?
}
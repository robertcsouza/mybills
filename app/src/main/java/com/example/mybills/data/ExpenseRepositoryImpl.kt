package com.example.mybills.data

import com.example.mybills.domain.Expense
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ExpenseRepositoryImpl(val dao: ExpenseDao ): ExpenseRepository {
    override suspend fun insert(
        description: String,
        amountInCents: Long,
        category: String,
        dataLabel: String,
        id: Long?
    ) {
       val entity =  ExpenseEntity(
           description = description,
           amountInCents = amountInCents,
           category = category,
           dateLabel = dataLabel
           )
       dao.insert(entity)
    }

    override suspend fun delete(id: Long) {
        TODO("Not yet implemented")
    }

    override fun getAll(): Flow<List<Expense>> {
        return dao.getAll().map { entities -> entities.map {
            entity -> Expense(id = entity.id,
            description = entity.description,
            amountInCents = entity.amountInCents,
                category = entity.category,
                dateLabel = entity.dateLabel
                ) }}
    }

    override suspend fun getBy(id: Long): Expense? {
        TODO("Not yet implemented")
    }
}
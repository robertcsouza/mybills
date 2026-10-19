package com.example.mybills.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {
   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insert(entity: ExpenseEntity)
   @Delete
   suspend fun delete(entity: ExpenseEntity)
   @Query("SELECT * FROM expenses")
   fun getAll(): Flow<List<ExpenseEntity>>
   @Query("SELECT * FROM expenses WHERE id = :id")
    suspend fun getBy(id: Long): ExpenseEntity?
}
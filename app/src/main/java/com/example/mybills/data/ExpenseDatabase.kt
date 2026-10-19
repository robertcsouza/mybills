package com.example.mybills.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ExpenseEntity::class], version = 1)
abstract class ExpenseDatabase: RoomDatabase(){
    abstract val expenseDao: ExpenseDao
}
object ExpenseDatabaseProvider {
    @Volatile
    private var INSTANCE: ExpenseDatabase? = null

    fun provide(context: Context): ExpenseDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                ExpenseDatabase::class.java,
                "expense-app"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}
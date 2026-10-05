package com.example.mybills.navigation

import kotlinx.serialization.Serializable


@Serializable
data object  ExpenseRoute

@Serializable
data object  NewExpenseRoute

@Serializable
data class EditExpenseRoute(
    val expenseId : Long
)


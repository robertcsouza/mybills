package com.example.mybills.ui.expenses

import com.example.mybills.domain.Expense

data class  ExpenseUiState(
    val expenses : List<Expense> = emptyList(),
    val isLoading: Boolean = true,
    val error: String? = null,
    val saved: Boolean = false
)
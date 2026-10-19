package com.example.mybills.ui.addEditExpense

data class AddExpenseUiState(
    val amount: String = "",
    val description: String = "",
    val category: String = "",
    val date: String = "",
    val isSaving: Boolean = false,
    val saved: Boolean = false,
    val error: String? = null
)
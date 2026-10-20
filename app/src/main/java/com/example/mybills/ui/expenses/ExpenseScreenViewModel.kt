package com.example.mybills.ui.expenses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybills.data.ExpenseRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


class ExpenseScreenViewModel(private val repository: ExpenseRepository): ViewModel() {


    val uiState = repository.getAll().map {
        expenses ->
        ExpenseUiState(
            expenses = expenses,
            isLoading = false,

        )
    }.catch {
        emit(
            ExpenseUiState(
                isLoading = false,
                error = "Não foi possivel carregar as despesas"
            )
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ExpenseUiState()
    )

}
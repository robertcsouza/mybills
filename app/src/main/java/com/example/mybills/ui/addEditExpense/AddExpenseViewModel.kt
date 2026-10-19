package com.example.mybills.ui.addEditExpense

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybills.data.ExpenseRepository
import kotlinx.coroutines.launch


class AddExpenseViewModel(private val repository: ExpenseRepository): ViewModel(){


    var uiState by mutableStateOf(AddExpenseUiState())
        private set

    fun onAmountChange(value: String) {
        if (uiState.isSaving) return

        if (value.matches(Regex("[0-9]{0,9}([,.][0-9]{0,2})?"))) {
            uiState = uiState.copy(amount = value, error = null)
        }
    }

    fun onDescriptionChange(value: String) {
        if (uiState.isSaving) return
        uiState = uiState.copy(description = value, error = null)
    }

    fun onSelectCategory(value: String){
        if (uiState.isSaving) return
        uiState = uiState.copy(category = value, error = null)
    }

    fun onDateChange(value: String) {
        if (uiState.isSaving) return

        uiState = uiState.copy(
            date = value.filter { it in '0'..'9' }.take(8),
            error = null
        )
    }


    fun saveExpense(){
        print(uiState.toString())
        println("teste")
//        viewModelScope.launch {
//            repository.insert(description = "testing insert",
//                amountInCents = 100,
//                category = "Mercado",
//                dataLabel = "07/10/2026",
//                id = 1
//            )
//        }
    }

}
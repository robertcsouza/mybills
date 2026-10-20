package com.example.mybills.ui.addEditExpense

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybills.data.ExpenseRepository
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException


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


    val amountInCents = uiState.amount
        .replace(',', '.')
        .toBigDecimalOrNull()

    fun saveExpense(){


        val cents = try {
            uiState.amount
                .replace(',', '.')
                .toBigDecimalOrNull()
                ?.movePointRight(2)
                ?.longValueExact()
        } catch (_: ArithmeticException) {
            null
        }

        if (cents == null || cents <= 0) {
            uiState = uiState.copy(
                error = "Informe um valor maior que zero."
            )
            return
        }


        if(uiState.isSaving || uiState.saved) return
        uiState =  uiState.copy(isSaving = false, saved = true)
        try {
            viewModelScope.launch {
                repository.insert(
                    description = uiState.description,
                    amountInCents = cents,
                    category = uiState.category,
                    dataLabel = uiState.date
                )
            }
        }catch (exception: CancellationException){
            throw exception
        }catch (_: Exception){
            uiState.copy(
                isSaving = false,
                error = "Não foi possivel salvar a despesa"
            )
        }
    }

}
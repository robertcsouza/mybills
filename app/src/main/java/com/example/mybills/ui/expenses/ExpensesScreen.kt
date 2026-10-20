package com.example.mybills.ui.expenses

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mybills.data.ExpenseDatabase
import com.example.mybills.data.ExpenseDatabaseProvider
import com.example.mybills.data.ExpenseRepository
import com.example.mybills.data.ExpenseRepositoryImpl
import com.example.mybills.domain.sampleExpenses
import com.example.mybills.ui.components.CategoryFilter
import com.example.mybills.ui.components.ExpenseItem
import com.example.mybills.ui.components.MonthSelector
import com.example.mybills.ui.components.MonthlyTotalCard
import com.example.mybills.ui.components.NewExpenseButton
import com.example.mybills.ui.expenses.components.ExpsensesHeader
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale



@Composable
fun ExpenseScreen(
    onNewExpense: () -> Unit,
    onEditExpense: (Long) -> Unit
) {
    val context = LocalContext.current.applicationContext
    val viewModel = viewModel<ExpenseScreenViewModel> {
        val database = ExpenseDatabaseProvider.provide(context)
        val repository = ExpenseRepositoryImpl(database.expenseDao)
        ExpenseScreenViewModel(repository)
    }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val totalIncents = uiState.expenses.sumOf { expense -> expense.amountInCents }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 24.dp)
    ) {
        ExpsensesHeader()
        MonthSelector(monthLabel = "Outubro", onPreviousMonth = {}, onNextMonth = {})
        MonthlyTotalCard(
            totalInCents = totalIncents,
            expenseCount = uiState.expenses.size
        )
        CategoryFilter(selectedCategory = "Mercado", onCategorySelected = {}, categories = listOf("Mercado","Combustivel","Cartao"))
        LazyColumn (
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            when{
                uiState.isLoading ->{

                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
                else -> {
                    items(
                        items = uiState.expenses,
                        key = { expense -> expense.id }
                    ){
                            expense ->
                        ExpenseItem(expense = expense, onClick = {})
                    }
                }
            }
        }

        NewExpenseButton(onClick = onNewExpense)
    }
}

@Preview(showBackground = true)
@Composable
private fun ExpensesPreview() {
    ExpenseScreen(onNewExpense = {}, onEditExpense = {})
}


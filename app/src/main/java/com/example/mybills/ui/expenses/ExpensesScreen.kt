package com.example.mybills.ui.expenses

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mybills.ui.components.CategoryFilter
import com.example.mybills.ui.components.ExpenseItem
import com.example.mybills.ui.components.MonthSelector
import com.example.mybills.ui.components.MonthlyTotalCard
import com.example.mybills.ui.components.NewExpenseButton
import com.example.mybills.ui.expenses.components.ExpsensesHeader
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

private val categories = listOf(
    "Todas",
    "Alimentação",
    "Transporte",
    "Moradia",
    "Lazer",
    "Saúde",
    "Outros"
)


private fun formatMoney(amountInCents: Long):String {
    val formatter = NumberFormat.getCurrencyInstance(
        Locale("pt","BR")
    )

    return formatter.format(
        BigDecimal.valueOf(amountInCents)
    )
}


@Composable
fun ExpenseScreen(
    onNewExpense: () -> Unit,
    onEditExpense: (Long) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ExpsensesHeader()
        MonthSelector(monthLabel = "Outubro", onPreviousMonth = {}, onNextMonth = {})
        MonthlyTotalCard(
            totalInCents = 128_490L,
            expenseCount = 5
        )
        CategoryFilter(selectedCategory = "Mercado", onCategorySelected = {}, categories = listOf("Mercado","Combustivel","Cartao"))

        ExpenseItem(expense = sampleExpenses[0], onClick = {})
        ExpenseItem(expense = sampleExpenses[1], onClick = {})
        NewExpenseButton(onClick = onNewExpense)

    }
}

@Preview(showBackground = true)
@Composable
private fun ExpensesPreview() {
    ExpenseScreen(onNewExpense = {}, onEditExpense = {})
}


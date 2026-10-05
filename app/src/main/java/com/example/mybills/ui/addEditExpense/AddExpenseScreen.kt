package com.example.mybills.ui.addEditExpense

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddExpenseScreen (
    title: String,
    onBack: () -> Unit,
    expenseId: Long? = null
) {
    Column(
    modifier = Modifier
    .fillMaxSize()
    .padding(24.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium
        )

        if (expenseId != null) {
            Text("ID recebido: $expenseId")
        }

        Button(onClick = onBack) {
            Text("Voltar")
        }
    }
}
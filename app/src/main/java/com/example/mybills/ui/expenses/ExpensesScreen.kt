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
        Text(
            text = "Suas despesas",
            style = MaterialTheme.typography.headlineMedium
        )

        Button(onClick = onNewExpense) {
            Text("Nova despesa")
        }

        Button(onClick = { onEditExpense(1L) }) {
            Text("Editar despesa de exemplo")
        }
    }
}


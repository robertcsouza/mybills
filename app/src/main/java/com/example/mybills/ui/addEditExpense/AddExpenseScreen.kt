package com.example.mybills.ui.addEditExpense

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mybills.data.ExpenseDatabaseProvider
import com.example.mybills.data.ExpenseRepositoryImpl
import com.example.mybills.domain.categories
import com.example.mybills.ui.components.CategorySelect
import com.example.mybills.ui.components.ExpenseDatePicker

@Composable
fun AddExpenseScreen(
    title: String,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {



    val context = LocalContext.current.applicationContext
    val database = ExpenseDatabaseProvider.provide(context)
    val repository = ExpenseRepositoryImpl(database.expenseDao)

    val viewModel = viewModel<AddExpenseViewModel>{
        AddExpenseViewModel(repository)
    }

    val uiState = viewModel.uiState
    val amount = uiState.amount
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextButton(onClick = onBack) {
                Text("Voltar")
            }

            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.weight(1f)
            )
        }

        Text(
            text = "Registre um gasto do seu dia.",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "VALOR DA DESPESA",
                    style = MaterialTheme.typography.labelSmall
                )

                Text(
                    text = "R$ $amount",
                    style = MaterialTheme.typography.displaySmall
                )
            }
        }

        OutlinedTextField(
            value = amount,
            onValueChange = viewModel::onAmountChange,
            modifier = Modifier.fillMaxWidth(),
            prefix = {
                Text("R$")
            },
            placeholder = {
                Text("0,00")
            },

            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            ),
            singleLine = true,
            shape = RoundedCornerShape(12.dp)
        )
        OutlinedTextField(
            value = uiState.description,
            onValueChange = viewModel ::onDescriptionChange,
            enabled = true,

            modifier = Modifier.fillMaxWidth(),
            label = { Text("Descrição") },
            placeholder = { Text("Ex.: compra no mercado") },
            singleLine = true,
            shape = RoundedCornerShape(12.dp)
        )

        CategorySelect(categories = categories, selectedCategory = uiState.category, onSelectCategory = viewModel::onSelectCategory, enable = true )

        ExpenseDatePicker(
            date = uiState.date,
            onDateSelected = viewModel::onDateChange,
            enabled = !uiState.isSaving
        )

        Text(
            text = "Você pode editar este gasto depois.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Button(
            onClick = { viewModel.saveExpense() },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 54.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                text = "Adicionar despesa",
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}
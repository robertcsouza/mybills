package com.example.mybills.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseDatePicker(
    date: String,
    onDateSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    var showDialog by rememberSaveable {
        mutableStateOf(false)
    }

    val displayDate = if (date.length == 8) {
        "${date.take(2)}/${date.substring(2, 4)}/${date.takeLast(4)}"
    } else {
        "Selecione uma data"
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Data",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground
        )

        OutlinedButton(
            onClick = { showDialog = true },
            enabled = enabled,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 56.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(displayDate)
        }
    }

    if (showDialog && enabled) {
        val formatter = remember {
            SimpleDateFormat(
                "ddMMyyyy",
                Locale("pt", "BR")
            ).apply {
                timeZone = TimeZone.getTimeZone("UTC")
                isLenient = false
            }
        }

        val initialDateMillis = remember(date) {
            if (date.matches(Regex("[0-9]{8}"))) {
                runCatching {
                    formatter.parse(date)?.time
                }.getOrNull()
            } else {
                null
            }
        }

        val pickerState = rememberDatePickerState(
            initialSelectedDateMillis = initialDateMillis
        )

        DatePickerDialog(
            onDismissRequest = {
                showDialog = false
            },
            confirmButton = {
                TextButton(
                    enabled = pickerState.selectedDateMillis != null,
                    onClick = {
                        pickerState.selectedDateMillis?.let { millis ->
                            onDateSelected(
                                formatter.format(Date(millis))
                            )
                        }

                        showDialog = false
                    }
                ) {
                    Text("Confirmar")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDialog = false
                    }
                ) {
                    Text("Cancelar")
                }
            }
        ) {
            DatePicker(
                state = pickerState,
                showModeToggle = false
            )
        }
    }
}
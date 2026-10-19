package com.example.mybills.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CategorySelect(
    modifier: Modifier = Modifier,
    categories: List<String>,
    selectedCategory: String,
    onSelectCategory: (String) -> Unit,
    enable: Boolean
) {

    var expanded by remember {
        mutableStateOf(false)
    }

    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {

        Text("Categoria",style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground)

    }

    Box{
        OutlinedButton(onClick = {expanded = true},
            enabled = enable,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 56.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = selectedCategory.ifBlank { "Selecione uma categoria" }, modifier = Modifier.weight(1f))
            Text("▾")

            DropdownMenu(expanded = expanded && enable, onDismissRequest = {expanded = false}) {

                categories.forEach { category ->
                    DropdownMenuItem(text ={Text(category)}, onClick = {
                        onSelectCategory(category)
                        expanded = false
                    } )
                }

            }


        }
    }
    
}
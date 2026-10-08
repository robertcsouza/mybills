package com.example.mybills.ui.components

import android.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.text.NumberFormat
import java.math.BigDecimal
import java.util.Locale

@Composable
fun MonthlyTotalCard(
    totalInCents:Long,
    expenseCount:Int,
    modifier: Modifier = Modifier
) {

    val formatedTotal = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
        .format(BigDecimal.valueOf(totalInCents, 2))

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(text = "Total do Mês", style = MaterialTheme.typography.labelSmall)
            Text(text = formatedTotal, style = MaterialTheme.typography.displaySmall)
            Text(
                text = if(expenseCount == 1){
                    "1  despesa registrada"
                }else{
                    "$expenseCount despesas registradas"
                },
                style = MaterialTheme.typography.bodyMedium

            )
        }
    }
}

@Preview
@Composable
private fun monthlyCardPreview() {

        MonthlyTotalCard(
            totalInCents = 128_490L,
            expenseCount = 5,
            modifier = Modifier.padding(24.dp)
        )

}
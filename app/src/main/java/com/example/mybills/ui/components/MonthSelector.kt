package com.example.mybills.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.w3c.dom.Text
import java.time.Month

@Composable
fun MonthSelector (
    modifier: Modifier = Modifier,
    monthLabel: String,
    onPreviousMonth: () -> Unit,
    onNextMonth: () -> Unit
) {

    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.outline)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            TextButton(
                onClick = onPreviousMonth,
                modifier = Modifier.semantics{
                    contentDescription = "Mês anterior"
                }
            ) {
                Text(
                    text = "<",
                    fontSize = 24.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Text(
                text = monthLabel,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )

            TextButton(
                onClick = onNextMonth,
                modifier = Modifier.semantics{
                    contentDescription = "Próximo mês"
                }
            ) {
                Text(
                    text = ">",
                    fontSize = 24.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            }

        }
    }


}


@Preview
@Composable
private fun MonthSelectorPreview () {
   MonthSelector(monthLabel = "Outubro 2026", onPreviousMonth = {}, onNextMonth = {})
}
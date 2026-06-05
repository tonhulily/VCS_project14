package com.example.vcs_project14.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vcs_project14.data.local.entity.TransactionEntity
import com.example.vcs_project14.presentation.theme.*

@Composable
fun TransactionItem(
    transaction: TransactionEntity
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = CardColor
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    transaction.title,
                    color = TextWhite
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    transaction.category,
                    color = Accent
                )
            }
            Text(
                text =
                    if (transaction.type == "Expense")
                        "- ${transaction.amount}"
                    else
                        "+ ${transaction.amount}",
                color =
                    if (transaction.type == "Expense")
                        Expense
                    else
                        Income
            )
        }
    }
}
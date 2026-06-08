package com.example.vcs_project14.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.vcs_project14.data.local.entity.TransactionEntity
import com.example.vcs_project14.presentation.theme.*
import com.example.vcs_project14.presentation.utils.DateUtils
import java.text.DecimalFormat

@Composable
fun TransactionItem(
    transaction: TransactionEntity,
    showDeleteSpacing: Boolean = true,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(28.dp),
        colors =
            CardDefaults.cardColors(
                containerColor =
                    if (transaction.isCategoryDeleted) Color(0xFFE0E0E0)
                    else CardColor
            ),
        elevation =
            CardDefaults.cardElevation(
                defaultElevation = 4.dp
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 18.dp,
                    top = 18.dp,
                    bottom = 18.dp,
                    end =
                        if (showDeleteSpacing) 64.dp
                        else 28.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .clip(CircleShape)
                        .background(
                            brush =
                                Brush.linearGradient(
                                    listOf(
                                        Primary,
                                        Secondary
                                    )
                                )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Category,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
                Spacer(
                    modifier = Modifier.width(16.dp)
                )
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = transaction.title,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        style =
                            MaterialTheme
                                .typography
                                .titleMedium,
                        fontWeight = FontWeight.Bold,
                        color =
                            if (transaction.isCategoryDeleted) {
                                GrayText
                            } else {
                                TextDark
                            }
                    )
                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )
                    Text(
                        text = transaction.category,
                        color = GrayText
                    )
                    Spacer(
                        modifier = Modifier.height(2.dp)
                    )
                    Text(
                        text =
                            DateUtils.formatDate(
                                transaction.date
                            ),
                        color = GrayText,
                        style =
                            MaterialTheme
                                .typography
                                .bodySmall
                    )
                }
            }
            val formatter = DecimalFormat("#,###")
            Box(
                modifier =
                    Modifier.width(
                        if (showDeleteSpacing) 100.dp
                        else 130.dp
                    ),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(
                    text =
                        if (transaction.type == "Expense") {
                            "- ${formatter.format(transaction.amount)}"
                        } else {
                            "+ ${formatter.format(transaction.amount)}"
                        },
                    style =
                        MaterialTheme
                            .typography
                            .titleMedium,
                    fontWeight = FontWeight.Bold,
                    color =
                        if (transaction.isCategoryDeleted) {
                            GrayText
                        } else {
                            if (transaction.type == "Expense") {
                                ExpenseRed
                            } else {
                                IncomeGreen
                            }
                        }
                )
            }
        }
    }
}
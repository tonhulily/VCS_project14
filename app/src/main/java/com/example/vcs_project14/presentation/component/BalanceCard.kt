package com.example.vcs_project14.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.vcs_project14.presentation.theme.*
import java.text.DecimalFormat

@Composable
fun BalanceCard(
    balance: Double
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(32.dp),
        elevation =
            CardDefaults.cardElevation(
                defaultElevation = 8.dp
            )
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush =
                        Brush.horizontalGradient(
                            listOf(
                                Primary,
                                Secondary
                            )
                        )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(28.dp)
            ) {
                Text(
                    text = "Tổng số dư",
                    color = CardColor,
                    style =
                        MaterialTheme
                            .typography
                            .titleMedium
                )
                Spacer(
                    modifier = Modifier.height(16.dp)
                )
                val formatter = DecimalFormat("#,###")
                Text(
                    text = "${formatter.format(balance)} đ",
                    style =
                        MaterialTheme
                            .typography
                            .headlineLarge,
                    fontWeight = FontWeight.Bold,
                    color = CardColor
                )
            }
        }
    }
}
package com.example.vcs_project14.presentation.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import java.text.NumberFormat
import java.util.*

@Composable
fun CurrencyText(
    amount: Double
) {
    val formatter =
        NumberFormat.getCurrencyInstance(
            Locale("vi", "VN")
        )
    Text(
        formatter.format(amount)
    )
}
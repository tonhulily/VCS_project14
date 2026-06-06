package com.example.vcs_project14.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vcs_project14.presentation.theme.*

@Composable
fun EmptyState() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 80.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.AccountBalanceWallet,
            contentDescription = null,
            tint = Primary,
            modifier = Modifier.size(90.dp)
        )
        Spacer(
            modifier = Modifier.height(20.dp)
        )
        Text(
            text = "Chưa có giao dịch nào",
            style =
                MaterialTheme
                    .typography
                    .headlineSmall,
            color = TextDark
        )
        Spacer(
            modifier = Modifier.height(8.dp)
        )
        Text(
            text = "Hãy thêm giao dịch đầu tiên",
            color = GrayText
        )
    }
}
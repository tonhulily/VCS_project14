package com.example.vcs_project14.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vcs_project14.data.local.entity.TransactionEntity

@Composable
fun EditTransactionDialog(
    transaction: TransactionEntity,
    onDismiss: () -> Unit,
    onSave: (
        TransactionEntity
    ) -> Unit
) {
    var title by remember {
        mutableStateOf(
            transaction.title
        )
    }
    var amount by remember {
        mutableStateOf(
            transaction.amount.toString()
        )
    }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                "Sửa giao dịch"
            )
        },
        text = {
            Column {
                FinanceTextField(
                    value = title,
                    onValueChange = {
                        title = it
                    },
                    label = "Tên"
                )
                Spacer(
                    modifier = Modifier.height(12.dp)
                )
                FinanceTextField(
                    value = amount,
                    onValueChange = {
                        amount = it
                    },
                    label = "Số tiền"
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onSave(
                        transaction.copy(
                            title = title,
                            amount = amount.toDoubleOrNull() ?: 0.0
                        )
                    )
                }
            ) {
                Text("Lưu")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text("Huỷ")
            }
        }
    )
}
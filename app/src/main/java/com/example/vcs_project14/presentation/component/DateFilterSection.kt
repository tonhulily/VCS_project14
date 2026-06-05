package com.example.vcs_project14.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DateFilterSection(
    onFilter: (
        Long,
        Long
    ) -> Unit
) {
    var startDate by remember {
        mutableStateOf("")
    }
    var endDate by remember {
        mutableStateOf("")
    }
    Column {
        FinanceTextField(
            value = startDate,
            onValueChange = {
                startDate = it
            },
            label = "Start timestamp"
        )
        Spacer(
            modifier = Modifier.height(10.dp)
        )
        FinanceTextField(
            value = endDate,
            onValueChange = {
                endDate = it
            },
            label = "End timestamp"
        )
        Spacer(
            modifier = Modifier.height(12.dp)
        )
        FinanceButton(
            text = "Filter"
        ) {
            if (
                startDate.isNotBlank() &&
                endDate.isNotBlank()
            ) {
                onFilter(
                    startDate.toLong(),
                    endDate.toLong()
                )
            }
        }
    }
}
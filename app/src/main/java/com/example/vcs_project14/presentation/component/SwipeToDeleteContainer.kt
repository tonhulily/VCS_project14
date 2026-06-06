package com.example.vcs_project14.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vcs_project14.presentation.theme.*
@Composable
fun SwipeToDeleteContainer(
    onDelete: () -> Unit,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        content()
        Box(
            modifier = Modifier
                .matchParentSize()
                .padding(end = 14.dp),

            contentAlignment = Alignment.CenterEnd
        ) {
            IconButton(
                onClick = onDelete
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(
                            ExpenseRed.copy(
                                alpha = 0.12f
                            ),
                            RoundedCornerShape(
                                16.dp
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        tint = ExpenseRed
                    )
                }
            }
        }
    }
}
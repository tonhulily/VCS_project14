package com.example.vcs_project14.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vcs_project14.presentation.theme.*

@Composable
fun ShimmerLoading() {
    Column {
        repeat(5) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
                    .padding(vertical = 8.dp)
                    .background(
                        GrayText,
                        RoundedCornerShape(24.dp)
                    )
            )
        }
    }
}
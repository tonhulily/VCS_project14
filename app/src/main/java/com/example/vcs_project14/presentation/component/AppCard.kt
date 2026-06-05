package com.example.vcs_project14.presentation.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vcs_project14.presentation.theme.CardColor

@Composable
fun AppCard(
    content: @Composable (
        PaddingValues
    ) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = CardColor
        )
    ) {
        content(PaddingValues(20.dp))
    }
}
package com.example.vcs_project14.presentation.component

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String
) {
    TopAppBar(
        title = {
            Text(title)
        }
    )
}
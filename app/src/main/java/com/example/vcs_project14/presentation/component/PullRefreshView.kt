package com.example.vcs_project14.presentation.component

import androidx.compose.runtime.*
import kotlinx.coroutines.delay

@Composable
fun rememberPullRefreshState(
    onRefresh: suspend () -> Unit
): Boolean {
    var refreshing by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(refreshing) {
        if (refreshing) {
            onRefresh()
            delay(1000)
            refreshing = false
        }
    }
    return refreshing
}
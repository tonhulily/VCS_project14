package com.example.vcs_project14.presentation.screen.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.vcs_project14.presentation.theme.*
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavHostController
) {
    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate(
            "home"
        )
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = null,
                tint = Primary,
                modifier = Modifier.size(120.dp)
            )
            Spacer(
                modifier = Modifier.height(20.dp)
            )
            Text(
                text = "Finance Manager",
                style =
                    MaterialTheme
                        .typography
                        .headlineMedium
            )
        }
    }
}
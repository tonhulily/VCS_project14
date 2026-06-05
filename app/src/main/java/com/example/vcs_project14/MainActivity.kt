package com.example.vcs_project14

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.vcs_project14.presentation.navigation.NavGraph
import com.example.vcs_project14.presentation.theme.FinanceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FinanceTheme {
                NavGraph()
            }
        }
    }
}
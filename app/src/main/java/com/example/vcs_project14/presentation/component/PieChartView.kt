package com.example.vcs_project14.presentation.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun PieChartView() {
    val values = listOf(
        40f,
        25f,
        20f,
        15f
    )
    val colors = listOf(
        Color(0xFFFF4D6D),
        Color(0xFF00D1B2),
        Color(0xFFFFD166),
        Color(0xFF7CFC00)
    )
    Canvas(
        modifier = Modifier
            .size(240.dp)
    ) {
        var startAngle = -90f
        values.forEachIndexed { index, value ->
            val sweepAngle = value / values.sum() * 360f
            drawArc(
                color = colors[index],
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(60f),
                topLeft = Offset(0f, 0f)
            )
            startAngle += sweepAngle
        }
    }
}
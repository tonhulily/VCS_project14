package com.example.vcs_project14.presentation.component

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedPieChart(
    data: Map<String, Double>
) {
    val animation =
        remember {
            Animatable(0f)
        }
    LaunchedEffect(Unit) {
        animation.animateTo(
            targetValue = 1f,
            animationSpec = tween(1500)
        )
    }
    val colors = listOf(
        Color(0xFFFF6B6B),
        Color(0xFF4ECDC4),
        Color(0xFFFFD166),
        Color(0xFF6C63FF)
    )
    val total = data.values.sum()
    if (total == 0.0) return
    Canvas(
        modifier = Modifier.size(260.dp)
    ) {
        var startAngle = -90f
        data.entries.forEachIndexed {
                index,
                entry ->
            val sweepAngle = (entry.value/total) * 360f * animation.value
            drawArc(
                color =
                    colors[
                        index % colors.size
                    ],
                startAngle = startAngle,
                sweepAngle = sweepAngle.toFloat(),
                useCenter = false,
                style = Stroke(90f),
                topLeft = Offset(0f, 0f)
            )
            startAngle += sweepAngle.toFloat()
        }
    }
}
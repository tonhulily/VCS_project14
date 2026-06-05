package com.example.vcs_project14.presentation.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.example.vcs_project14.presentation.theme.*
import java.text.DecimalFormat

@Composable
fun PieChartView(
    data: Map<String, Double>
) {
    val total = data.values.sum()
    if (total <= 0.0) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Chưa có dữ liệu",
                color = GrayText,
                style =
                    MaterialTheme
                        .typography
                        .titleMedium
            )
        }
        return
    }
    val colors = listOf(
        Color(0xFF7B61FF),
        Color(0xFFFF8A65),
        Color(0xFF4CAF50),
        Color(0xFFFFC107),
        Color(0xFF26C6DA),
        Color(0xFFFF5C8A)
    )
    val formatter = DecimalFormat("#,###")
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.size(260.dp),
            contentAlignment = Alignment.Center
        ) {
            Canvas(
                modifier = Modifier.fillMaxSize()
            ) {
                var startAngle = -90f
                val canvasSize = size.minDimension
                val chartSize = canvasSize * 0.78f
                val topLeftOffset = (canvasSize - chartSize) / 2f
                data.entries.forEachIndexed {
                        index,
                        entry ->
                    val sweepAngle = (entry.value / total) * 360f
                    drawArc(
                        color = colors[index % colors.size],
                        startAngle = startAngle,
                        sweepAngle = sweepAngle.toFloat(),
                        useCenter = false,
                        style = Stroke(
                            width = 70f
                        ),
                        topLeft = androidx.compose.ui.geometry.Offset(
                            topLeftOffset,
                            topLeftOffset
                        ),
                        size =
                            Size(
                                chartSize,
                                chartSize
                            )
                    )
                    startAngle += sweepAngle.toFloat()
                }
            }
        }
        Spacer(
            modifier = Modifier.height(24.dp)
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            data.entries.forEachIndexed {
                    index,
                    entry ->
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        modifier = Modifier.size(18.dp),
                        shape = CircleShape,
                        color = colors[index % colors.size]
                    ) {}
                    Spacer(
                        modifier = Modifier.width(10.dp)
                    )
                    Text(
                        text = "${entry.key}: ${formatter.format(entry.value.toInt())}"
                    )
                }
            }
        }
    }
}
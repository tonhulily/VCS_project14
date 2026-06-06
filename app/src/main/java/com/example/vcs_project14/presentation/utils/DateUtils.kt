package com.example.vcs_project14.presentation.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    @SuppressLint("ConstantLocale")
    private val displayFormat =
        SimpleDateFormat(
            "dd/MM/yyyy",
            Locale.getDefault()
        )
    fun formatDate(
        timestamp: Long
    ): String {
        return displayFormat.format(
            Date(timestamp)
        )
    }
    fun parseDate(
        input: String
    ): Long? {
        return try {
            displayFormat.isLenient = false
            displayFormat.parse(input)?.time
        } catch (_: Exception) {
            null
        }
    }
}
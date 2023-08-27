package com.example.mytodoapp

// Data class for holding data
data class Todo(
    val title: String,
    var isChecked: Boolean = false
)
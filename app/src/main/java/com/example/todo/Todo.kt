package com.example.todo.model

data class Todo(
    val id: Int,
    val title: String,
    var isDone: Boolean = false
)

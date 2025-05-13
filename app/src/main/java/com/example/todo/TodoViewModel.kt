package com.example.todo.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.todo.model.Todo

class TodoViewModel : ViewModel() {
    private var nextId = 0
    var todoList = mutableStateListOf<Todo>()
        private set

    fun addTodo(title: String) {
        if (title.isNotBlank()) {
            todoList.add(Todo(nextId++, title))
        }
    }

    fun toggleDone(todo: Todo) {
        todo.isDone = !todo.isDone
    }

    fun removeDone() {
        todoList.removeAll { it.isDone }
    }
}

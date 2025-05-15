package com.example.todo.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import com.example.todo.viewmodel.TodoViewModel

@Composable
fun TodoApp(viewModel: TodoViewModel) {
    var input by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            BasicTextField(
                value = input,
                onValueChange = { input = it },
                textStyle = TextStyle.Default,
                modifier = Modifier
                    .weight(1f)
                    .border(1.dp, MaterialTheme.colorScheme.primary)
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                viewModel.addTodo(input)
                input = ""
            }) {
                Text("추가")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        for (todo in viewModel.todoList) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { viewModel.toggleDone(todo) }
                    .padding(vertical = 8.dp)
            ) {
                Checkbox(
                    checked = todo.isDone,
                    onCheckedChange = { viewModel.toggleDone(todo) }
                )
                Text(
                    text = todo.title,
                    modifier = Modifier.padding(start = 8.dp),
                    textDecoration = if (todo.isDone) TextDecoration.LineThrough else TextDecoration.None
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { viewModel.removeDone() }) {
            Text("완료 항목 모두 삭제")
        }
    }
}

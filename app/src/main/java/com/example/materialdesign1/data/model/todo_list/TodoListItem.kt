package com.example.materialdesign1.data.model.todo_list

import java.util.*

sealed class TodoItem(val id: UUID = UUID.randomUUID())

data class TodoTextItem(val text: String) : TodoItem()
data class TodoImageItem(val image: Int) : TodoItem()

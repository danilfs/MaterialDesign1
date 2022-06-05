package com.example.materialdesign1.data.model.todo_list

import java.util.*

sealed class TodoItem(val id: UUID = UUID.randomUUID())

data class TodoTextItem(val text: String) : TodoItem()
data class TodoImageItem(val url: String) : TodoItem()

fun randomTextItem(): TodoTextItem {
    when (Random().nextInt(10)) {
        0 -> "0"
        1 -> "1"
        2 -> "2"
        3 -> "3"
        4 -> "4"
        5 -> "5"
        6 -> "6"
        7 -> "7"
        8 -> "8"
        9 -> "9"
        else -> "nothing"
    }.also {
        return TodoTextItem(it)
    }
}

fun randomImageItem(): TodoImageItem {
    val randomHeight = 600 + Random().nextInt(300)
    return TodoImageItem("https://picsum.photos/500/$randomHeight")
}

fun randomTodoItem(): TodoItem = when (Random().nextBoolean()) {
    true -> randomTextItem()
    false -> randomImageItem()
}

fun randomTodoList(count: Int): List<TodoItem> = (1..count).map { randomTodoItem() }

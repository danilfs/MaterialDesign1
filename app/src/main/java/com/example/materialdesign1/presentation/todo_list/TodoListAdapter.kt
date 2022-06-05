package com.example.materialdesign1.presentation.todo_list

import coil.load
import com.example.materialdesign1.data.model.todo_list.TodoImageItem
import com.example.materialdesign1.data.model.todo_list.TodoItem
import com.example.materialdesign1.data.model.todo_list.TodoTextItem
import com.example.materialdesign1.databinding.ItemImageTodoBinding
import com.example.materialdesign1.databinding.ItemTextTodoBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun todoTextAdapterDelegate(onClickItem: (TodoTextItem) -> Unit) =
    adapterDelegateViewBinding<TodoTextItem, TodoItem, ItemTextTodoBinding>(
        { layoutInflater, root -> ItemTextTodoBinding.inflate(layoutInflater, root, false) }
    ) {
        binding.root.setOnClickListener { onClickItem(item) }
        bind {
            binding.todoTextView.text = item.text
        }
    }

fun todoImageAdapterDelegate(onClickItem: (TodoImageItem) -> Unit) =
    adapterDelegateViewBinding<TodoImageItem, TodoItem, ItemImageTodoBinding>(
        { layoutInflater, root -> ItemImageTodoBinding.inflate(layoutInflater, root, false) }
    ) {
        binding.root.setOnClickListener { onClickItem(item) }
        bind {
            binding.todoImageView.load(item.url)
        }
    }
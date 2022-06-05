package com.example.materialdesign1.presentation.todo_list

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.materialdesign1.R
import com.example.materialdesign1.data.model.todo_list.TodoImageItem
import com.example.materialdesign1.data.model.todo_list.TodoItem
import com.example.materialdesign1.data.model.todo_list.TodoTextItem
import com.example.materialdesign1.data.model.todo_list.randomTodoList
import com.example.materialdesign1.databinding.FragmentTodoListBinding
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import java.util.*

class TodoListFragment : Fragment(R.layout.fragment_todo_list), TodoItemTouchAdapter {

    private val todoData = randomTodoList(50).toMutableList()

    private val binding by viewBinding(FragmentTodoListBinding::bind)

    private val adapter = ListDelegationAdapter<List<TodoItem>>(
        todoTextAdapterDelegate(::onClickTodoTextItem),
        todoImageAdapterDelegate(::onClickTodoImageItem),
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        adapter.items = todoData
        binding.todoRecyclerView.adapter = adapter
        ItemTouchHelper(TodoItemTouchHelperCallback(this))
            .attachToRecyclerView(binding.todoRecyclerView)
    }

    private fun onClickTodoTextItem(item: TodoTextItem) {
        Toast.makeText(context, "Click text", Toast.LENGTH_SHORT).show()
    }

    private fun onClickTodoImageItem(item: TodoImageItem) {
        Toast.makeText(context, "Click image", Toast.LENGTH_SHORT).show()
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        Collections.swap(todoData, fromPosition, toPosition)
        adapter.notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        todoData.removeAt(position)
        adapter.notifyItemRemoved(position)
    }

}
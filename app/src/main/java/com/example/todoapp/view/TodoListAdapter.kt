package com.example.todoapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.TodoItemLayoutBinding
import com.example.todoapp.model.Todo

class TodoListAdapter(val todoList:ArrayList<Todo>, val adapterOnClick : (Todo) -> Unit)
    : RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>(),
    TodoCheckedChangeListener, TodoEditClickListener {
    class TodoViewHolder(var binding: TodoItemLayoutBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        var binding = TodoItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent,false)
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int)
    {
//        holder.binding.checkTask.text = todoList[position].title
//        holder.binding.checkTask.isChecked = false
//        holder.binding.checkTask.setOnCheckedChangeListener {
//                compoundButton, b ->
//            if(compoundButton.isPressed) {
//                adapterOnClick(todoList[position])
//            }
//        }
//        holder.binding.imgEdit.setOnClickListener {
//            val action =
//                TodoListFragmentDirections.actionEditTodo(todoList[position].uuid)
//
//            Navigation.findNavController(it).navigate(action)
//        }


        // WEEK 10 isi todo nya
        holder.binding.todo = todoList[position] //semua data binding harus di initialize
        holder.binding.listener = this //semua listener harus di initialize
        holder.binding.editListener = this
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    fun updateTodoList(newTodoList: List<Todo>) {
        //untuk me replace to-do kita yang lama dengan yang baru
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }

    override fun onCheckedChanged(cb: CompoundButton,
                                  isChecked: Boolean, obj: Todo) {
        if(cb.isPressed) {
            adapterOnClick(obj)
        }
    }
    override fun onTodoEditClick(v: View) {
        val uuid = v.tag.toString().toInt()
        val action = TodoListFragmentDirections.actionEditTodo(uuid)

        Navigation.findNavController(v).navigate(action)
    }



}

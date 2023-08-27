package com.example.mytodoapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*

class TodoAdapter (
    private val todos:MutableList<Todo> // create a Mutable list of data type T#odo class.

        ) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){

    // RecyclerView view  to allow recycle behaviour which allows only loads when we scroll.
    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate( // Convert the xml into view
                R.layout.item_todo,
                parent,
                false
            )
        )

    }

    //Add items to the list
    fun addTodo(todo: Todo){
        todos.add(todo)
        notifyItemInserted(todos.size -1)   //Notify will help to change the text in the UI.

    }

    //Delete items to the list
    fun deleteDoneTodos(){
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }


    // Change the bg colours of checked to-dos and reset to normal if unchecked.
    private fun changeBgColor(tvToDoTitle: TextView, isChecked: Boolean ){
        if(isChecked){
            tvToDoTitle.setBackgroundColor(Color.parseColor("#FF0000"))  // red bg colour
        }else{
            tvToDoTitle.setBackgroundColor(Color.TRANSPARENT)       // reset bg colour to transparent
        }
    }


    // Set or assign the values to the to-do list, using the user input.
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTodo = todos[position]
        holder.itemView.apply {
            tvToDoTitle.text = curTodo.title
            cbFinished.isChecked = curTodo.isChecked
            changeBgColor(tvToDoTitle, curTodo.isChecked)
            cbFinished.setOnCheckedChangeListener { _, isChecked ->
                changeBgColor(tvToDoTitle, isChecked)
                curTodo.isChecked = !curTodo.isChecked
            }
        }
    }


    // Return the size of the list.
    override fun getItemCount(): Int {
        return todos.size
    }
}
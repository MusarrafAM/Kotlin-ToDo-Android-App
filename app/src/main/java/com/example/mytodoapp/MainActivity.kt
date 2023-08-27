package com.example.mytodoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoAdapter = TodoAdapter(mutableListOf())


        rvToDoItems.adapter = todoAdapter
        rvToDoItems.layoutManager = LinearLayoutManager(this)

        // Add behaviour of the ADD_button
        btnAddToDo.setOnClickListener {
            val todoTitle = etToDOTitle.text.toString()
            if(todoTitle.isNotEmpty()){
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                etToDOTitle.text.clear()
            }
        }

        // Add behaviour of the Delete_Button
        btnDeleteFinishedToDo.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }
    }
}


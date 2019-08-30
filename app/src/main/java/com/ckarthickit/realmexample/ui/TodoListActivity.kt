package com.ckarthickit.realmexample.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ckarthickit.realmexample.R
import com.ckarthickit.realmexample.viewmodel.TodoViewModel
import com.google.android.material.snackbar.Snackbar


class TodoListActivity : AppCompatActivity() {

    private lateinit var rootView: View
    private lateinit var toolBar: Toolbar
    private lateinit var todoListView: RecyclerView

    private lateinit var todoViewModel: TodoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)
        initViews()
        setSupportActionBar(toolBar)
    }

    private fun initViews() {
        rootView = findViewById(R.id.todo_list_root)
        toolBar = findViewById(R.id.todo_list_toolbar)
        todoListView = findViewById(R.id.todo_list_todoList)
        todoViewModel = ViewModelProviders.of(this)[TodoViewModel::class.java]

        todoListView.run {
            println("=========== todoList =======")
            println("todo_items = ${todoViewModel.todoItems}")
            println("todo_items_size = ${todoViewModel.todoItems.size}")
            layoutManager = LinearLayoutManager(this@TodoListActivity)
            setHasFixedSize(true)
            adapter = TodoListAdapter(todoViewModel.todoItems)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_todo_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.addTodo -> launchTodoActivity()
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun launchTodoActivity(): Boolean {
        startActivityForResult(AddTodoActivity.newIntent(this), REQUEST_CODE_ADD_ITEM)
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            REQUEST_CODE_ADD_ITEM -> {
                when (resultCode) {
                    RESPONSE_CODE_SUCCESS -> {
                        Snackbar.make(rootView, "Added!", Snackbar.LENGTH_SHORT).show()
                        todoListView.adapter?.notifyDataSetChanged()
                    }
                    RESPONSE_CODE_FAILURE ->
                        Snackbar.make(rootView, "Cancelled!", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }
}

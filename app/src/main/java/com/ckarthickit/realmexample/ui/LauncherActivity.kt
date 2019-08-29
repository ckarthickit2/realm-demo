package com.ckarthickit.realmexample.ui

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


class LauncherActivity : AppCompatActivity() {

    private lateinit var rootView: View
    private lateinit var toolBar: Toolbar
    private lateinit var todoListView: RecyclerView

    private lateinit var todoViewModel: TodoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)
        initViews()
        setSupportActionBar(toolBar)
    }

    private fun initViews() {
        rootView = findViewById(R.id.launcher_root)
        toolBar = findViewById(R.id.toolbar)
        todoListView = findViewById(R.id.todoList)
        todoViewModel = ViewModelProviders.of(this)[TodoViewModel::class.java]
        todoListView.run {
            println("=========== todoList =======")
            println("todo_items = ${todoViewModel.todoItems}")
            println("todo_items_size = ${todoViewModel.todoItems.size}")
            layoutManager = LinearLayoutManager(this@LauncherActivity)
            setHasFixedSize(true)
            adapter = TodoListAdapter(todoViewModel.todoItems)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_launcher, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.addTodo -> launchTodoActivity()
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun launchTodoActivity(): Boolean {
        Snackbar.make(rootView, "Add Todo", Snackbar.LENGTH_SHORT).show()
        return true
    }
}

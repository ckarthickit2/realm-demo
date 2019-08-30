package com.ckarthickit.realmexample.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.ckarthickit.realmexample.R
import com.ckarthickit.realmexample.data.dao.TodoDao
import com.ckarthickit.realmexample.data.entity.TodoItem
import io.realm.Realm
import java.util.*

class AddTodoActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent()
            intent.action = Intent.ACTION_DEFAULT
            //intent.`package` = context.packageName
            intent.setClass(context, AddTodoActivity::class.java)
            return intent
        }
    }

    private lateinit var toolbar: Toolbar
    private lateinit var titleInput: EditText
    private lateinit var descriptionInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        setSupportActionBar(toolbar)
    }

    private fun initViews() {
        setContentView(R.layout.activity_add_todo)
        toolbar = findViewById(R.id.todo_add_toolbar)
        titleInput = findViewById(R.id.title_input)
        descriptionInput = findViewById(R.id.description_input)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_todo_add, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.save_todo -> saveTodo()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun saveTodo(): Boolean {
        val title = titleInput.text
        val description = descriptionInput.text
        val retVal = when {
            title.isNotBlank() || description.isNotBlank() -> {
                val todoDAo = TodoDao(Realm.getDefaultInstance())
                todoDAo.copyOrUpdate(
                    TodoItem(
                        UUID.randomUUID().toString(),
                        title = title.toString(),
                        description = description.toString()
                    )
                )
                true
            }
            else -> {
                false
            }
        }
        finish()
        return retVal
    }
}

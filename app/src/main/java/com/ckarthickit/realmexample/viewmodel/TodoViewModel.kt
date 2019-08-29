package com.ckarthickit.realmexample.viewmodel

import com.ckarthickit.realmexample.data.dao.TodoDao
import com.ckarthickit.realmexample.data.entity.TodoItem
import io.realm.Realm
import io.realm.RealmResults

class TodoViewModel : androidx.lifecycle.ViewModel() {

    val todoItems
        get() = _todoItems
    private val realmDB: Realm
    private val todoDao: TodoDao
    private var _todoItems: RealmResults<TodoItem>

    init {
        realmDB = Realm.getDefaultInstance()
        todoDao = TodoDao(realmDB)
        _todoItems = todoDao.findAll()
    }

    override fun onCleared() {
        realmDB.close()
    }
}
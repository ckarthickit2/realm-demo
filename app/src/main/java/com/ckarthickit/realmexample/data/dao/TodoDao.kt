package com.ckarthickit.realmexample.data.dao

import com.ckarthickit.realmexample.data.entity.TodoItem
import io.realm.Realm
import io.realm.RealmResults

class TodoDao(
    private val realmDB: Realm
) {
    fun findAll(): RealmResults<TodoItem> {
        return realmDB.where(TodoItem::class.java).findAll()
    }

    fun copyOrUpdate(item: TodoItem): TodoItem? {
        var resultItem: TodoItem? = null
        if (realmDB.isInTransaction) {
            resultItem = realmDB.copyToRealmOrUpdate(item)
        } else {
            realmDB.executeTransaction { db ->
                resultItem = db.copyToRealmOrUpdate(item)
            }
        }
        return resultItem
    }

    fun deleteAll() {
        realmDB.deleteAll()
    }
}
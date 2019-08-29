package com.ckarthickit.realmexample

import android.app.Application
import com.ckarthickit.realmexample.data.dao.TodoDao
import com.ckarthickit.realmexample.data.entity.TodoItem
import io.realm.Realm
import io.realm.RealmConfiguration
import java.util.*

@Suppress("unused")
class RealmApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        Realm.setDefaultConfiguration(
            RealmConfiguration.Builder()
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .initialData { db ->
                    val todoDao = TodoDao(db)
                    todoDao.deleteAll()
                    todoDao.copyOrUpdate(
                        TodoItem(
                            UUID.randomUUID().toString(),
                            "Learn Realm",
                            "Develop a sample app on Realm"
                        )
                    )
                    todoDao.copyOrUpdate(
                        TodoItem(
                            UUID.randomUUID().toString(),
                            "Learn Architecture Components",
                            "Develop a sample app to demo architecture components"
                        )
                    )
                }
                .inMemory()
                .build()
        )
    }
}
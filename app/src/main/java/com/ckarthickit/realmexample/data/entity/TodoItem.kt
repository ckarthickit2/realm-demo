package com.ckarthickit.realmexample.data.entity

import io.realm.RealmObject
import io.realm.annotations.Ignore
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class TodoItem(
//    @PrimaryKey var id: String = "",
//    var title: String = "",
//    var description: String = ""
) : RealmObject() {
    @PrimaryKey
    lateinit var id: String
    lateinit var title: String
    lateinit var description: String
    @Ignore
    var tempValue: Int = 0

    constructor(id: String, title: String, description: String) : this() {
        this.id = id
        this.title = title
        this.description = description
    }
}
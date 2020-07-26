package com.example.myfirstkotlin.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class User {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
    @ColumnInfo(name = "first_name")
    var firstName: String? = null
    @ColumnInfo(name = "last_name")
    var lastName: String? = null
    var age: Int = 0
}
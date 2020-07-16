package com.example.myfirstkotlin.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.myfirstkotlin.model.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(vararg userIds: Int): List<User>

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): User

    @Insert
    fun insert(user: User)
    @Insert
    fun insertAll(vararg users: User)
    @Insert
    fun insertAll(users: List<User>)
    @Delete
    fun delete(user: User)
    @Query("DELETE FROM user")
    fun deleteAll()
    @Query("DELETE FROM user WHERE age < :age")
    fun deleteYoungerThan(age: Int)
}
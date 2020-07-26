package com.example.myfirstkotlin.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myfirstkotlin.dao.UserDao
import com.example.myfirstkotlin.model.User

@Database(entities = [User::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    //参考
    //https://codelabs.developers.google.com/codelabs/android-room-with-a-view-kotlin/#7

    // DAOを取得する。
    abstract fun userDao(): UserDao
    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).allowMainThreadQueries()//メインスレッドでDBアクセスを許可する
                    .fallbackToDestructiveMigration()//既存データ全削除を許容してエンティティに変更入れてもエラーが発生しないようにする
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
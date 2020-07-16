package com.example.myfirstkotlin.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.myfirstkotlin.R
import com.example.myfirstkotlin.dao.UserDao
import com.example.myfirstkotlin.database.AppDatabase
import com.example.myfirstkotlin.model.User
import java.util.*

class UserManageActivity : BaseActivity() {
    private lateinit var myUserDao : UserDao
    private lateinit var adapter: ArrayAdapter<*>
    //DBアクセスしてみる
    //参考
    //https://codelabs.developers.google.com/codelabs/android-room-with-a-view-kotlin/#9
    //https://qiita.com/niusounds/items/fff5e83489e69d7924fd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_manage)
        //toolbarを表示
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        myUserDao = AppDatabase.getDatabase(application).userDao()

        //ユーザをDBから取得する
        val all = getAll()
        //画面表示形式に変換する
        val showItems: Array<String> = toShowItems(all)
        adapter = ArrayAdapter<Any?>(
            this,
            android.R.layout.simple_list_item_1,
            showItems
        )
        //画面に表示
        findViewById<ListView>(R.id.list_user).adapter = adapter

        val btnUserShow: Button = findViewById(R.id.btn_user_show)
        btnUserShow.setOnClickListener {
            //再表示する
            val intent = Intent(application, UserManageActivity::class.java)
            startActivity(intent)
        }
        val btnUserAdd: Button = findViewById(R.id.btn_user_add)
        btnUserAdd.setOnClickListener {
            //Userを追加して再表示する
            val user = User()
            user.uid = Random().nextInt(100000)
            user.firstName = "たいち"
            user.lastName = "なしもと"
            myUserDao.insert(user)
            val intent = Intent(application, UserManageActivity::class.java)
            startActivity(intent)

        }
        val btnUserDelete: Button = findViewById(R.id.btn_user_delete)
        btnUserDelete.setOnClickListener {
            //Userを全削除して再表示する
            myUserDao.deleteAll()
            val intent = Intent(application, UserManageActivity::class.java)
            startActivity(intent)
        }

    }

    private fun getAll(): List<User> {
        //ユーザをDBから取得する
        val all = myUserDao.getAll()
        if (all.isEmpty()) {
            Toast.makeText(this, "Nothing!!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, all[0].uid.toString(), Toast.LENGTH_SHORT).show()
        }
        return all
    }

    private fun toShowItems(users: List<User>): Array<String> {
        var items: Array<String> = emptyArray()
        users.forEach {
            items += """
                |${it.uid}
                |${it.lastName}
                |${it.firstName}""".trimMargin()
        }
        return items
    }
}
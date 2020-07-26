package com.example.myfirstkotlin.activity

import android.content.DialogInterface
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import com.example.myfirstkotlin.R
import com.example.myfirstkotlin.dao.UserDao
import com.example.myfirstkotlin.database.AppDatabase
import com.example.myfirstkotlin.model.User
import java.util.*


class UserManageActivity : BaseActivity() {
    private lateinit var myUserDao: UserDao
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

        //全ユーザ情報をDBから取得する
        val all = getAll()
        //画面表示形式に変換する
        val showItems: ArrayList<String> = toShowItems(all)
        adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            showItems
        )
        //画面に表示
        val listView = findViewById<ListView>(R.id.lvUsers)
        findViewById<ListView>(R.id.lvUsers).adapter = adapter

        //更新ボタン
        val btnUserShow: Button = findViewById(R.id.btnUserLoad)
        btnUserShow.setOnClickListener {
            //DBから再取得して再表示する
            showItems.clear()
            val showItems2: ArrayList<String> = toShowItems(getAll())
            showItems.addAll(showItems2)
            adapter.notifyDataSetChanged()
        }
        //追加ボタン
        val btnUserAdd: Button = findViewById(R.id.btn_user_add)
        btnUserAdd.setOnClickListener {
            //参考
            //https://101010.fun/posts/android-try-fragment.html

            //EditTextの値を取得
            val lastName = findViewById<EditText>(R.id.etLastName)
            val firstName = findViewById<EditText>(R.id.etFirstName)

            //Userを追加する
            val user = User()
            //user.uid = Random().nextInt(100000)  uidはautoGenerateを使って連番でInsertする
            user.firstName = firstName.text.toString()
            user.lastName = lastName.text.toString()

            if (user.firstName == "" || user.lastName == "") {
                Toast.makeText(this, "ユーザ情報を入力してね", Toast.LENGTH_LONG).show()
            } else {
                myUserDao.insert(user)
                //Insertしたら画面の入力をクリア
                lastName.text.clear()
                firstName.text.clear()
                Toast.makeText(
                    this,
                    "ユーザ追加したよ\n 姓:${user.lastName}　名:${user.firstName}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        //リストからユーザを長押しした時の処理
        listView.setOnItemLongClickListener { parent, view, position, id ->
            // ダイアログを作成してほんとに削除していいか確認
            AlertDialog.Builder(this).apply {
                // 長押しされたテキストを取得
                val tmp = (view as AppCompatTextView).text
                // ユーザIDを取得
                val deleteTargetId = tmp.split("\n")[0]
                val deleteTargetName = tmp.split("\n")[1]
                setTitle("ユーザ削除")
                setMessage(
                    getString(
                        //可変パラメータ
                        R.string.confirm_delete_user,
                        deleteTargetId,
                        deleteTargetName
                    )
                )
                // OKをタップしたときの処理
                setPositiveButton(R.string.ok, DialogInterface.OnClickListener { _, _ ->
                    //指定Userを削除して再表示する
                    val user = User()
                    user.uid = deleteTargetId.toInt()
                    myUserDao.delete(user)
                    Toast.makeText(context, "削除したよ!!", Toast.LENGTH_LONG).show()
                    //画面上から削除
                    showItems.removeAt(position)
                    adapter.notifyDataSetChanged()
                })
                // cancelをタップしたときの処理
                setNegativeButton(getString(R.string.cancel), null)
                show()
            }
            true
        }
        //全削除ボタン
        val btnUserDelete: Button = findViewById(R.id.btnUserDeleteAll)
        btnUserDelete.setOnClickListener {
            // ダイアログを作成してほんとに削除していいか確認
            AlertDialog.Builder(this).apply {
                setTitle("全削除するよ？")
                setMessage("全削除していい場合はOKを押してね。")
                setPositiveButton("OK", DialogInterface.OnClickListener { _, _ ->
                    // OKをタップしたときの処理
                    //Userを全削除して再表示する
                    myUserDao.deleteAll()
                    Toast.makeText(context, "全ユーザ削除したよ!!", Toast.LENGTH_LONG).show()
                    showItems.clear()
                    val showItems2: ArrayList<String> = toShowItems(getAll())
                    showItems.addAll(showItems2)
                    adapter.notifyDataSetChanged()
                })
                setNegativeButton(getString(R.string.cancel), null)
                show()
            }
        }

    }

    //DBからデータを全取得
    private fun getAll(): List<User> {
        //ユーザをDBから取得する
        val all: List<User> = myUserDao.getAll()
        if (all.isEmpty()) {
            Toast.makeText(this, "Nobody here!!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Found " + all.size + " users!", Toast.LENGTH_SHORT).show()
        }
        return all
    }

    //DBから取得したデータを画面表示形式に変換する
    private fun toShowItems(users: List<User>): ArrayList<String> {
        val items = arrayListOf<String>()
        users.forEach {
            //「"」３つで囲むとヒアドキュメントになる
            items.add(
                """
                |${it.uid}
                |${it.lastName} ${it.firstName}""".trimMargin()
            )
        }
        return items
    }
}
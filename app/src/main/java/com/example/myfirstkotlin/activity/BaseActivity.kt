package com.example.myfirstkotlin.activity

import android.content.Intent
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myfirstkotlin.R
//全画面共通の機能はこの基底アクティビティに入れる
//各アクティビティはこのクラスを継承する
open class BaseActivity : AppCompatActivity() {
    //右上にオプションボタンを表示する
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.example_menu, menu)
        return true
    }
    //メニューアイテムが選択された時に何かする
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btnHome -> {
                //TOPへ戻る
                Toast.makeText(this, "btnHome が選択されたよ", Toast.LENGTH_SHORT).show()
                val intent = Intent(application, MainActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.btnOpenBrowser -> {
                //OpenBrowserActivityへ
                Toast.makeText(this, "btnOpenBrowser が選択されたよ", Toast.LENGTH_SHORT).show()
                val intent = Intent(application, OpenBrowserActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.btnMisc -> {
                //サブメニュー表示するだけ
                Toast.makeText(this, "btnMisc が選択されたよ", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.btnDoNothing -> {
                //NothingActivityへ
                Toast.makeText(this, "btnDoNothing が選択されたよ", Toast.LENGTH_SHORT).show()
                val intent = Intent(application, NothingActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.btn_user_manage -> {
                //UserManageActivityへ
                Toast.makeText(this, "btnUserManage が選択されたよ", Toast.LENGTH_SHORT).show()
                val intent = Intent(application, UserManageActivity::class.java)
                startActivity(intent)
                return true
            }
            else->return super.onOptionsItemSelected(item)
        }
    }

}
package com.example.myfirstkotlin.activity


import android.content.Intent
import android.os.Bundle
import android.widget.Button
//import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.myfirstkotlin.R


//Activityはアプリ内の１つの画面を表す
class MainActivity : BaseActivity() {

    //onCreateはActivityで一番最初に動くメソッド
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //toolbarを表示
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        //ID指定でXML要素をactivity_mainから取得して変数に格納
        val btnViewList: Button = findViewById(R.id.button1_1)
        val btnOpenBrowser: Button = findViewById(R.id.button1_2)
        val btnNothing: Button = findViewById(R.id.button1_3)

        //setOnClickListener：ボタンクリックされたときに何かする
        btnViewList.setOnClickListener {
            //OpenBrowserActivityを呼ぶ
            val intent = Intent(application, ViewListActivity::class.java)
            startActivity(intent)
        }
        btnOpenBrowser.setOnClickListener {
            //OpenBrowserActivityを呼ぶ
            startActivity(Intent(application, OpenBrowserActivity::class.java))
        }
        btnNothing.setOnClickListener {
            //NothingActivityを呼ぶ
            startActivity(Intent(application, NothingActivity::class.java))
        }
    }
}
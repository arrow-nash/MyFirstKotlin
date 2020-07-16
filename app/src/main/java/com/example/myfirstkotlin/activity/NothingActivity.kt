package com.example.myfirstkotlin.activity

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.myfirstkotlin.R

class NothingActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nothing)
        //toolbarを表示
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val btnNothing = findViewById<TextView>(R.id.button3_1)
        btnNothing.setOnClickListener {
            Toast.makeText(this, "工事中だよー", Toast.LENGTH_SHORT).show()
        }
    }
}
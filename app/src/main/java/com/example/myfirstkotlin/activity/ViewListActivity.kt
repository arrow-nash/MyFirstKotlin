package com.example.myfirstkotlin.activity


import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
//import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.myfirstkotlin.R
import com.example.myfirstkotlin.model.Case
import java.io.BufferedReader
import java.io.InputStreamReader


//Activityはアプリ内の１画面が持つ機能を表すアクションクラスみたいなもの
class ViewListActivity : BaseActivity() {
    private val inputFile = "corona.tsv"

    //[Kotlin]varで定義すると変数、valで定義するとfinal
    private var header: Array<String> = emptyArray()
    private var data: Array<Case> = emptyArray()

    //onCreateはActivityで一番最初に動くメソッド
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        //toolbarを表示
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        readCsv(inputFile)
        val showItems: Array<String> = toShowItems(data)

        val adapter: ArrayAdapter<*> = ArrayAdapter<Any?>(
            this,
            android.R.layout.simple_list_item_1,
            showItems
        )
        findViewById<ListView>(R.id.listView).adapter = adapter
    }


    private fun readCsv(filename: String) {
        val file = resources.assets.open(filename)
        val fileReader = BufferedReader(InputStreamReader(file))
        var i = 0
        //タブ区切りデータを1行ずつ読み込んでオブジェクトに格納する
        fileReader.forEachLine {
            if (it.isNotBlank()) {
                if (i == 0) {
                    //最初の行はヘッダとする
                    header = it.split("\t").toTypedArray()
                } else {
                    //それ以外はデータ
                    val line = it.split("\t").toTypedArray()
                    arrayToObj(line)
                }
            }
            i++
        }
    }

    //文字列リストをオブジェクトリストに変換する
    private fun arrayToObj(line: Array<String>) {
        val case = Case(
            date = line[0],
            cases = line[1]
        )
        data += case
    }
    //画面表示形式に変換する
    private fun toShowItems(data: Array<Case>): Array<String> {
        var items: Array<String> = emptyArray()
        data.forEach {
            //"""で囲まれるとヒアドキュメントになる（改行とかインデントがそのまま画面出力される）
            //https://www.atmarkit.co.jp/ait/articles/1801/30/news010_3.html
            //https://kotlinlang.org/docs/reference/basic-types.html#string-templates
            items += """
                |${it.date}
                |${it.cases}""".trimMargin()
        }
        return items
    }
}
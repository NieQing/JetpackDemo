package cn.nieqingliu.jetpackdemo.ui.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import cn.nieqingliu.jetpackdemo.R
import cn.nieqingliu.jetpackdemo.ViewModel.WordViewModel
import cn.nieqingliu.jetpackdemo.entity.Word
import cn.nieqingliu.jetpackdemo.ui.adapter.WordListAdapter

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private val NEW_WORD_ACTIVITY_REQUEST_CODE = 1
    private lateinit var mWordViewModel: WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            startActivityForResult(
                Intent(this@MainActivity, NewWordActivity::class.java),
                NEW_WORD_ACTIVITY_REQUEST_CODE
            )
        }

        val adapter = WordListAdapter(this)
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(this)

        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)
        mWordViewModel.allWords.observe(this, Observer<List<Word>> {
            adapter.setWord(it!!)
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            val word = Word(data?.getStringExtra(NewWordActivity.EXTRA_REPLY))
            mWordViewModel.insert(word)
        } else {
            Toast.makeText(this, R.string.empty_not_saved, Toast.LENGTH_LONG).show()
        }
    }
}

package cn.nieqingliu.jetpackdemo.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import cn.nieqingliu.jetpackdemo.R
import cn.nieqingliu.jetpackdemo.entity.Word
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class WordListAdapter(private val context: Context) : RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {

    private var mWords: List<Word>? = null

    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvWord: TextView = itemView.findViewById(R.id.textView)

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): WordViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recyclerview_item, p0, false)
        return WordViewHolder(view)
    }

    override fun onBindViewHolder(p0: WordViewHolder, p1: Int) {
        if (mWords != null) {
            val current = mWords!![p1]
            p0.itemView.textView.text = current.word
        } else {
            p0.itemView.textView.text = "No Word"
        }
    }

    fun setWord(words: List<Word>) {
        mWords = words
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return if (mWords != null) {
            mWords!!.size
        } else {
            0
        }
    }
}
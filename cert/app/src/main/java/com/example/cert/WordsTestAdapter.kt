package com.example.cert

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WordsTestAdapter (
    private val words: List<Words>
): RecyclerView.Adapter<WordsTestAdapter.WordTestViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        WordTestViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_word_test,parent,false)
        )

    override fun onBindViewHolder(holder: WordTestViewHolder, position: Int) {
        holder.bind(words[position])
    }

    override fun getItemCount() = words.size

    class WordTestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val wordTestView: TextView = itemView.findViewById(R.id.wordTestView)
        private val expTestView: TextView = itemView.findViewById(R.id.expTestView)

        private val gradingBtn: Button = itemView.findViewById(R.id.gradingBtn)
        private val submissionBtn: Button = itemView.findViewById(R.id.submissionBtn)

        fun bind(words: Words) {
            wordTestView.text = words.word
            expTestView.text = words.exp

            wordTestView.visibility = View.GONE

            submissionBtn.setOnClickListener {
                wordTestView.visibility = View.VISIBLE
            }

            gradingBtn.setOnClickListener {
                wordTestView.visibility = View.VISIBLE
            }
        }

    }

}
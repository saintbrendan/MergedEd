package com.example.mergeded.adapter

import android.content.ClipData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mergeded.R
import com.example.mergeded.callback.WordsDiffCallback

//import com.example.wordorder.callback.WordsDiffCallback

/**
 * A @androidx.recyclerview.widget.RecyclerView adapter to show draggable items
 *
 * @param onDragStarted will provide the current draggable view value. String in this case
 * */
class WordsAdapter(private val onDragStarted: (String) -> Unit) : ListAdapter<String, WordsAdapter.WordsViewHolder>(
    WordsDiffCallback()
) {
    private var sentenceAdapter: SentenceAdapter = SentenceAdapter(this)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_word, parent, false)

        return WordsViewHolder(view)
    }

    override fun onBindViewHolder(holder: WordsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun removeItem(position: Int) {
        val list = ArrayList(currentList)
        list.removeAt(position)
        submitList(list)
    }

    fun addItem(selectedWord: String) {
        val list = ArrayList(currentList)
        list.add(selectedWord)
        submitList(list)
    }

    fun setSentenceAdapter(sentenceAdapter: SentenceAdapter) {
        sentenceAdapter.also { this.sentenceAdapter = it }
    }

    inner class WordsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(word: String) = itemView.run {
            findViewById<TextView>(R.id.tvWord).text = word

            setOnClickListener {
                removeItem(adapterPosition)
                sentenceAdapter.addItem(word)
            }
        }
    }
}

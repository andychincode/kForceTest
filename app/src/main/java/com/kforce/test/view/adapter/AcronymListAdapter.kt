package com.kforce.test.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kforce.test.R
import com.kforce.test.model.Longform
import kotlinx.android.synthetic.main.layout_acronym_item.view.*

class AcronymListAdapter(private var words: List<Longform>): RecyclerView.Adapter<AcronymListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_acronym_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = words[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return words.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(words: List<Longform>) {
        this.words = words
        notifyDataSetChanged()
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val txtWord = view.txtWord

        fun bind(item: Longform) {
            txtWord.text = item.longform
        }
    }
}
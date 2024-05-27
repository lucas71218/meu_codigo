package com.music.activebeat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class MusicAdapter(private var musicList: List<Music>, private val onItemClick: (Music) -> Unit) : RecyclerView.Adapter<MusicAdapter.MusicViewHolder>(), Filterable {

    private var filteredMusicList = musicList.toList()

    inner class MusicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.title_textview)
        private val artistTextView: TextView = itemView.findViewById(R.id.artist_textview)

        fun bind(music: Music) {
            titleTextView.text = music.title
            artistTextView.text = music.artist
            itemView.setOnClickListener { onItemClick(music) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_music, parent, false)
        return MusicViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        holder.bind(filteredMusicList[position])
    }

    override fun getItemCount() = filteredMusicList.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val query = constraint?.toString()?.trim()?.lowercase()
                val results = FilterResults()
                results.values = if (query.isNullOrEmpty()) {
                    musicList
                } else {
                    musicList.filter {
                        it.title.lowercase().contains(query) || it.artist.lowercase().contains(query)
                    }
                }
                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                if (results?.values is List<*>) {
                    @Suppress("UNCHECKED_CAST")
                    val filteredList = results.values as List<Music>
                    updateMusicList(filteredList)
                }
            }
        }
    }

    fun updateMusicList(newMusicList: List<Music>) {
        val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize() = filteredMusicList.size
            override fun getNewListSize() = newMusicList.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return filteredMusicList[oldItemPosition].title == newMusicList[newItemPosition].title
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return filteredMusicList[oldItemPosition] == newMusicList[newItemPosition]
            }
        })
        filteredMusicList = newMusicList
        diffResult.dispatchUpdatesTo(this)
    }

    fun addItem(music: Music) {
        val newList = filteredMusicList.toMutableList()
        newList.add(music)
        updateMusicList(newList)
        notifyItemInserted(newList.size - 1)
    }

    fun removeItem(position: Int) {
        val newList = filteredMusicList.toMutableList()
        newList.removeAt(position)
        updateMusicList(newList)
        notifyItemRemoved(position)
    }

    fun updateItem(position: Int, newMusic: Music) {
        val newList = filteredMusicList.toMutableList()
        newList[position] = newMusic
        updateMusicList(newList)
        notifyItemChanged(position)
    }

}
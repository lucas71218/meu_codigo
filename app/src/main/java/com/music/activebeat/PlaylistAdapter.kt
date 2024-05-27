package com.music.activebeat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PlaylistAdapter(
    private val playlists: List<Playlist>,
    private val editCallback: (Playlist) -> Unit,
    private val deleteCallback: (Playlist) -> Unit
) : RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.playlist_item_layout, parent, false)
        return PlaylistViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        val playlist = playlists[position]
        holder.bind(playlist)
    }

    override fun getItemCount(): Int {
        return playlists.size
    }

    inner class PlaylistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val playlistNameTextView: TextView = itemView.findViewById(R.id.playlistNameTextView)
        private val numberOfSongsTextView: TextView = itemView.findViewById(R.id.numberOfSongsTextView)
        private val editButton: ImageButton = itemView.findViewById(R.id.editButton)
        private val deleteButton: ImageButton = itemView.findViewById(R.id.deleteButton)

        fun bind(playlist: Playlist) {
            playlistNameTextView.text = playlist.name
            numberOfSongsTextView.text = itemView.context.getString(R.string.number_of_songs, playlist.numberOfSongs)

            editButton.setOnClickListener { editCallback(playlist) }
            deleteButton.setOnClickListener { deleteCallback(playlist) }
        }
    }
}
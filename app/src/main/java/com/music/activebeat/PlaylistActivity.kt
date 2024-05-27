package com.music.activebeat

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PlaylistActivity : AppCompatActivity() {

    private lateinit var playlistRecyclerView: RecyclerView
    private lateinit var adapter: PlaylistAdapter
    private val playlists = mutableListOf(
        Playlist("Playlist 1", 10),
        Playlist("Playlist 2", 15),
        Playlist("Playlist 3", 20)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.playlist_layout)

        playlistRecyclerView = findViewById(R.id.playlistRecyclerView)

        val layoutManager = LinearLayoutManager(this)
        playlistRecyclerView.layoutManager = layoutManager
        adapter = PlaylistAdapter(playlists, ::onEditPlaylist, ::onDeletePlaylist)
        playlistRecyclerView.adapter = adapter

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fabAddPlaylist: FloatingActionButton = findViewById(R.id.fab_add_playlist)
        fabAddPlaylist.setOnClickListener { showAddEditPlaylistDialog() }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem):

            Boolean {
        return when (item.itemId) {
            R.id.action_home -> {
                startActivity(Intent(this, MainActivity::class.java))
                true
            }
            R.id.action_album -> {
                startActivity(Intent(this, AlbumActivity::class.java))
                true
            }
            R.id.action_artist -> {
                startActivity(Intent(this, ArtistActivity::class.java))
                true
            }
            R.id.action_playlist -> {
                startActivity(Intent(this, PlaylistActivity::class.java))
                true
            }
            R.id.action_music -> {
                startActivity(Intent(this, MusicActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun onEditPlaylist(playlist: Playlist) {
        showAddEditPlaylistDialog(playlist)
    }

    private fun onDeletePlaylist(playlist: Playlist) {
        AlertDialog.Builder(this)
            .setTitle("Delete Playlist")
            .setMessage("Are you sure you want to delete this playlist?")
            .setPositiveButton("Delete") { dialog, _ ->
                deletePlaylist(playlist)
                dialog.dismiss()
            }
            .setNegativeButton("Cancel", null)
            .create()
            .show()
    }

    private fun deletePlaylist(playlist: Playlist) {
        val index = playlists.indexOf(playlist)
        if (index != -1) {
            playlists.removeAt(index)
            adapter.notifyItemRemoved(index)
        }
    }

    private fun showAddEditPlaylistDialog(playlist: Playlist? = null) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_edit_playlist, null)
        val playlistNameEditText = dialogView.findViewById<EditText>(R.id.playlistNameEditText)
        val songCountEditText = dialogView.findViewById<EditText>(R.id.songCountEditText)

        if (playlist != null) {
            playlistNameEditText.setText(playlist.name)
            songCountEditText.setText(playlist.numberOfSongs.toString())
        }

        AlertDialog.Builder(this)
            .setTitle(if (playlist == null) "Add Playlist" else "Edit Playlist")
            .setView(dialogView)
            .setPositiveButton("Save") { dialog, _ ->
                val newName = playlistNameEditText.text.toString()
                val newSongCount = songCountEditText.text.toString().toIntOrNull() ?: 0

                if (playlist == null) {
                    addPlaylist(Playlist(newName, newSongCount))
                } else {
                    editPlaylist(playlist, newName, newSongCount)
                }
                dialog.dismiss()
            }
            .setNegativeButton("Cancel", null)
            .create()
            .show()
    }

    private fun addPlaylist(playlist: Playlist) {
        playlists.add(playlist)
        adapter.notifyItemInserted(playlists.size - 1)
    }

    private fun editPlaylist(playlist: Playlist, newName: String, newSongCount: Int) {
        val index = playlists.indexOf(playlist)
        if (index != -1) {
            playlists[index] = playlist.copy(name = newName, numberOfSongs = newSongCount)
            adapter.notifyItemChanged(index)
        }
    }
}
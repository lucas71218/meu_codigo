package com.music.activebeat

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MusicActivity : AppCompatActivity() {

    private lateinit var musicRecyclerView: RecyclerView
    private lateinit var musicAdapter: MusicAdapter
    private lateinit var searchView: SearchView
    private lateinit var btnAdd: Button
    private lateinit var btnRemove: Button
    private lateinit var btnUpdate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        searchView = findViewById(R.id.search_view)
        musicRecyclerView = findViewById(R.id.music_recycler_view)
        musicRecyclerView.layoutManager = LinearLayoutManager(this)

        btnAdd = findViewById(R.id.btn_add)
        btnRemove = findViewById(R.id.btn_remove)
        btnUpdate = findViewById(R.id.btn_update)

        val musicList = listOf(
            Music("Song 1", "Artist 1"),
            Music("Song 2", "Artist 2"),
            // Adicione mais músicas aqui
        )

        musicAdapter = MusicAdapter(musicList) { music ->
            val intent = Intent(this, MusicPlayerActivity::class.java).apply {
                putExtra("music_title", music.title)
                putExtra("music_artist", music.artist)
                putExtra("album_art", R.drawable.ic_album_placeholder)
            }
            startActivity(intent)
        }
        musicRecyclerView.adapter = musicAdapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                musicAdapter.filter.filter(newText)
                return true
            }
        })

        btnAdd.setOnClickListener {
            val newMusic = Music("New Song", "New Artist")
            musicAdapter.addItem(newMusic)
        }

        btnRemove.setOnClickListener {
            if (musicAdapter.itemCount > 0) {
                musicAdapter.removeItem(musicAdapter.itemCount - 1)
            }
        }

        btnUpdate.setOnClickListener {
            if (musicAdapter.itemCount > 0) {
                val updatedMusic = Music("Updated Song", "Updated Artist")
                musicAdapter.updateItem(0, updatedMusic)
            }
        }
    }
}
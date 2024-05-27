package com.music.activebeat

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AlbumActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.album_layout)

        // Inicializar o RecyclerView
        val albumRecyclerView: RecyclerView = findViewById(R.id.albumRecyclerView)

        // Dados fictícios de álbuns (substitua com seus próprios dados)
        val albums = listOf(
            Album("Album 1", "Artista 1"),
            Album("Album 2", "Artista 2"),
            Album("Album 3", "Artista 3")
        )

        // Configurar o RecyclerView
        val layoutManager = LinearLayoutManager(this)
        albumRecyclerView.layoutManager = layoutManager
        val adapter = AlbumAdapter(albums)
        albumRecyclerView.adapter = adapter

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_home -> {
                // Voltar para a MainActivity
                startActivity(Intent(this, MainActivity::class.java))
                true
            }
            R.id.action_album -> {
                // Navegar para AlbumActivity
                startActivity(Intent(this, AlbumActivity::class.java))
                true
            }
            R.id.action_artist -> {
                // Navegar para ArtistActivity
                startActivity(Intent(this, ArtistActivity::class.java))
                true
            }
            R.id.action_playlist -> {
                // Navegar para PlaylistActivity
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

}

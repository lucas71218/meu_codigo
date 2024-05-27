package com.music.activebeat

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class ArtistActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.artist_layout)

        // Referências aos componentes de layout usando findViewById
        val artistProfileImageView = findViewById<ImageView>(R.id.artistProfileImageView)
        val artistNameTextView = findViewById<TextView>(R.id.artistNameTextView)

        // Lógica para configurar os componentes de layout
        artistProfileImageView.setImageResource(R.drawable.default_artist_profile_image)
        artistNameTextView.text = getString(R.string.artist_name_placeholder)

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

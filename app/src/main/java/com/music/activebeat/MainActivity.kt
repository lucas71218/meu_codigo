package com.music.activebeat

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
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
            R.id.action_music -> {
                startActivity(Intent(this, MusicActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

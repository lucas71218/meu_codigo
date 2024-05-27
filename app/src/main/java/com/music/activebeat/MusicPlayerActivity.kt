package com.music.activebeat

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MusicPlayerActivity : AppCompatActivity() {

    private lateinit var musicTitle: TextView
    private lateinit var musicArtist: TextView
    private lateinit var albumArt: ImageView
    private lateinit var playPauseButton: ImageButton
    private lateinit var prevButton: ImageButton
    private lateinit var nextButton: ImageButton
    private lateinit var seekBar: SeekBar
    private lateinit var mediaPlayer: MediaPlayer
    private var isPlaying = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_player)

        musicTitle = findViewById(R.id.music_title)
        musicArtist = findViewById(R.id.music_artist)
        albumArt = findViewById(R.id.album_art)
        playPauseButton = findViewById(R.id.btn_play_pause)
        prevButton = findViewById(R.id.btn_prev)
        nextButton = findViewById(R.id.btn_next)
        seekBar = findViewById(R.id.music_seekbar)

        val musicTitleText = intent.getStringExtra("music_title")
        val musicArtistText = intent.getStringExtra("music_artist")
        val albumArtResource = intent.getIntExtra("album_art", R.drawable.ic_album_placeholder)

        musicTitle.text = musicTitleText
        musicArtist.text = musicArtistText
        albumArt.setImageResource(albumArtResource)

        mediaPlayer = MediaPlayer.create(this, R.raw.sample_music)

        playPauseButton.setOnClickListener {
            if (isPlaying) {
                mediaPlayer.pause()
                playPauseButton.setImageResource(R.drawable.ic_play)
            } else {
                mediaPlayer.start()
                playPauseButton.setImageResource(R.drawable.ic_pause)
            }
            isPlaying = !isPlaying
        }

        nextButton.setOnClickListener {
            // Implemente a lógica para tocar a próxima música
        }

        prevButton.setOnClickListener {
            // Implemente a lógica para tocar a música anterior
        }

        mediaPlayer.setOnPreparedListener {
            seekBar.max = mediaPlayer.duration
        }

        mediaPlayer.setOnSeekCompleteListener {
            seekBar.progress = mediaPlayer.currentPosition
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}
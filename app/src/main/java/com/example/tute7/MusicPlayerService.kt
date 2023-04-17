package com.example.tute7

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.IBinder
import java.io.IOException

class MusicPlayerService : Service(),
    MediaPlayer.OnPreparedListener {

    private lateinit var mediaPlayer: MediaPlayer
    private var currentTrack = 0
    private lateinit var trackList: List<Int>

    var isPaused = false
    var nowPlaying = ""

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer()
        mediaPlayer.setOnPreparedListener(this)
        trackList = listOf(R.raw.track1, R.raw.track2 ,R.raw.track3)
    }
    override fun onPrepared(p0: MediaPlayer?) {
        TODO("Not yet implemented")

    }

    private fun playTrack(trackIndex:Int){
        val uri =
            Uri.parse("android.resource://$packageName/${trackList[trackIndex]}")
        nowPlaying = "Now Playing: Track: Track ${trackIndex +1}"
        if (isPaused){
            mediaPlayer.start()
            isPaused = false
        }else{
            try {
                mediaPlayer.reset()
                mediaPlayer = MediaPlayer.create(this,uri)
                mediaPlayer.setOnPreparedListener(this)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    fun play(){
        playTrack(currentTrack)
    }
    fun pauseTrack(){
        mediaPlayer.pause()
        isPaused = true
    }
    fun skipTrack() {
        currentTrack = (currentTrack + 1) % trackList.size
        playTrack(currentTrack)
    }
}
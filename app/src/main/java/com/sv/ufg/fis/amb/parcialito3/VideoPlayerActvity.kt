package com.sv.ufg.fis.amb.parcialito3

import android.net.Uri
import android.os.Bundle
import android.widget.VideoView
import androidx.activity.ComponentActivity
import com.sv.ufg.fis.amb.parcialito3.R

class VideoPlayerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        val videoUri = intent.getStringExtra("videoUri")?.let { Uri.parse(it) }
        val videoView = findViewById<VideoView>(R.id.videoView)

        videoUri?.let {
            videoView.setVideoURI(it)
            videoView.start()
        }
    }
}

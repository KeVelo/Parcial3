package com.sv.ufg.fis.amb.parcialito3

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.VideoView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import com.sv.ufg.fis.amb.parcialito3.ui.theme.Parcialito3Theme
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {

    private var videoUri: Uri? = null
    private val videoDirectory by lazy {
        File(getExternalFilesDir(null), "Videos").apply { mkdirs() }
    }

    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                recordVideo()
            } else {
                Toast.makeText(this, "Permiso de cámara es necesario para grabar video", Toast.LENGTH_SHORT).show()
            }
        }

        setMainScreen()
    }

    private fun setMainScreen() {
        setContent {
            Parcialito3Theme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    MainScreen(
                        onRecordVideoClick = { requestPermissionLauncher.launch(Manifest.permission.CAMERA) },
                        onViewSavedVideosClick = { setVideoListScreen() },
                        onPlayLastVideoClick = { playLastVideo() }
                    )
                }
            }
        }
    }

    private fun recordVideo() {
        val videoFile = File(videoDirectory, "VID_${SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())}.mp4")
        videoUri = FileProvider.getUriForFile(this, "${packageName}.provider", videoFile)

        val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE).apply {
            putExtra(MediaStore.EXTRA_OUTPUT, videoUri)
        }
        startActivity(intent)
    }

    private fun setVideoListScreen() {
        setContent {
            Parcialito3Theme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    VideoListScreen(videoDirectory) {
                        setMainScreen()
                    }
                }
            }
        }
    }

    private fun playLastVideo() {
        val lastVideoFile = videoDirectory.listFiles()?.maxByOrNull { it.lastModified() }
        lastVideoFile?.let { file ->
            val intent = Intent(this, VideoPlayerActivity::class.java).apply {
                putExtra("videoUri", Uri.fromFile(file).toString())
            }
            startActivity(intent)
        }
    }
}

@Composable
fun MainScreen(
    onRecordVideoClick: () -> Unit,
    onViewSavedVideosClick: () -> Unit,
    onPlayLastVideoClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Parcial 3 - MG101121", style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.primary)

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onRecordVideoClick,
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text("Grabar Video", color = MaterialTheme.colorScheme.onPrimary)
        }

        Button(
            onClick = onViewSavedVideosClick,
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text("Ver Videos Guardados", color = MaterialTheme.colorScheme.onSecondary)
        }

        Button(
            onClick = onPlayLastVideoClick,
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text("Reproducir Último Video", color = MaterialTheme.colorScheme.onPrimary)
        }
    }
}

@Composable
fun VideoListScreen(videoDirectory: File, onBackClick: () -> Unit) {
    val videoFiles = videoDirectory.listFiles()?.filter { it.extension == "mp4" } ?: emptyList()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(
            onClick = onBackClick,
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text("Volver a la pantalla principal", color = MaterialTheme.colorScheme.onSecondary)
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(videoFiles) { videoFile ->
                val context = LocalContext.current
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = videoFile.name, modifier = Modifier.weight(1f))

                    Button(
                        onClick = {
                            val intent = Intent(context, VideoPlayerActivity::class.java).apply {
                                putExtra("videoUri", Uri.fromFile(videoFile).toString())
                            }
                            context.startActivity(intent)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                    ) {
                        Text("Reproducir", color = MaterialTheme.colorScheme.onPrimary)
                    }
                }
            }
        }
    }
}

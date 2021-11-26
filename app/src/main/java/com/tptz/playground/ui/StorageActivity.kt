package com.tptz.playground.ui

import android.os.Bundle
import android.os.Environment
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class StorageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val textView = TextView(this).apply {
            append("getFilesDir():$SPACE$filesDir\n")
            append("getCacheDir():$SPACE$cacheDir\n")
            append("getExternalCacheDir():$SPACE$externalCacheDir\n")
            append("getExternalFileDir(null):$SPACE${getExternalFilesDir(null)}\n")
            append("getExternalFileDir(DIRECTORY_MUSIC):$SPACE${getExternalFilesDir(Environment.DIRECTORY_MUSIC)}\n")
            append("getFileStreamPath(\"app-debug.apk\"):$SPACE${getFileStreamPath("app-debug.apk")}\n")
            append("getDir(\"dex\", 0):$SPACE${getDir("dex", 0)}\n")
            append("Environment.getDataDirectory():$SPACE${Environment.getDataDirectory()}\n")
            append("Environment.getExternalStorageDirectory():$SPACE${Environment.getExternalStorageDirectory()}\n")
            append("File.pathSeparator:$SPACE${File.pathSeparator}\n")
        }

        setContentView(textView)
    }
    
    companion object {
        const val SPACE = "\n        "
    }
}
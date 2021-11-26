package com.tptz.playground.ui

import android.os.Bundle
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import com.tptz.playground.databinding.ActivitySeekbarBinding
import kotlin.math.abs

/**
 * @author Created by Chang Liu on 2021/11/26
 */
class SeekBarActivity : AppCompatActivity() {
    private var lastBrightness = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySeekbarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.attributes.apply {
            screenBrightness = 0.1f
            window.attributes = this
        }
        binding.bar.setOnSeekBarChangeListener(
            object : OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                    var progress = progress
                    if (progress < 10) {
                        progress = 10
                    }
                    if (abs(progress - lastBrightness) < 5) {
                        return
                    }
                    window.attributes.apply {
                        screenBrightness = progress / 100f
                        window.attributes = this
                    }
                    lastBrightness = progress.toFloat()
                }

                override fun onStartTrackingTouch(seekBar: SeekBar) {}
                override fun onStopTrackingTouch(seekBar: SeekBar) {}
            }
        )
    }
}
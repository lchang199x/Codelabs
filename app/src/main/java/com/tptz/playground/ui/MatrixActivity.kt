package com.tptz.playground.ui

import android.graphics.*
import android.graphics.Shader.TileMode
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.tptz.playground.R

class MatrixActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ImageView(this).apply {
            layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
            setImageBitmap(
                createReflection(R.drawable.blossom)
            )
            setContentView(this)
        }
    }

    private fun createReflection(resId: Int): Bitmap? {
        val source = scale(resId)
        val matrix = Matrix().apply {
            setScale(1f, -1f)
        }

        val reflection = Bitmap.createBitmap(
            source,
            0,
            source.height / 2,
            source.width,
            source.height / 3,
            matrix,
            false
        )

        val compose = Bitmap.createBitmap(
            source.width,
            source.height + source.height / 3 + 5,
            source.config
        )
        val canvas = Canvas(compose).apply {
            drawBitmap(source, 0f, 0f, null)
            drawBitmap(reflection, 0f, (source.height + 5).toFloat(), null)
        }
        val paint = Paint().apply {
            shader = LinearGradient(
                0f, source.height + 5f, 0f, compose.height.toFloat(),
                Color.BLACK, Color.TRANSPARENT, TileMode.CLAMP
            )
            xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
        }

        canvas.drawRect(
            0f, source.height + 5f, source.width.toFloat(), compose.height.toFloat(), paint
        )
        return compose
    }

    private fun scale(
        resId: Int
    ): Bitmap {
        val options = BitmapFactory.Options().apply {
            inJustDecodeBounds = true
        }
        BitmapFactory.decodeResource(resources, resId, options)

        val width = options.outWidth
        val height = options.outHeight
        Log.d(TAG, "width: $width, height: $height")

        var inSampleSize = 1

        if (width > height && width > WIDTH) {
            inSampleSize = width / WIDTH
        } else if (height > width && height > HEIGHT) {
            inSampleSize = height / HEIGHT
        }

        Log.d(TAG, "inSampleSize: $inSampleSize")

        options.inJustDecodeBounds = false
        options.inSampleSize = inSampleSize

        return BitmapFactory.decodeResource(resources, R.drawable.blossom, options)
    }

    companion object {
        private const val TAG = "MatrixActivity"
        private const val WIDTH = 800
        private const val HEIGHT = 800
    }
}
package cc.changliu.play.ui

import android.graphics.*
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import cc.changliu.play.base.HomeScreenActivity

class ShaderActivity : HomeScreenActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val textView = TextView(this).apply {
            layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
            gravity = Gravity.CENTER_HORIZONTAL

            setTextSize(TypedValue.COMPLEX_UNIT_SP, TEXT_SIZE_SP)
            paint.apply {
                val rect = Rect()
                getTextBounds(TEXT, 0, TEXT.length, rect)
                shader = shader(rect.height().toFloat())
            }
            text = TEXT
        }

        val textView1 = TextView(this).apply {
            layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
            gravity = Gravity.CENTER_HORIZONTAL

            setTextSize(TypedValue.COMPLEX_UNIT_SP, TEXT_SIZE_SP)
            paint.apply {
                shader = shader(lineHeight.toFloat())
            }
            text = TEXT
        }

        val imageView = ImageView(this)

        val parent = LinearLayout(this).apply {
            layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)

            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            addView(textView)
            addView(textView1)
            addView(imageView)
        }

        val paint = Paint().apply {
            style = Paint.Style.STROKE
            strokeWidth = 20f
            shader = shader(BITMAP_SIZE.toFloat())
        }

        Bitmap.createBitmap(BITMAP_SIZE, BITMAP_SIZE, Bitmap.Config.ARGB_8888).let {
            Canvas(it).apply {
                drawRect(Rect(0, 0, BITMAP_SIZE, BITMAP_SIZE), paint)
            }
            imageView.setImageBitmap(it)
        }

        setContentView(parent)
    }

    companion object {
        private const val TEXT = "Shader"
        private const val TEXT_SIZE_SP = 100f
        private const val BITMAP_SIZE = 800
    }

    private fun shader(height: Float) = LinearGradient(
        0f, 0f, 0f, height,
        Color.rgb(0x0A, 0xDF, 0xFF),
        Color.rgb(0xFF, 0x41, 0x81),
        Shader.TileMode.CLAMP
    )
}
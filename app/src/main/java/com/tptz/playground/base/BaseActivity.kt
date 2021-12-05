package com.tptz.playground.base

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

/**
 * @author Created by Chang Liu on 2021/12/05
 */
open class BaseActivity : AppCompatActivity() {
    override fun onResume() {
        super.onResume()
        ViewCompat.getWindowInsetsController(findViewById(android.R.id.content))?.let {
            it.hide(WindowInsetsCompat.Type.navigationBars())
            it.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }
}
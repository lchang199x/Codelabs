package com.tptz.playground

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.google.android.flexbox.*
import com.tptz.playground.databinding.ActivityMainBinding
import com.tptz.playground.util.collectActivitiesExcept

/**
 * @author Created by Chang Liu on 2021/11/24
 */
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val list = collectActivitiesExcept("com.tptz.playground.MainActivity")

        val adapter = HomeAdapter(list).apply {
            setOnItemClickListener(object : HomeAdapter.OnItemClickListener {
                override fun onClick(position: Int) {
                    Intent().apply {
                        setClassName(this@MainActivity, list[position])
                        navigate(this)
                    }
                }
            })
        }
        binding.recyclerView.apply {
            this.adapter = adapter
            layoutManager = FlexboxLayoutManager(this@MainActivity).apply {
                flexDirection = FlexDirection.ROW
                alignItems = AlignItems.FLEX_START
                flexWrap = FlexWrap.WRAP
                justifyContent = JustifyContent.SPACE_AROUND
            }
        }
    }

    private fun navigate(intent: Intent) {
        packageManager
            .resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY)
            ?: return Toast.makeText(this, "Coming soon...", Toast.LENGTH_SHORT).show()

        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        ViewCompat.getWindowInsetsController(binding.root)?.let {
            it.hide(WindowInsetsCompat.Type.navigationBars())
            it.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }
}
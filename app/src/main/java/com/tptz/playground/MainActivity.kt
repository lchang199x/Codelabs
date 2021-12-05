package com.tptz.playground

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.tptz.playground.base.BaseActivity
import com.tptz.playground.databinding.ActivityMainBinding
import com.tptz.playground.util.collectActivitiesExcept

/**
 * @author Created by Chang Liu on 2021/11/24
 */
class MainActivity : BaseActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val list = collectActivitiesExcept("com.tptz.playground.MainActivity").sorted()
        list.forEach {
            MaterialButton(this).apply {
                id = View.generateViewId()
                isAllCaps = false
                text = getDisplayName(it)
                binding.container.addView(this)
                binding.flow.addView(this)
            }.setOnClickListener { _ ->
                Intent().apply {
                    setClassName(this@MainActivity, it)
                    navigate(this)
                }
            }
        }
    }

    private fun navigate(intent: Intent) {
        packageManager
            .resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY)
            ?: return Toast.makeText(this, "Coming soon...", Toast.LENGTH_SHORT).show()

        startActivity(intent)
    }

    private fun getDisplayName(name: String): String {
        val act = name.split('.').last()
        return act.substring(0, act.length - "Activity".length)
    }
}
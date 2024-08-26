package cc.changliu.codelabs.ui

import android.os.Bundle
import androidx.appcompat.content.res.AppCompatResources
import cc.changliu.codelabs.R
import cc.changliu.codelabs.base.HomeScreenActivity
import cc.changliu.codelabs.databinding.ActivityPokerBinding

class PokerActivity : HomeScreenActivity() {
    private lateinit var binding: ActivityPokerBinding
    private var count = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imgIds = arrayOf(
            R.drawable.red_joker,
            R.drawable.black_joker,
            R.drawable.ace_of_spades,
            R.drawable.ace_of_hearts,
            R.drawable.ace_of_clubs,
            R.drawable.ace_of_diamonds,
            R.drawable.ten_of_spades,
            R.drawable.ten_of_hearts,
            R.drawable.ten_of_clubs,
            R.drawable.ten_of_diamonds
        )
        binding.viewer1.setOnClickListener {
            binding.viewer1.setImageDrawable(
                AppCompatResources.getDrawable(this, imgIds[count++ % imgIds.size])
            )
        }
    }
}
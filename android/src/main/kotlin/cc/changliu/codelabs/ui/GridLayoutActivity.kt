package cc.changliu.codelabs.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cc.changliu.codelabs.R
import cc.changliu.codelabs.base.HomeScreenActivity
import cc.changliu.codelabs.databinding.ActivityGridBinding
import cc.changliu.codelabs.util.GridLayoutItemDecoration

class GridLayoutActivity : HomeScreenActivity() {
    private lateinit var binding: ActivityGridBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGridBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val texts = listOf(
            "ker",
            "joker",
            "spades",
            "hearts",
            "clubs",
            "diamonds",
            "spades",
            "hearts",
            "clubs",
            "diamonds",
            "dnds",
            "dionds",
            "diams",
            "monds",
            "diamonds",
            "onds",
            "ds"
        )
        binding.root.layoutManager = GridLayoutManager(this, 3)
        binding.root.adapter = MyAdapter(texts)
        binding.root.addItemDecoration(GridLayoutItemDecoration(this@GridLayoutActivity, R.drawable.bg_h_divider, R.drawable.bg_v_divider))
    }

    class MyAdapter(val texts: List<String>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_grid_layout, parent, false))
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.itemView.findViewById<TextView>(R.id.textview).text = texts[position]
        }

        override fun getItemCount(): Int {
            return texts.size
        }

        class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    }
}
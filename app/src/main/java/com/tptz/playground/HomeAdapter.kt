package com.tptz.playground

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.tptz.playground.HomeAdapter.HomeHolder
import com.tptz.playground.MainApp.Companion.getContext

class HomeAdapter(private val activities: List<String>) : RecyclerView.Adapter<HomeHolder>() {
    private var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): HomeHolder {
        val view = LayoutInflater.from(getContext()).inflate(
            R.layout.item_list_home,
            viewGroup, 
            false
        )
        return HomeHolder(view)
    }

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        holder.bindView(activities[position])
        holder.itemView.findViewById<View>(R.id.button).setOnClickListener {
            onItemClickListener?.onClick(position)
        }
    }

    class HomeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val button: Button = itemView.findViewById(R.id.button)

        fun bindView(s: String) {
            val activityNames = s.split(".")
            val name = activityNames[activityNames.size - 1]
            button.text = name.substring(0, name.length - "Activity".length)
        }

    }

    override fun getItemCount(): Int {
        return activities.size
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    interface OnItemClickListener {
        fun onClick(position: Int)
    }
}
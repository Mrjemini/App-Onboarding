package com.aspire.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aspire.CardItem
import com.aspire.R

class CardAdapter(private val items: List<CardItem>) :
    RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    inner class CardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvDuration: TextView = view.findViewById(R.id.tvDuration)
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvPoints: TextView = view.findViewById(R.id.tvPoints)
        val tvCoins: TextView = view.findViewById(R.id.tvCoins)
        val tvJoined: TextView = view.findViewById(R.id.tvJoined)
        val tvDescription: TextView = view.findViewById(R.id.tvDescription)
        val tvTask1: TextView = view.findViewById(R.id.tvTask1)
        val tvTask2: TextView = view.findViewById(R.id.tvTask2)
        val tvTask3: TextView = view.findViewById(R.id.tvTask3)
        val tvTask4: TextView = view.findViewById(R.id.tvTask4)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        val screenWidth = parent.resources.displayMetrics.widthPixels
        val layoutParams = RecyclerView.LayoutParams(
            (screenWidth * 0.8).toInt(),
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.marginEnd = (screenWidth * 0.02).toInt() // spacing between cards
        view.layoutParams = layoutParams

        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val item = items[position]
        holder.tvDuration.text = item.duration
        holder.tvTitle.text = item.title
        holder.tvPoints.text = item.points
        holder.tvCoins.text = item.coins
        holder.tvJoined.text = item.joined
        holder.tvDescription.text = item.description
        val context = holder.itemView.context
        holder.tvTask1.text =
            context.getString(R.string.bullet_task, item.tasks.getOrNull(0).orEmpty())
        holder.tvTask2.text =
            context.getString(R.string.bullet_task, item.tasks.getOrNull(1).orEmpty())
        holder.tvTask3.text =
            context.getString(R.string.bullet_task, item.tasks.getOrNull(2).orEmpty())
        holder.tvTask4.text =
            context.getString(R.string.bullet_task, item.tasks.getOrNull(3).orEmpty())
        val layoutParams = holder.itemView.layoutParams as RecyclerView.LayoutParams
        val sideMargin = (holder.itemView.resources.displayMetrics.widthPixels * 0.02).toInt()
        layoutParams.marginEnd = if (position == items.size - 1) sideMargin else 0
        layoutParams.marginStart = 0
        holder.itemView.layoutParams = layoutParams
    }

    override fun getItemCount(): Int = items.size
}
package com.nbs.composemigration.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import coil3.size.Scale
import com.nbs.composemigration.databinding.ItemScorerBinding
import com.nbs.composemigration.model.TopScorer

class TopScorerAdapter(
    private val onClick: (Int) -> Unit
) : RecyclerView.Adapter<TopScorerAdapter.TopScorerViewHolder>() {

    private var scorers: List<TopScorer> = emptyList()

    inner class TopScorerViewHolder(
        val binding: ItemScorerBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(scorer: TopScorer) {
            binding.tvRank.text = (bindingAdapterPosition + 1).toString()

            binding.tvPlayerName.text = scorer.name
            binding.tvClubName.text = scorer.club.name
            binding.tvGoalCount.text = scorer.goal.toString()

            binding.ivPlayerPhoto.load(scorer.imageUrl) {
                scale(Scale.FIT)
            }

            binding.ivClubLogo.load(scorer.club.logoUrl)

            binding.root.setOnClickListener {
                onClick(scorer.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopScorerViewHolder {
        val binding = ItemScorerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return TopScorerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopScorerViewHolder, position: Int) {
        holder.bind(scorers[position])
    }

    override fun getItemCount() = scorers.size

    fun submitList(newList: List<TopScorer>) {
        scorers = newList
        notifyDataSetChanged()
    }
}
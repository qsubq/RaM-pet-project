package com.github.qsubq.rampetproject.app.presentation.screen.episodes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.qsubq.rampetproject.databinding.EpisodeItemLayoutBinding
import com.github.qsubq.rampetproject.data.model.episodeModel.Result

class EpisodesAdapter : RecyclerView.Adapter<EpisodesAdapter.EpisodesViewHolder>() {
    class EpisodesViewHolder(val binding : EpisodeItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    private var episodesList = emptyList<Result>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesViewHolder {
        val itemBinding = EpisodeItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EpisodesViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: EpisodesViewHolder, position: Int) {
        holder.binding.apply {
            tvEpisodeItemName.text = episodesList[position].name
            tvEpisodeItemDate.text = episodesList[position].air_date
        }
    }

    override fun getItemCount(): Int {
        return episodesList.size
    }

    fun setList(list : List<Result>){
        episodesList = list
        notifyDataSetChanged()
    }


}
package com.github.qsubq.rampetproject.app.presentation.screen.episodes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.qsubq.rampetproject.app.presentation.screen.episodes.EpisodesPageAdapter.MyViewHolder
import com.github.qsubq.rampetproject.data.model.episodeModel.Result
import com.github.qsubq.rampetproject.databinding.EpisodeItemLayoutBinding

class EpisodesPageAdapter : PagingDataAdapter<Result, MyViewHolder>(diffCallBack) {


    inner class MyViewHolder(val binding: EpisodeItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        val diffCallBack = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = getItem(position)

        holder.binding.apply {
            tvEpisodeItemName.text = currentItem?.name
            tvEpisodeItemDate.text = currentItem?.air_date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(EpisodeItemLayoutBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }


}
package com.github.qsubq.rampetproject.app.presentation.screen.allCharacter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.qsubq.rampetproject.app.presentation.screen.allCharacter.AllCharacterPageAdapter.MyViewHolder
import com.github.qsubq.rampetproject.data.model.characterModel.CharacterModelItem
import com.github.qsubq.rampetproject.databinding.CharacterItemLayoutBinding
import com.squareup.picasso.Picasso

class AllCharacterPageAdapter : PagingDataAdapter<CharacterModelItem, MyViewHolder>(diffCallBack) {
    inner class MyViewHolder(val binding: CharacterItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        val diffCallBack = object : DiffUtil.ItemCallback<CharacterModelItem>() {
            override fun areItemsTheSame(
                oldItem: CharacterModelItem,
                newItem: CharacterModelItem,
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: CharacterModelItem,
                newItem: CharacterModelItem,
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = getItem(position)

        holder.binding.apply {
            tvItemName.text = currentItem?.name
            Picasso.get().load(currentItem?.image).into(imgItemCharacter)

            tvItemStatus.text = currentItem?.status

            when (currentItem?.status) {
                "Dead" -> tvItemStatus.setTextColor(Color.RED)
                "Alive" -> tvItemStatus.setTextColor(Color.GREEN)
                else -> tvItemStatus.setTextColor(Color.GRAY)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(CharacterItemLayoutBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }
}

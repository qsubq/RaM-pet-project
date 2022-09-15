package com.github.qsubq.rampetproject.screen.search

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.qsubq.rampetproject.databinding.CharacterItemLayoutBinding
import com.github.qsubq.rampetproject.model.characterModel.CharacterModelItem
import com.github.qsubq.rampetproject.model.searchModel.Result
import com.squareup.picasso.Picasso

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    class SearchViewHolder(val binding: CharacterItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var characterList = emptyList<Result>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val itemBinding =
            CharacterItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.binding.apply {
            tvItemName.text = characterList[position].name
            Picasso.get().load(characterList[position].image).into(imgItemCharacter)

            tvItemStatus.text = characterList[position].status

            when (characterList[position].status) {
                "Dead" -> tvItemStatus.setTextColor(Color.RED)
                "Alive" -> tvItemStatus.setTextColor(Color.GREEN)
                else -> tvItemStatus.setTextColor(Color.GRAY)
            }
        }
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    fun setList(list: List<Result>) {
        characterList = list
        notifyDataSetChanged()
    }
}
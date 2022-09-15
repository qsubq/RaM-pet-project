package com.github.qsubq.rampetproject.app.presentation.screen.search

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.github.qsubq.rampetproject.R
import com.github.qsubq.rampetproject.data.model.characterModel.CharacterModelItem
import com.github.qsubq.rampetproject.databinding.CharacterItemLayoutBinding
import com.squareup.picasso.Picasso

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    class SearchViewHolder(val binding: CharacterItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var characterList = emptyList<CharacterModelItem>()

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

    fun setList(list: List<CharacterModelItem>) {
        characterList = list
        notifyDataSetChanged()
    }

    override fun onViewAttachedToWindow(holder: SearchViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable("character", characterList[holder.adapterPosition])
            Navigation.findNavController(holder.itemView)
                .navigate(R.id.action_searchFragment_to_detailFragment, bundle)
        }
    }
}
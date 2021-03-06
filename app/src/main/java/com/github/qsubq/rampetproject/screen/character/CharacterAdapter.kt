package com.github.qsubq.rampetproject.screen.character

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.qsubq.rampetproject.databinding.CharacterItemLayoutBinding
import com.github.qsubq.rampetproject.model.characterModel.CharacterModelItem
import com.squareup.picasso.Picasso

class CharacterAdapter : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {
    class CharacterViewHolder(val binding: CharacterItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)


    private var listCharacter = emptyList<CharacterModelItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val itemBinding =
            CharacterItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.binding.apply {
            tvItemName.text = listCharacter[position].name
            Picasso.get().load(listCharacter[position].image).into(imgItemCharacter)

            tvItemStatus.text = listCharacter[position].status

            when (listCharacter[position].status) {
                "Dead" -> tvItemStatus.setTextColor(Color.RED)
                "Alive" -> tvItemStatus.setTextColor(Color.GREEN)
                else -> tvItemStatus.setTextColor(Color.GRAY)
            }
        }
    }

    override fun getItemCount(): Int {
        return listCharacter.size
    }

    fun setList(list: List<CharacterModelItem>) {
        listCharacter = list
        notifyDataSetChanged()
    }

    override fun onViewAttachedToWindow(holder: CharacterViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            CharacterFragment.onClickItem(listCharacter[holder.adapterPosition])
        }
    }
}
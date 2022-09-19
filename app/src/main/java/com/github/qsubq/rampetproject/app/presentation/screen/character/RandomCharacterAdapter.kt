package com.github.qsubq.rampetproject.app.presentation.screen.character

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.github.qsubq.rampetproject.R
import com.github.qsubq.rampetproject.data.model.characterModel.CharacterModelItem
import com.github.qsubq.rampetproject.databinding.CharacterItemLayoutBinding
import com.squareup.picasso.Picasso

class RandomCharacterAdapter : RecyclerView.Adapter<RandomCharacterAdapter.CharacterViewHolder>() {
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

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<CharacterModelItem>) {
        listCharacter = list
        notifyDataSetChanged()
    }

    override fun onViewAttachedToWindow(holder: CharacterViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable("character", listCharacter[holder.bindingAdapterPosition])
            findNavController(holder.itemView).navigate(R.id.action_characterFragment_to_detailFragment,
                bundle)
        }
    }
}
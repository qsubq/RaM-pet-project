package com.github.qsubq.rampetproject.screen.character

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.github.qsubq.rampetproject.R
import com.github.qsubq.rampetproject.databinding.CharacterItemLayoutBinding
import com.github.qsubq.rampetproject.model.characterModel.CharacterModelItem
import com.squareup.picasso.Picasso
import dagger.hilt.android.internal.managers.FragmentComponentManager

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
            val bundle = Bundle()
            bundle.putParcelable("character", listCharacter[holder.adapterPosition])


            val context = FragmentComponentManager.findActivity(it.context) as AppCompatActivity
            val navHostFragment =
                context.supportFragmentManager.findFragmentById(R.id.nav_fragment) as NavHostFragment
            navHostFragment.navController.navigate(R.id.action_characterFragment_to_detailFragment,
                bundle)
        }
    }
}
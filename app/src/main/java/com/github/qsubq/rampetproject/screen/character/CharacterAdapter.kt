package com.github.qsubq.rampetproject.screen.character

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.qsubq.rampetproject.R
import com.github.qsubq.rampetproject.model.CharacterModelItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.character_item_layout.view.*

class CharacterAdapter : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {
    class CharacterViewHolder(view : View) : RecyclerView.ViewHolder(view)

    private var listCharacter = emptyList<CharacterModelItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.character_item_layout, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.itemView.tv_item_name.text = listCharacter[position].name
        Picasso.get().load(listCharacter[position].image).into(holder.itemView.img_item_character)
    }

    override fun getItemCount(): Int {
        return listCharacter.size
    }

    fun setList(list : List<CharacterModelItem>){
        listCharacter = list
        notifyDataSetChanged()
    }

    override fun onViewAttachedToWindow(holder: CharacterViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener{
            CharacterFragment.onClickItem(listCharacter[holder.adapterPosition])
        }
    }
}
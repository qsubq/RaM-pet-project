package com.github.qsubq.rampetproject.screen.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.qsubq.rampetproject.R
import com.github.qsubq.rampetproject.databinding.FragmentDetailBinding
import com.github.qsubq.rampetproject.model.CharacterModelItem
import com.squareup.picasso.Picasso


class DetailFragment : Fragment() {
    private lateinit var binding : FragmentDetailBinding
    private lateinit var currentItem : CharacterModelItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)

        currentItem = requireArguments().getSerializable("character") as CharacterModelItem
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentItem = requireArguments().getSerializable("character") as CharacterModelItem
        init()
    }

    private fun init(){
        Picasso.get().load(currentItem.image).into(binding.imgCharacter)
        binding.tvName.text = currentItem.name
        binding.tvOrigin.text = currentItem.origin.name
        binding.tvSpecies.text = currentItem.species
        binding.tvStatus.text = currentItem.status

        when(currentItem.status){
            "Dead" -> binding.imgStatus.setImageResource(R.drawable.ic_dead_status_image)
            "Alive" -> binding.imgStatus.setImageResource(R.drawable.ic_alive_status_image)
            else -> binding.imgStatus.setImageResource(R.drawable.ic_unknown_status_image)
        }
    }

}
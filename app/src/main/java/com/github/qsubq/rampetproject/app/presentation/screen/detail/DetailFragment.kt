package com.github.qsubq.rampetproject.app.presentation.screen.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.qsubq.rampetproject.R
import com.github.qsubq.rampetproject.databinding.FragmentDetailBinding
import com.github.qsubq.rampetproject.data.model.characterModel.CharacterModelItem
import com.squareup.picasso.Picasso


class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val currentItem by lazy(LazyThreadSafetyMode.NONE) {
        arguments?.getParcelable<CharacterModelItem>("character") as CharacterModelItem
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        Picasso.get().load(currentItem.image).into(binding.imgCharacter)
        binding.tvName.text = currentItem.name
        binding.tvOrigin.text = currentItem.origin.name
        binding.tvSpecies.text = currentItem.species
        binding.tvStatus.text = currentItem.status

        binding.tvId.text = currentItem.id.toString()

        if (currentItem.type == "" || currentItem.type == " ") {
            binding.tvType.text = getString(R.string.unknown_type)
        } else {
            binding.tvType.text = currentItem.type
        }

        when (currentItem.status) {
            "Dead" -> binding.imgStatus.setImageResource(R.drawable.ic_dead_status_image)
            "Alive" -> binding.imgStatus.setImageResource(R.drawable.ic_alive_status_image)
            else -> binding.imgStatus.setImageResource(R.drawable.ic_unknown_status_image)
        }
    }

}
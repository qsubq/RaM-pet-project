package com.github.qsubq.rampetproject.app.presentation.screen.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.qsubq.rampetproject.databinding.FragmentCharacterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterFragment : Fragment() {
    private lateinit var binding: FragmentCharacterBinding
    private val viewModel: CharacterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCharacterBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        if (viewModel.characterList.value == null) {
            getRandomCharacter()
        }
    }

    private fun init() {
        val recyclerVIew = binding.rvCharacters
        val adapter = CharacterAdapter()
        recyclerVIew.adapter = adapter

        viewModel.characterList.observe(viewLifecycleOwner) { list ->
            list.body()?.let { adapter.setList(it) }
        }

        binding.SwipeRefreshLayout.setOnRefreshListener {
            getRandomCharacter()
            binding.SwipeRefreshLayout.isRefreshing = false
        }
    }

    private fun getRandomCharacter() {
        viewModel.getRandomCharacters()

//        view?.let {
//            Snackbar.make(it, "Connection error", 5000)
//                .setAction("Try again") {
//                    getCharacter()
//                }
//                .show()
//        }
    }
}
package com.github.qsubq.rampetproject.app.presentation.screen.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.qsubq.rampetproject.databinding.FragmentCharacterBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RandomCharacterFragment : Fragment() {
    private lateinit var binding: FragmentCharacterBinding
    private val viewModel: RandomCharacterViewModel by viewModels()

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
            viewModel.getRandomCharacters()
        }
    }

    private fun init() {
        val recyclerVIew = binding.rvCharacters
        val adapter = RandomCharacterAdapter()
        recyclerVIew.adapter = adapter

        binding.SwipeRefreshLayout.setOnRefreshListener {
            viewModel.getRandomCharacters()
            binding.SwipeRefreshLayout.isRefreshing = false
        }

        viewModel.characterList.observe(viewLifecycleOwner) { list ->
            adapter.setList(list)
        }
        viewModel.errorLiveData.observe(viewLifecycleOwner){error->
            view?.let { Snackbar.make(it,  error,Snackbar.LENGTH_LONG).show() }
        }
    }
}

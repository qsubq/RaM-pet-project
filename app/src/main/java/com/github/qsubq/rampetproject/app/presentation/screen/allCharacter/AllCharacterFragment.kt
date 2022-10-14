package com.github.qsubq.rampetproject.app.presentation.screen.allCharacter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.github.qsubq.rampetproject.databinding.FragmentAllCharacterBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AllCharacterFragment : Fragment() {
    private lateinit var binding: FragmentAllCharacterBinding
    private val viewModel: AllCharacterViewModel by viewModels()
    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        AllCharacterPageAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAllCharacterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        loadingData()
    }

    private fun init() {
        val recyclerView: RecyclerView = binding.rcView
        recyclerView.adapter = adapter
    }

    private fun loadingData() {
        lifecycleScope.launch {
            viewModel.listData.collect { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }
}
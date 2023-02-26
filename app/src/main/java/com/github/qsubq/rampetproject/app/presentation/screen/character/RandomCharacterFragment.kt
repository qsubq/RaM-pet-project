package com.github.qsubq.rampetproject.app.presentation.screen.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.github.qsubq.rampetproject.app.presentation.utils.MyDialogFragment
import com.github.qsubq.rampetproject.data.repository.RemoteRepositoryImpl.NetworkResult
import com.github.qsubq.rampetproject.databinding.FragmentCharacterBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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

        if (!viewModel.isAlreadyGetCharacters) {
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


        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.myUiState.collect { uiState ->
                    when (uiState) {
                        is NetworkResult.Success -> adapter.setList(uiState.data)
                        is NetworkResult.Error -> MyDialogFragment(uiState.message.toString()).show(requireActivity().supportFragmentManager, "ErrorDialog")
                        is NetworkResult.Exception -> MyDialogFragment(uiState.e.toString()).show(requireActivity().supportFragmentManager, "ErrorDialog")
                    }
                }
            }
        }
    }
}

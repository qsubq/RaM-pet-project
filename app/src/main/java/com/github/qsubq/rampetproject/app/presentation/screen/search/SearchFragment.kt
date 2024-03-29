package com.github.qsubq.rampetproject.app.presentation.screen.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.qsubq.rampetproject.app.extension.showToast
import com.github.qsubq.rampetproject.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private val viewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        binding.editTextSearch.doAfterTextChanged {
            viewModel.submitQuery(it.toString())
        }
    }

    private fun init() {
        val adapter = SearchAdapter()
        binding.rcView.adapter = adapter

        viewModel.characterLiveData.observe(viewLifecycleOwner) { list ->
            adapter.setList(list)
        }
        viewModel.errorLiveData.observe(viewLifecycleOwner) { error ->
            view?.let {
                showToast(error)
            }
        }
    }
}
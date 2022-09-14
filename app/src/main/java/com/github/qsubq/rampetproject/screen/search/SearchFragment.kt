package com.github.qsubq.rampetproject.screen.search

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.qsubq.rampetproject.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private val viewModel:SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var currentText = ""
        val handler = Handler(Looper.getMainLooper())
        val searchRunnable = Runnable {
            viewModel.submitQuery(currentText)
        }

        binding.editTextSearch.doAfterTextChanged {
            currentText = it?.toString() ?: ""
            handler.removeCallbacks(searchRunnable)
            handler.postDelayed(searchRunnable,500L)
        }
    }
}